package com.gwu.android.androidtweets

import android.location.Address
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.gwu.android.androidtweets.data.twitter.TwitterManager

class TweetsActivity : AppCompatActivity(), TweetsAdapter.OnRowClickListener {

    // companion objects provide "static" access, in Java terms...
    companion object {
        // ... because we want this constant to be shared with another class
        val INTENT_KEY_LOCATION = "LOCATION_NAME"
    }

    private lateinit var recyclerView: RecyclerView

    /**
     * Called when the Activity is being rendered for the first time, but before anything is
     * shown to the user.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        // Retrieve the Address out of the intent. An Address isn't a primitive type, so we need to use
        // getSerializableExtra or getParcelableExtra (and an Address implements Parcelable).
        // TODO will cover this in the next lecture.
        val address: Address = intent.getParcelableExtra(INTENT_KEY_LOCATION)
        title = getString(R.string.tweet_title, address.getAddressLine(0))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        TwitterManager().retrieveTweets(
            address = address,
            successCallback = { tweetsList ->
                runOnUiThread {
                    recyclerView.adapter = TweetsAdapter(tweetsList, this)
                }
            },
            errorCallback = {
                runOnUiThread {
                    Toast.makeText(this, "Failed to retrieve Tweets!", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    /**
     * For now, just demonstrating how to show a multi-choice dialog -- but not related to the
     * actual planned functionality of the Android Tweets app.
     */
    override fun onRowItemClicked(tweet: Tweet) {
        // Data
        val choicesList = listOf("A", "B", "C")

        // Adapts the data to a UI
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice)
        arrayAdapter.addAll(choicesList)

        AlertDialog.Builder(this)
            .setTitle("Choose One")
            .setAdapter(arrayAdapter) { dialog, index ->
                Toast.makeText(this, "You picked ${choicesList[index]}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("CANCEL") { dialog, id ->
                dialog.dismiss()
            }
            .show()
    }

}