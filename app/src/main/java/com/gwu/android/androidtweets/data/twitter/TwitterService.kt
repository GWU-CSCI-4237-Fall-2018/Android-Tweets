package com.gwu.android.androidtweets.data.twitter

import com.gwu.android.androidtweets.data.twitter.response.oauth.OAuthResponse
import com.gwu.android.androidtweets.data.twitter.response.search.SearchTweetsResponse
import retrofit2.Call
import retrofit2.http.*

interface TwitterService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun oAuth(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String
    ): Call<OAuthResponse>

    @GET("1.1/search/tweets.json")
    fun searchTweets(
        @Header("Authorization") authorization: String,
        @Query("q") topic: String,
        @Query("geocode") geocode: String
    ): Call<SearchTweetsResponse>
}