package com.jgasteiz.comictrackerandroid.components.comiclist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.components.comicdetail.ComicDetail
import com.jgasteiz.comictrackerandroid.models.Comic

class WeeklyReleases : Activity() {

    private val LOG_TAG = WeeklyReleases::class.java.simpleName

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mComicListView: ListView

    private lateinit var mComicList: List<Comic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_releases)

        mProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        mComicListView = findViewById<ListView>(R.id.comicList)

        fetchComics()

        mComicListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ComicDetail::class.java)
            intent.putExtra("comic", mComicList[position])
            startActivity(intent)
        }
    }

    private fun fetchComics() {
        mProgressBar.animate()
        mProgressBar.visibility = ProgressBar.VISIBLE

        val apiClient = ComicTrackerApiClient()
        apiClient.getWeeklyReleases { comicList ->
            mComicList = comicList
            mProgressBar.clearAnimation()
            mProgressBar.visibility = ProgressBar.GONE

            if (mComicList.count() > 0) {
                Log.d(LOG_TAG, "Comics fetched: $mComicList")
                Toast.makeText(this, "Comics fetched", Toast.LENGTH_SHORT).show()
                val comicListAdapter = ComicListAdapter(this, mComicList)
                mComicListView.invalidate()
                mComicListView.adapter = comicListAdapter
            } else {
                Log.d(LOG_TAG, "No comics fetched, trying again")
                Toast.makeText(this, "No comics fetched, trying again", Toast.LENGTH_SHORT).show()
                fetchComics()
            }
        }
    }
}
