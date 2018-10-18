package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import com.gwu.android.androidtweets.data.twitter.models.Tweet
import com.gwu.android.androidtweets.data.twitter.models.TwitterOAuthToken
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Using OkHttp for networking and the standard / built-in org.json.* classes for parsing.
 *
 * Requests are made asynchronously via OkHttp's enqueue function. Callbacks are invoked
 * from the background thread (default OkHttp behavior).
 */
class TwitterManager_OkHttp_JSONObject {

    // The "cached" token
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
                    val json = JSONObject(responseString)
                    val tokenString = json.getString("access_token")

                    val token = TwitterOAuthToken(tokenString)
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
                            val tweets = mutableListOf<Tweet>()
                            val statuses = JSONObject(responseString).getJSONArray("statuses")
                            for (i in 0 until statuses.length()) {
                                val curr = statuses.getJSONObject(i)
                                val text = curr.getString("text")
                                val user = curr.getJSONObject("user")
                                val name = user.getString("name")
                                val handle = user.getString("screen_name")
                                val profilePictureUrl = user.getString("profile_image_url")
                                tweets.add(
                                    Tweet(
                                        iconUrl = profilePictureUrl,
                                        username = name,
                                        handle = handle,
                                        content = text
                                    )
                                )
                            }
                            successCallback(tweets)
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