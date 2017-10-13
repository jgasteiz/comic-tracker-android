package com.jgasteiz.comictrackerandroid.components.comiclist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.jgasteiz.comictrackerandroid.ComicTrackerApiClient
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.components.comicdetail.ComicDetail
import com.jgasteiz.comictrackerandroid.models.Comic
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class WeeklyReleases : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val LOG_TAG = WeeklyReleases::class.java.simpleName

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mComicListView: ListView

    private lateinit var mComicList: List<Comic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Initialize UI components.
        mProgressBar = findViewById(R.id.progressBar)
        mComicListView = findViewById(R.id.comicList)

        // Initialise comic list callback action.
        mComicListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ComicDetail::class.java)
            intent.putExtra("comic", mComicList[position])
            startActivity(intent)
        }

        // Fetch the weekly comics.
        fetchComics()

        // Initialise navigation
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                Toast.makeText(this, "Comics fetched", Toast.LENGTH_SHORT).show()
                val comicListAdapter = ComicListAdapter(this, mComicList)
                mComicListView.invalidate()
                mComicListView.adapter = comicListAdapter
            } else {
                Log.d(LOG_TAG, "No comics fetched, trying again")
                Toast.makeText(this, "No comics fetched, trying again", Toast.LENGTH_SHORT).show()
                fetchComics()
            }
        }
    }
}
