package com.jgasteiz.comictrackerandroid.components.comiclist

import android.support.v4.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.components.comicdetail.ComicDetailActivity
import com.jgasteiz.comictrackerandroid.models.Comic

class WeeklyReleasesFragment : Fragment() {

    private val LOG_TAG = WeeklyReleasesFragment::class.java.simpleName

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mComicListView: ListView

    private lateinit var mComicList: List<Comic>

    companion object {
        fun newInstance(): Fragment {
            return WeeklyReleasesFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_comic_list, container,false)

        // Initialize UI components.
        mProgressBar = view.findViewById(R.id.progressBar)
        mComicListView = view.findViewById(R.id.comicList)

        // Initialise comic list callback action.
        mComicListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(activity, ComicDetailActivity::class.java)
            intent.putExtra("comic", mComicList[position])
            startActivity(intent)
        }

        // Fetch the weekly comics.
        fetchComics()

        return view
    }

    private fun fetchComics() {
        mProgressBar.animate()
        mProgressBar.visibility = View.VISIBLE

        val apiClient = ComicTrackerApiClient()
        apiClient.getWeeklyReleases { comicList ->
            mComicList = comicList
            mProgressBar.clearAnimation()
            mProgressBar.visibility = View.GONE

            if (mComicList.count() > 0) {
                Log.d(LOG_TAG, "Comics fetched: $mComicList")
                Toast.makeText(activity, "Comics fetched", Toast.LENGTH_SHORT).show()
                val comicListAdapter = ComicListAdapter(activity, mComicList)
                mComicListView.invalidate()
                mComicListView.adapter = comicListAdapter
            } else {
                Log.d(LOG_TAG, "No comics fetched, trying again")
                Toast.makeText(activity, "No comics fetched, trying again", Toast.LENGTH_SHORT).show()
                fetchComics()
            }
        }
    }
}
