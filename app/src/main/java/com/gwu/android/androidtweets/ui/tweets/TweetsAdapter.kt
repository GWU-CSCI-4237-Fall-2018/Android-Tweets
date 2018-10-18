package com.gwu.android.androidtweets.ui.tweets

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gwu.android.androidtweets.R
import com.gwu.android.androidtweets.data.twitter.models.Tweet

/**
 * Adapts our list of Tweet model objects to a list of rows on the UI.
 * https://developer.android.com/guide/topics/ui/layout/recyclerview
 */
class TweetsAdapter(
    private val tweets: List<Tweet>,
    private val rowClickListener: OnRowClickListener
) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {

    /**
     * Used for the Activity to receive callbacks when a row is clicked.
     * You can also do this by having the Activity pass a lambda instead:
     *      private val rowClickListener: (Tweet) -> Unit
     */
    interface OnRowClickListener {
        fun onRowItemClicked(tweet: Tweet)
    }

    /**
     * List is ready to render a new row that hasn't been created before. Load (inflate)
     * the XML layout and return a [ViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflater is an object that loads & parses XML, you get it from a Context
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_listitem_tweet, parent, false)
        return ViewHolder(view)
    }

    /**
     * How many total rows to render in your list.
     */
    override fun getItemCount(): Int {
        return tweets.size
    }

    /**
     * The list is ready to render a new row at [position]. It gives you the [ViewHolder]
     * either created from [onCreateViewHolder] or recycled from a row that scrolled offscreen.
     * So, you need to set up the content of the row's UI based on corresponding [Tweet].
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTweet = tweets[position]
        holder.usernameTextView.text = currentTweet.username
        holder.handleTextView.text = currentTweet.handle
        holder.contentTextView.text = currentTweet.content

        holder.cardView.setOnClickListener {
            rowClickListener.onRowItemClicked(currentTweet)
        }
    }

    /**
     * Holds a reference to the views of a row that has already been loaded (inflated) from XML.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardView: CardView = view.findViewById(R.id.cardView)

        val usernameTextView: TextView = view.findViewById(R.id.username)

        val handleTextView: TextView = view.findViewById(R.id.handle)

        val contentTextView: TextView = view.findViewById(R.id.tweet_content)

        val iconImageView: ImageView = view.findViewById(R.id.icon)

    }
}