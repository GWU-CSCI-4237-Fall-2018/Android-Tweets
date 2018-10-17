package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class Url{

	@Json(name = "urls")
	private List<UrlsItem> urls;

	public void setUrls(List<UrlsItem> urls){
		this.urls = urls;
	}

	public List<UrlsItem> getUrls(){
		return urls;
	}

	@Override
 	public String toString(){
		return 
			"Url{" + 
			"urls = '" + urls + '\'' + 
			"}";
		}
}