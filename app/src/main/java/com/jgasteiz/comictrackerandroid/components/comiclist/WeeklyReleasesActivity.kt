package com.jgasteiz.comictrackerandroid.components.comiclist

import android.os.Bundle
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.components.BaseActivity


class WeeklyReleasesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Weekly Releases"

        if (savedInstanceState == null) {
            val fragment = WeeklyReleasesFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, "weeklyReleases")
                    .commit()
        }
    }
}
