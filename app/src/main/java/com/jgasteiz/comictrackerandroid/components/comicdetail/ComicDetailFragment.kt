package com.jgasteiz.comictrackerandroid.components.comicdetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.models.Comic
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uk.co.senab.photoview.PhotoViewAttacher

class ComicDetailFragment : Fragment() {

    private val LOG_TAG = ComicDetailFragment::class.java.simpleName

    private lateinit var mComic: Comic
    private lateinit var mComicTitleView: TextView
    private lateinit var mComicPublisherView: TextView
    private lateinit var mComicPriceView: TextView
    private lateinit var mComicDescriptionView: TextView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mPageImageView: ImageView
    private lateinit var mAttacher: PhotoViewAttacher

    companion object {
        fun newInstance(): Fragment {
            return ComicDetailFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_comic_detail, container,false)

        // Get comic from intent.
        mComic = arguments.getSerializable("comic") as Comic

        // Initialize UI components.
        mComicTitleView = view.findViewById(R.id.comicTitle)
        mComicPublisherView = view.findViewById(R.id.comicPublisher)
        mComicPriceView = view.findViewById(R.id.comicPrice)
        mComicDescriptionView = view.findViewById(R.id.comicDescription)
        mProgressBar = view.findViewById(R.id.progressBar)
        mPageImageView = view.findViewById(R.id.comicCover)
        mAttacher = PhotoViewAttacher(mPageImageView)

        // Populate UI with comic data.
        mComicTitleView.text = mComic.title
        mComicPublisherView.text = mComic.publisher
        mComicPriceView.text = mComic.price
        mComicDescriptionView.text = mComic.description

        // Load comic cover.
        Picasso
                .with(activity)
                .load(mComic.cover_url)
                .into(mPageImageView, picassoCallback)

        return view
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