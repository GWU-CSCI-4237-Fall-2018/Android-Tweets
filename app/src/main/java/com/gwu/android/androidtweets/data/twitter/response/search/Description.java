package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Description{

	@Json(name = "urls")
	private List<Object> urls;

	public void setUrls(List<Object> urls){
		this.urls = urls;
	}

	public List<Object> getUrls(){
		return urls;
	}

	@Override
 	public String toString(){
		return 
			"Description{" + 
			"urls = '" + urls + '\'' + 
			"}";
		}
}