package com.jgasteiz.comictrackerandroid

import android.os.AsyncTask
import android.util.Log
import com.jgasteiz.comictrackerandroid.models.Comic
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject

class ComicTrackerApiClient {
    private val LOG_TAG = ComicTrackerApiClient::class.java.simpleName

    val baseUrl = "https://comic-tracker.herokuapp.com"
    var token = ""

    fun getMockWeeklyReleases (): List<Comic> {
        return Utils().getSampleWeeklyReleases();
    }

    /**
     * Given a username and a password, call the api to retrieve
     * the given user token.
     */
    fun fetchToken(username: String, password: String) {
        val url = "$baseUrl/api-token-auth/"

        val task = PostStringResponseAsyncTask(object : OnResponseFetched {
            override fun callback(response: String) {
                Log.d(LOG_TAG, response)
                try {
                    val jsonResponse = JSONObject(response)
                    token = jsonResponse.get("token") as String
                    Log.d(LOG_TAG, "Token retrieved: $token")
                } catch (e: JSONException) {
                    Log.e(LOG_TAG, e.message)
                }
            }
        })
        task.execute(url, "{\"username\":\"$username\",\"password\":\"$password\"}")
    }

    /**
     * Given a username and a password, calls the api to retrieve
     * the given user token.
     */
    fun getWeeklyReleases() {
        val url = "$baseUrl/api/new-releases/"
    }

    class GetStringResponseAsyncTask(private val mOnResponseFetched: OnResponseFetched) : AsyncTask<String, Void, String>() {

        private val client = OkHttpClient()

        override fun doInBackground(vararg params: String): String? {
            val url = params[0]

            val request = Request.Builder()
                    .url(url)
                    .build()
            try {
                val response = client.newCall(request).execute()
                return response.body()!!.string()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(response: String) {
            mOnResponseFetched.callback(response)
        }
    }

    class PostStringResponseAsyncTask(private val mOnResponseFetched: OnResponseFetched) : AsyncTask<String, Void, String>() {

        private val client = OkHttpClient()
        val JSON = MediaType.parse("application/json; charset=utf-8")

        override fun doInBackground(vararg params: String): String? {
            val url = params[0]
            val payload = params[1]

            val body = RequestBody.create(JSON, payload)
            val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()
            try {
                val response = client.newCall(request).execute()
                return response.body()!!.string()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(response: String) {
            mOnResponseFetched.callback(response)
        }
    }
}