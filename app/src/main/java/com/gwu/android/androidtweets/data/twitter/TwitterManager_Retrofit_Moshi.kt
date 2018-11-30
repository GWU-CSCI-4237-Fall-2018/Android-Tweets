package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import com.gwu.android.androidtweets.data.Result
import com.gwu.android.androidtweets.data.twitter.models.Tweet
import com.gwu.android.androidtweets.data.twitter.models.TwitterOAuthToken
import com.gwu.android.androidtweets.ui.login.PREF_FILENAME
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val twitterService: TwitterService = retrofit.create(TwitterService::class.java)


    private suspend fun retrieveOAuthToken(): Result<TwitterOAuthToken> {
        val currentToken: TwitterOAuthToken? = oAuthToken
        if (currentToken != null) {
            return Result.Success(currentToken)
        }

        // TODO read from a gitignored string file, but I've already shared this around
        val combinedKey =
            "bFdLdGZTMWJmeUlTeWlyWEsyck43N2NQMzpXZXpRaGsxZnhFaElFbTN2MWQ0T291SmwwQkNvTlh0d2F4dzJPSmZhN3NvRERVcHg5Tg=="

        val response = twitterService.oAuth(
            "Basic $combinedKey",
            "client_credentials"
        ).await()

        val body = response.body()

        return if (response.isSuccessful && body != null) {
            Result.Success(TwitterOAuthToken(body.accessToken))
        } else {
            Result.Failure(Exception("OAuth was unsuccessful"))
        }
    }

    suspend fun retrieveTweets(
        address: Address
    ): Result<List<Tweet>> {

        val oAuthTokenResult = retrieveOAuthToken()
        if (oAuthTokenResult is Result.Failure) {
            return oAuthTokenResult
        }

        val oAuthToken = (oAuthTokenResult as Result.Success).data

        val lat = address.latitude
        val lon = address.longitude
        val topic = "Android"
        val radius = "30mi"
        val response = twitterService.searchTweets(
            authorization = "Bearer ${oAuthToken.token}",
            topic = topic,
            geocode = "$lat,$lon,$radius"
        ).await()

        val body = response.body()
        if (response.isSuccessful && body != null) {
            val statuses = body.statuses
            val convertedStatuses = statuses.map { status ->
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
            return Result.Success(convertedStatuses)
        } else {
            return Result.Failure(Exception("Failed to parse Tweets response"))
        }
    }
}