package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import com.gwu.android.androidtweets.data.twitter.models.Tweet
import com.gwu.android.androidtweets.data.twitter.models.TwitterOAuthToken
import com.gwu.android.androidtweets.data.twitter.response.oauth.OAuthResponse
import com.gwu.android.androidtweets.data.twitter.response.search.SearchTweetsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Uses Retrofit for networking and Moshi for automatic JSON parsing.
 *
 * Requests are made asynchronously via Retrofit's enqueue function. Callbacks are invoked
 * from the UI thread, which is a default for Retrofit.
 */
class TwitterManager_Retrofit_Moshi {

    private var oAuthToken: TwitterOAuthToken? = null

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

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.twitter.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val twitterService: TwitterService = retrofit.create(TwitterService::class.java)


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

        twitterService.oAuth("Basic $combinedKey", "client_credentials")
            .enqueue(object : retrofit2.Callback<OAuthResponse> {
                override fun onFailure(call: Call<OAuthResponse>, t: Throwable) {
                    errorCallback(Exception(t))
                }

                override fun onResponse(
                    call: Call<OAuthResponse>,
                    response: Response<OAuthResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        successCallback(
                            TwitterOAuthToken(body.accessToken)
                        )
                    } else {
                        errorCallback(Exception("Twitter OAuth call failed"))
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
                twitterService.searchTweets(
                    authorization = "Bearer ${token.token}",
                    topic = topic,
                    geocode = "$lat,$lon,$radius"
                ).enqueue(object : retrofit2.Callback<SearchTweetsResponse> {
                    override fun onFailure(call: Call<SearchTweetsResponse>, t: Throwable) {
                        errorCallback(Exception(t))
                    }

                    override fun onResponse(
                        call: Call<SearchTweetsResponse>,
                        response: Response<SearchTweetsResponse>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            val statuses = body.statuses
                            val convertedTweets: List<Tweet> = statuses.map { status ->
                                val content = status.text
                                val user = status.user
                                val name = user.name
                                val handle = user.screenName
                                val profilePictureUrl = user.profileImageUrl
                                Tweet(
                                    username = name,
                                    handle = handle,
                                    content = content,
                                    iconUrl = profilePictureUrl
                                )
                            }
                            successCallback(convertedTweets)
                        } else {
                            errorCallback(Exception("Twitter OAuth call failed"))
                        }
                    }
                })
            },
            errorCallback = errorCallback
        )
    }
}