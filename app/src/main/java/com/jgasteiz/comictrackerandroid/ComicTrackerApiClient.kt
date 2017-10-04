package com.jgasteiz.comictrackerandroid

import com.jgasteiz.comictrackerandroid.models.Comic

class ComicTrackerApiClient {
    fun getWeeklyReleases (): List<Comic> {
        return Utils().getSampleWeeklyReleases();
    }
}