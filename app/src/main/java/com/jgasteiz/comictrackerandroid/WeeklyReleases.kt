package com.jgasteiz.comictrackerandroid

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ListView

class WeeklyReleases : Activity() {

    private val LOG_TAG = WeeklyReleases::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_releases)

        val apiClient = ComicTrackerApiClient()

        apiClient.getWeeklyReleases { comicList ->
            Log.d(LOG_TAG, "Comics fetched: $comicList")
            val comicListView = findViewById<ListView>(R.id.comic_list)
            val comicListAdapter = ComicListAdapter(this, comicList)
            comicListView.adapter = comicListAdapter
        }
    }
}
