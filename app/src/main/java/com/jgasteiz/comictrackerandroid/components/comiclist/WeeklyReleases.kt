package com.jgasteiz.comictrackerandroid.components.comiclist

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient
import com.jgasteiz.comictrackerandroid.R

class WeeklyReleases : Activity() {

    private val LOG_TAG = WeeklyReleases::class.java.simpleName

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mComicListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_releases)

        mProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        mComicListView = findViewById<ListView>(R.id.comicList)

        fetchComics()
    }

    private fun fetchComics() {
        mProgressBar.animate()
        mProgressBar.visibility = ProgressBar.VISIBLE

        val apiClient = ComicTrackerApiClient()
        apiClient.getWeeklyReleases { comicList ->
            mProgressBar.clearAnimation()
            mProgressBar.visibility = ProgressBar.GONE

            if (comicList.count() > 0) {
                Log.d(LOG_TAG, "Comics fetched: $comicList")
                Toast.makeText(this, "Comics fetched", Toast.LENGTH_SHORT).show()
                val comicListAdapter = ComicListAdapter(this, comicList)
                mComicListView.adapter = comicListAdapter
            } else {
                Log.d(LOG_TAG, "No comics fetched, trying again")
                Toast.makeText(this, "No comics fetched, trying again", Toast.LENGTH_SHORT).show()
                fetchComics()
            }
        }
    }
}
