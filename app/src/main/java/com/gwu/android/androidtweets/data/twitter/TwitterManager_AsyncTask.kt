package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import android.os.AsyncTask
import com.gwu.android.androidtweets.data.twitter.models.Tweet
import com.gwu.android.androidtweets.data.twitter.models.TwitterOAuthToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Uses an AsyncTask to show how you can use Retrofit (and OkHttp's) execute function instead
 * of the enqueue function to execute network requests synchronously.
 *
 * Callbacks are made on the UI thread, thanks to the AsyncTask's functionality.
 *
 * You can also substitute Retrofit for OkHttp and Moshi for org.json.* in a similar manner.
 */
class TwitterManager_AsyncTask(
    private val successCallback: (List<Tweet>) -> Unit,
    private val errorCallback: (Exception) -> Unit
) : AsyncTask<Address, Void, List<Tweet>>() {


    override fun doInBackground(vararg params: Address): List<Tweet>? {
        val firstAddress = params[0]

        return try {
            retrieveTweets(firstAddress)
        } catch (exception: Exception) {
            null
        }
    }

    override fun onPostExecute(result: List<Tweet>?) {
        super.onPostExecute(result)

        if (result == null) {
            errorCallback(Exception("Failed to retrieve Tweets!"))
        } else {
            successCallback(result)
        }
    }

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


    private fun retrieveOAuthToken(): TwitterOAuthToken? {

        // TODO read from a gitignored string file, but I've already shared this alround
        val combinedKey =
            "bFdLdGZTMWJmeUlTeWlyWEsyck43N2NQMzpXZXpRaGsxZnhFaElFbTN2MWQ0T291SmwwQkNvTlh0d2F4dzJPSmZhN3NvRERVcHg5Tg=="

        val response = twitterService.oAuth("Basic $combinedKey", "client_credentials").execute()
        val body = response.body()

        return if (response.isSuccessful && body != null) {
            TwitterOAuthToken(body.accessToken)
        } else {
            null
        }
    }

    private fun retrieveTweets(
        address: Address
    ): List<Tweet>? {
        val oAuthToken = retrieveOAuthToken() ?: return null

        val lat = address.latitude
        val lon = address.longitude
        val topic = "Android"
        val radius = "30mi"

        val response = twitterService.searchTweets(
            authorization = "Bearer ${oAuthToken.token}",
            topic = topic,
            geocode = "$lat,$lon,$radius"
        ).execute()

        val body = response.body()

        return if (response.isSuccessful && body != null) {
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
            convertedTweets
        } else {
            null
        }
    }
}