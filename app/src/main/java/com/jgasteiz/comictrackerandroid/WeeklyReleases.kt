package com.jgasteiz.comictrackerandroid

import android.app.Activity
import android.os.Bundle
import android.widget.ListView

class WeeklyReleases : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_releases)

        val apiClient = ComicTrackerApiClient()

        apiClient.fetchToken("<something>", "<somethingElse>")

        val comicList = apiClient.getMockWeeklyReleases()
        val comicListView = findViewById<ListView>(R.id.comic_list)
        val comicListAdapter = ComicListAdapter(this, comicList)
        comicListView.adapter = comicListAdapter
    }
}
