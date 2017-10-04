package com.jgasteiz.comictrackerandroid.models

import java.io.Serializable

data class Comic(
        val external_id: Int,
        val title: String,
        val publisher: String,
        val release_date: String,
        val price: String,
        val description: String,
        val cover_url: String,
        val weekly_index: Int,
        val external_url: String,
        val is_tracked: Boolean
) : Serializable
