package com.gwu.android.androidtweets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class TweetsActivity : AppCompatActivity() {

    companion object {
        val INTENT_KEY_LOCATION = "LOCATION_NAME"
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        val locationName = intent.getStringExtra(INTENT_KEY_LOCATION)
        title = getString(R.string.tweet_title, locationName)


        val tweets: List<Tweet> = listOf(
            Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
            Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
            Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
            Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://....")
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = TODO next week
    }
}