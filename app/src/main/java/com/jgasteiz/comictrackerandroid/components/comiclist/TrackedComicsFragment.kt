package com.jgasteiz.comictrackerandroid.components.comiclist

import android.support.v4.app.Fragment
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient

class TrackedComicsFragment : BaseComicListFragment() {

    companion object {
        fun newInstance(): Fragment {
            return TrackedComicsFragment()
        }
    }

    override fun fetchComics() {
        super.fetchComics()

        val apiClient = ComicTrackerApiClient()
        apiClient.getTrackedComics { comicList ->
            this.onComicListFetched(comicList)
        }
    }
}