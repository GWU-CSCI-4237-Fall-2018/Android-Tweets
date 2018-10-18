package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import com.gwu.android.androidtweets.data.twitter.models.Tweet
import com.gwu.android.androidtweets.data.twitter.models.TwitterOAuthToken
import com.gwu.android.androidtweets.data.twitter.response.oauth.OAuthResponse
import com.gwu.android.androidtweets.data.twitter.response.search.SearchTweetsResponse
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Uses OkHttp for networking and Moshi for JSON parsing.
 *
 * Requests are made asynchronously via OkHttp's enqueue function. Callbacks are invoked
 * from the background thread (default OkHttp behavior).
 */
class TwitterManager_OkHttp_Moshi {

    private var oAuthToken: TwitterOAuthToken? = null

    private val moshi = Moshi.Builder().build()

    private val oAuthResponseAdapter = moshi.adapter(OAuthResponse::class.java).nullSafe()

    private val searchTweetsResponseAdapter = moshi.adapter(SearchTweetsResponse::class.java).nullSafe()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().let { builder ->
        // For printing request / response to logs
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)

        // Network timeouts
        builder.connectTimeout(20, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)

        builder.build()
    }


    private fun retrieveOAuthToken(
        successCallback: (TwitterOAuthToken) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        val currentToken: TwitterOAuthToken? = oAuthToken
        if (currentToken != null) {
            successCallback(currentToken)
            return
        }

        // TODO read from a gitignored string file, but I've already shared this alround
        val combinedKey =
            "bFdLdGZTMWJmeUlTeWlyWEsyck43N2NQMzpXZXpRaGsxZnhFaElFbTN2MWQ0T291SmwwQkNvTlh0d2F4dzJPSmZhN3NvRERVcHg5Tg=="

        val request = Request.Builder()
            .url("https://api.twitter.com/oauth2/token")
            .header("Authorization", "Basic $combinedKey")
            .post(
                RequestBody.create(
                    MediaType.parse("application/x-www-form-urlencoded"),
                    "grant_type=client_credentials"
                )
            )
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorCallback(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString: String? = response.body()?.string()

                if (response.isSuccessful && responseString != null) {
                    val oAuthResponse = oAuthResponseAdapter.fromJson(responseString)
                    val token = TwitterOAuthToken(
                        oAuthResponse?.accessToken ?: ""
                    )
                    oAuthToken = token
                    successCallback(token)
                } else {
                    errorCallback(Exception("OAuth returned unsuccessfully"))
                }
            }
        })

    }

    fun retrieveTweets(
        address: Address,
        successCallback: (List<Tweet>) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        retrieveOAuthToken(
            successCallback = { token ->
                val lat = address.latitude
                val lon = address.longitude
                val topic = "Android"
                val radius = "30mi"

                val request = Request.Builder()
                    .url("https://api.twitter.com/1.1/search/tweets.json?q=$topic&geocode=$lat,$lon,$radius")
                    .header("Authorization", "Bearer ${token.token}")
                    .build()

                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        errorCallback(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseString: String? = response.body()?.string()

                        if (response.isSuccessful && responseString != null) {
                            val searchResponse = searchTweetsResponseAdapter.fromJson(responseString)
                            val statuses = searchResponse?.statuses ?: listOf()

                            val convertedTweets: List<Tweet> = statuses.map { status ->
                                val content = status.text
                                val user = status.user
                                val name = user.name
                                val handle = user.screenName
                                val profilePictureUrl = user.profileImageUrl
                                Tweet(
                                    name,
                                    handle,
                                    content,
                                    profilePictureUrl
                                )
                            }

                            successCallback(convertedTweets)
                        } else {
                            errorCallback(Exception("OAuth returned unsuccessfully"))
                        }
                    }
                })
            },
            errorCallback = errorCallback
        )
    }
}