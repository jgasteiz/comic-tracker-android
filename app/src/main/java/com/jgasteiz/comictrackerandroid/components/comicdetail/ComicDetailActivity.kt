package com.jgasteiz.comictrackerandroid.components.comicdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.models.Comic


class ComicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val comic = intent.getSerializableExtra("comic") as Comic
        supportActionBar?.title = comic.title

        if (savedInstanceState == null) {
            val fragment = ComicDetailFragment.newInstance()

            val bundle = Bundle()
            bundle.putSerializable("comic", comic)
            fragment.arguments = bundle

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, "comicDetail")
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}