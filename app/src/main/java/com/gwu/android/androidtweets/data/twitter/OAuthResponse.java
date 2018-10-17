package com.gwu.android.androidtweets.data.twitter;

import com.squareup.moshi.Json;

public class OAuthResponse {

    @Json(name = "access_token")
    public String accessToken;

    @Json(name = "token_type")
    public  String tokenType;

}
