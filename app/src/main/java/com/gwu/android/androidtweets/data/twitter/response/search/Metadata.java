package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Metadata{

	@Json(name = "result_type")
	private String resultType;

	@Json(name = "iso_language_code")
	private String isoLanguageCode;

	public void setResultType(String resultType){
		this.resultType = resultType;
	}

	public String getResultType(){
		return resultType;
	}

	public void setIsoLanguageCode(String isoLanguageCode){
		this.isoLanguageCode = isoLanguageCode;
	}

	public String getIsoLanguageCode(){
		return isoLanguageCode;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"result_type = '" + resultType + '\'' + 
			",iso_language_code = '" + isoLanguageCode + '\'' + 
			"}";
		}
}