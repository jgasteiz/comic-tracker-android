package com.jgasteiz.comictrackerandroid.components.comicdetail

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.models.Comic
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uk.co.senab.photoview.PhotoViewAttacher

class ComicDetail : Activity() {

    private val LOG_TAG = ComicDetail::class.java.simpleName

    private lateinit var mComic: Comic
    private lateinit var mComicTitleView: TextView
    private lateinit var mComicPublisherView: TextView
    private lateinit var mComicPriceView: TextView
    private lateinit var mComicDescriptionView: TextView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mPageImageView: ImageView
    private lateinit var mAttacher: PhotoViewAttacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        // Get comic from intent.
        mComic = intent.getSerializableExtra("comic") as Comic

        // Initialize UI components.
        mComicTitleView = findViewById(R.id.comicTitle)
        mComicPublisherView = findViewById(R.id.comicPublisher)
        mComicPriceView = findViewById(R.id.comicPrice)
        mComicDescriptionView = findViewById(R.id.comicDescription)
        mProgressBar = findViewById(R.id.progressBar)
        mPageImageView = findViewById(R.id.comicCover)
        mAttacher = PhotoViewAttacher(mPageImageView)

        // Populate UI with comic data.
        actionBar.title = mComic.title
        mComicTitleView.text = mComic.title
        mComicPublisherView.text = mComic.publisher
        mComicPriceView.text = mComic.price
        mComicDescriptionView.text = mComic.description

        // Load comic cover.
        Picasso
                .with(this)
                .load(mComic.cover_url)
                .into(mPageImageView, picassoCallback)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    private val picassoCallback: Callback
        get() = object : Callback {
            override fun onSuccess() {
                mProgressBar.visibility = View.GONE
                mAttacher.update()
            }

            override fun onError() {
                Log.e(LOG_TAG, "An error occurred")
            }
        }
}