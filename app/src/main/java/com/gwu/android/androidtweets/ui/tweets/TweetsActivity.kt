package com.gwu.android.androidtweets.ui.tweets

import android.location.Address
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gwu.android.androidtweets.R
import com.gwu.android.androidtweets.data.twitter.TwitterManager_Retrofit_Moshi
import com.gwu.android.androidtweets.data.twitter.models.Tweet

class TweetsActivity : AppCompatActivity(),
    TweetsAdapter.OnRowClickListener {

    // companion objects provide "static" access, in Java terms...
    companion object {
        // ... because we want this constant to be shared with another class
        val INTENT_KEY_LOCATION = "LOCATION_NAME"
    }

    private val BUNDLE_KEY_TWEETS_LIST = "TWEETS_LIST"

    private lateinit var recyclerView: RecyclerView

    private lateinit var tweetContent: EditText

    private lateinit var addTweet: FloatingActionButton

    private lateinit var firebaseDatabase: FirebaseDatabase

    private val currentTweetsList: MutableList<Tweet> = mutableListOf()

    /**
     * Called when the Activity is being rendered for the first time, but before anything is
     * shown to the user.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        firebaseDatabase = FirebaseDatabase.getInstance()


        // Retrieve the Address out of the intent. An Address isn't a primitive type, so we need to use
        // getSerializableExtra or getParcelableExtra (and an Address implements Parcelable).
        // TODO will cover this in a future lecture.
        val address: Address = intent.getParcelableExtra(INTENT_KEY_LOCATION)

        // If the user picks a location in the USA, the "adminArea" == state
        val state: String = address.adminArea ?: "Unknown"

        // The point in the Firebase DB where we want to read / write
        val firebasePath = "tweets/$state"

        val stateReference = firebaseDatabase.getReference(firebasePath)


        title = getString(R.string.tweet_title, state)

        tweetContent = findViewById(R.id.tweet_content)
        addTweet = findViewById(R.id.add_tweet)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addTweet.setOnClickListener {
            // Post the Tweet to Firebase
            val content = tweetContent.text.toString()
            if (content.isNotEmpty()) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val email = currentUser!!.email!!
                val handle = email
                val profilePictureUrl = ""
                val tweet = Tweet(email, handle, content, profilePictureUrl)

                stateReference.push().setValue(tweet)
            }
        }

        if (savedInstanceState != null) {
            // The user has rotated the screen, restore state
            val savedTweets: List<Tweet> = savedInstanceState.getSerializable(BUNDLE_KEY_TWEETS_LIST) as List<Tweet>

            currentTweetsList.clear()
            currentTweetsList.addAll(savedTweets)

            recyclerView.adapter =
                TweetsAdapter(savedTweets, this)
        } else {

            stateReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        this@TweetsActivity,
                        "Failed to retrieve Firebase! Error: ${databaseError.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    currentTweetsList.clear()

                    dataSnapshot.children.forEach { data ->
                        val tweet: Tweet? = data.getValue(Tweet::class.java)
                        if (tweet != null) {
                            currentTweetsList.add(tweet)
                        }
                    }

                    recyclerView.adapter = TweetsAdapter(
                        tweets = currentTweetsList,
                        rowClickListener = this@TweetsActivity
                    )

                }

            })



            GlobalScope.launch(Dispatchers.Main) {
                val tweetsResult = TwitterManager_Retrofit_Moshi().retrieveTweets(address)
                if (tweetsResult is Result.Failure) {
                    Toast.makeText(this@TweetsActivity, "Failed to retrieve Tweets!", Toast.LENGTH_LONG).show()
                } else {
                    tweetsResult as Result.Success
                    currentTweetsList.clear()
                    currentTweetsList.addAll(tweetsResult.data)
                        recyclerView.adapter =
                            TweetsAdapter(tweetsResult.data, this@TweetsActivity)
                }
            }
        }
    }

    /**
     * The screen is going to be recreated, so let's save the current list of Tweets
     * to restore after rotation.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(BUNDLE_KEY_TWEETS_LIST, ArrayList(currentTweetsList))
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