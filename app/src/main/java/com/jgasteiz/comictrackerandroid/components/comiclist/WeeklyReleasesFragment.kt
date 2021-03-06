package com.jgasteiz.comictrackerandroid.components.comiclist

import android.support.v4.app.Fragment
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient

class WeeklyReleasesFragment : BaseComicListFragment() {

    companion object {
        fun newInstance(): Fragment {
            return WeeklyReleasesFragment()
        }
    }

    override fun fetchComics() {
        super.fetchComics()

        val apiClient = ComicTrackerApiClient()
        apiClient.getWeeklyReleases { comicList ->
            this.onComicListFetched(comicList)
        }
    }
}
