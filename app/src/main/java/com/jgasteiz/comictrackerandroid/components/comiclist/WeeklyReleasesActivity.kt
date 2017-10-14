package com.jgasteiz.comictrackerandroid.components.comiclist

import android.os.Bundle
import android.widget.ListView
import android.widget.ProgressBar
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.components.BaseActivity
import com.jgasteiz.comictrackerandroid.models.Comic


class WeeklyReleasesActivity : BaseActivity() {
    private val LOG_TAG = WeeklyReleasesActivity::class.java.simpleName

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mComicListView: ListView

    private lateinit var mComicList: List<Comic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = WeeklyReleasesFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, "weeklyReleases")
                    .commit()
        }
    }
}
