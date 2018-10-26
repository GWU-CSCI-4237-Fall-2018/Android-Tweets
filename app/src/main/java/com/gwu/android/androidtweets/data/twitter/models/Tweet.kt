package com.gwu.android.androidtweets.data.twitter.models

import java.io.Serializable

data class Tweet(
    val username: String,
    val handle: String,
    val content: String,
    val iconUrl: String
) : Serializable