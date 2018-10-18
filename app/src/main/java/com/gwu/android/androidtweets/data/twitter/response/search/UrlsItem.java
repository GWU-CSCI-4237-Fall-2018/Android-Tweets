package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class UrlsItem{

	@Json(name = "display_url")
	private String displayUrl;

	@Json(name = "indices")
	private List<Integer> indices;

	@Json(name = "expanded_url")
	private String expandedUrl;

	@Json(name = "url")
	private String url;

	public void setDisplayUrl(String displayUrl){
		this.displayUrl = displayUrl;
	}

	public String getDisplayUrl(){
		return displayUrl;
	}

	public void setIndices(List<Integer> indices){
		this.indices = indices;
	}

	public List<Integer> getIndices(){
		return indices;
	}

	public void setExpandedUrl(String expandedUrl){
		this.expandedUrl = expandedUrl;
	}

	public String getExpandedUrl(){
		return expandedUrl;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"UrlsItem{" + 
			"display_url = '" + displayUrl + '\'' + 
			",indices = '" + indices + '\'' + 
			",expanded_url = '" + expandedUrl + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}