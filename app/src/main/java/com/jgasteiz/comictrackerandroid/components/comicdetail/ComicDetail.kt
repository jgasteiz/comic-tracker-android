package com.jgasteiz.comictrackerandroid.components.comicdetail

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.models.Comic

class ComicDetail : Activity() {

    private val LOG_TAG = ComicDetail::class.java.simpleName

    private lateinit var mComic: Comic
    private lateinit var mComicTitleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        mComicTitleView = findViewById(R.id.comic_title)

        mComic = intent.getSerializableExtra("comic") as Comic
        mComicTitleView.text = mComic.title
    }
}