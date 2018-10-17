package com.gwu.android.androidtweets.data.twitter

import android.location.Address
import com.gwu.android.androidtweets.Tweet
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class TwitterManager {

    private var oAuthToken: TwitterOAuthToken? = null

    private val moshi = Moshi.Builder().build()

    private val oAuthResponseAdapter = moshi.adapter(OAuthResponse::class.java)

    private val searchTweetsResponseAdapter = moshi.adapter(SearchTweetsResponse::class.java)

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


        val combinedKey: String =
            "bFdLdGZTMWJmeUlTeWlyWEsyck43N2NQMzpXZXpRaGsxZnhFaElFbTN2MWQ0T291SmwwQkNvTlh0d2F4dzJPSmZhN3NvRERVcHg5Tg=="

        // Step 2 - Construct the request
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
//                    val json = JSONObject(responseString)
//                    val tokenString = json.getString("access_token")
//
//                    val token = TwitterOAuthToken(tokenString)
                    val oAuthResponse = oAuthResponseAdapter.fromJson(responseString)
                    val token = TwitterOAuthToken(oAuthResponse?.accessToken ?: "")
                    oAuthToken = token
                    successCallback(token)
                } else {
                    errorCallback(Exception("OAuth returned unsuccessfully"))
                }
            }
        })

    }

    fun retrieveTweets(
        address: Address, // android.location.Address class (from Maps screen)
        successCallback: (List<Tweet>) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        retrieveOAuthToken(
            successCallback = { token ->
                // Data setup
                val lat = address.latitude
                val lon = address.longitude
                val topic = "Android"
                val radius = "30mi"

                // Building the request, passing the OAuth token as a header
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

    /**
     * Will be removed after we implement networking.
     */
    private fun getFakeTweets(): List<Tweet> = listOf(
        Tweet(
            username = "Nick Capurso",
            handle = "@nickcapurso",
            content = "We're learning lists!",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "Android Central",
            handle = "@androidcentral",
            content = "NVIDIA Shield TV vs. Shield TV Pro: Which should I buy?",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "DC Android",
            handle = "@DCAndroid",
            content = "FYI - another great integration for the @Firebase platform",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "KotlinConf",
            handle = "@kotlinconf",
            content = "Can't make it to KotlinConf this year? We have a surprise for you. We'll be live streaming the keynotes, closing panel and an entire track over the 2 main conference days. Sign-up to get notified once we go live!",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "Android Summit",
            handle = "@androidsummit",
            content = "What a #Keynote! @SlatteryClaire is the Director of Performance at Speechless, and that's exactly how she left us after her amazing (and interactive!) #keynote at #androidsummit. #DCTech #AndroidDev #Android",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "Fragmented Podcast",
            handle = "@FragmentedCast",
            content = ".... annnnnnnnnd we're back!\n\nThis week @donnfelker talks about how it's Ok to not know everything and how to set yourself up mentally for JIT (Just In Time [learning]). Listen in here: \nhttp://fragmentedpodcast.com/episodes/135/ ",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "Jake Wharton",
            handle = "@JakeWharton",
            content = "Free idea: location-aware physical password list inside a password manager. Mostly for garage door codes and the like. I want to open my password app, switch to the non-URL password section, and see a list of things sorted by physical distance to me.",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "Droidcon Boston",
            handle = "@droidconbos",
            content = "#DroidconBos will be back in Boston next year on April 8-9!",
            iconUrl = "https://...."
        ),
        Tweet(
            username = "AndroidWeekly",
            handle = "@androidweekly",
            content = "Latest Android Weekly Issue 327 is out!\nhttp://androidweekly.net/ #latest-issue  #AndroidDev",
            iconUrl = "https://...."
        ),
        Tweet(
            username = ".droidconSF",
            handle = "@droidconSF",
            content = "Drum roll please.. Announcing droidcon SF 2018! November 19-20 @ Mission Bay Conference Center. Content and programming by @tsmith & @joenrv.",
            iconUrl = "https://...."
        )
    )
}