package com.jgasteiz.comictrackerandroid

import android.os.AsyncTask
import com.jgasteiz.comictrackerandroid.interfaces.FetchResponseInBackground
import com.jgasteiz.comictrackerandroid.interfaces.OnResponseFetched
import com.jgasteiz.comictrackerandroid.models.Comic
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject

class ComicTrackerApiClient {
    private val LOG_TAG = ComicTrackerApiClient::class.java.simpleName

    private val baseUrl = "https://comic-tracker.herokuapp.com"
    private var token = ""

    /**
     * Get the weekly releases.
     */
    fun getWeeklyReleases (onWeeklyReleasesFetched: (List<Comic>) -> Unit) {
        val task = ApiAsyncTask(object: FetchResponseInBackground {
            override fun onResponseFetched(): String {
                return fetchWeeklyReleases()
            }
        }, object : OnResponseFetched {
            override fun parseResponse(response: String) {
                // Parse the response and pass it to the callback
                onWeeklyReleasesFetched(parseComicsResponse(response))
            }
        })
        task.execute()
    }

    /**
     * Get the tracked comics for this week.
     */
    fun getTrackedComics (onTrackedComicsFetched: (List<Comic>) -> Unit) {
        val task = ApiAsyncTask(object: FetchResponseInBackground {
            override fun onResponseFetched(): String {
                return fetchTrackedComics()
            }
        }, object : OnResponseFetched {
            override fun parseResponse(response: String) {
                // Parse the response and pass it to the callback
                onTrackedComicsFetched(parseComicsResponse(response))
            }
        })
        task.execute()
    }

    /**
     * Fetch the token if needed.
     */
    private fun fetchToken() {
        val client = OkHttpClient()

        val url = "$baseUrl/api-token-auth/"
        val payload = "{\"username\":\"${Secret.USERNAME}\",\"password\":\"${Secret.PASSWORD}\"}"
        val JSON = MediaType.parse("application/json; charset=utf-8")

        val body = RequestBody.create(JSON, payload)
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
        try {
            val response = client.newCall(request).execute()
            val jsonResponse = JSONObject(response.body()!!.string())
            token = jsonResponse.get("token") as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return
    }

    /**
     * Fetch the weekly releases from the api.
     */
    private fun fetchWeeklyReleases() : String {
        if (token == "") {
            fetchToken()
        }

        val url = "$baseUrl/api/new-releases/"
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .header("Authorization", "Token $token")
                .build()
        try {
            val response = client.newCall(request).execute()
            return response.body()!!.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "[]"
    }

    /**
     * Fetch the weekly releases from the api.
     */
    private fun fetchTrackedComics() : String {
        if (token == "") {
            fetchToken()
        }

        val url = "$baseUrl/api/tracked-comics/"
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .header("Authorization", "Token $token")
                .build()
        try {
            val response = client.newCall(request).execute()
            return response.body()!!.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "[]"
    }

    /**
     * Given a json response, parse the weekly releases and return
     * a list of Comic instances.
     */
    private fun parseComicsResponse (response: String) : ArrayList<Comic> {
        val comicList = ArrayList<Comic>()
        try {
            val jsonResponse = JSONArray(response)
            for (i in 0..(jsonResponse.length() - 1)) {
                val jsonComic = jsonResponse.getJSONObject(i)
                val comic = Comic(
                        jsonComic.getInt("external_id"),
                        jsonComic.getString("title"),
                        jsonComic.getString("publisher"),
                        jsonComic.getString("release_date"),
                        jsonComic.getString("price"),
                        jsonComic.getString("description"),
                        jsonComic.getString("cover_url"),
                        jsonComic.getInt("weekly_index"),
                        jsonComic.getString("external_url"),
                        jsonComic.getBoolean("is_tracked")
                )
                comicList.add(comic)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return comicList
    }

    private class ApiAsyncTask(
            private val mInBackground: FetchResponseInBackground,
            private val mOnResponseFetched: OnResponseFetched
    ) : AsyncTask<String, Void, String>()
    {
        override fun doInBackground(vararg p0: String?): String {
            return mInBackground.onResponseFetched()
        }
        override fun onPostExecute(response: String) {
             mOnResponseFetched.parseResponse(response)
        }
    }
}