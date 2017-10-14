package com.jgasteiz.comictrackerandroid.components.comiclist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jgasteiz.comictrackerandroid.R
import com.jgasteiz.comictrackerandroid.models.Comic

class ComicListAdapter internal constructor(context: Context, comicList: List<Comic>?) : ArrayAdapter<Comic>(context, 0, comicList) {

    private val LOG_TAG = ComicListAdapter::class.java.simpleName

    // We need a reference to the parent activity.
    private val mWeeklyReleases: WeeklyReleasesActivity = context as WeeklyReleasesActivity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cView = convertView
        // Get the data item for this position
        val comic = getItem(position)

        // Inflate the view if necessary.
        if (cView == null) {
            cView = LayoutInflater.from(context).inflate(R.layout.comic_item, parent, false)!!
        }

        // Get references to the text views.
        val comicTitleView = cView.findViewById<TextView>(R.id.comic_title)
        val comicPublisherView = cView.findViewById<TextView>(R.id.comic_publisher)
        val comicReleaseDateView = cView.findViewById<TextView>(R.id.release_date)
        val comicPriceView = cView.findViewById<TextView>(R.id.comic_price)

        // Set the comic title and publisher.
        comicTitleView.text = comic.title
        comicPublisherView.text = comic.publisher
        comicReleaseDateView.text = comic.release_date
        comicPriceView.text = comic.price

        return cView
    }
}
