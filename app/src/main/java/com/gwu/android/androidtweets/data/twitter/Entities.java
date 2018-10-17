package com.gwu.android.androidtweets.data.twitter;

import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class Entities{

	@Json(name = "description")
	private Description description;

	@Json(name = "urls")
	private List<Object> urls;

	@Json(name = "hashtags")
	private List<Object> hashtags;

	@Json(name = "user_mentions")
	private List<Object> userMentions;

	@Json(name = "symbols")
	private List<Object> symbols;

	@Json(name = "media")
	private List<MediaItem> media;

	@Json(name = "url")
	private Url url;

	public void setDescription(Description description){
		this.description = description;
	}

	public Description getDescription(){
		return description;
	}

	public void setUrls(List<Object> urls){
		this.urls = urls;
	}

	public List<Object> getUrls(){
		return urls;
	}

	public void setHashtags(List<Object> hashtags){
		this.hashtags = hashtags;
	}

	public List<Object> getHashtags(){
		return hashtags;
	}

	public void setUserMentions(List<Object> userMentions){
		this.userMentions = userMentions;
	}

	public List<Object> getUserMentions(){
		return userMentions;
	}

	public void setSymbols(List<Object> symbols){
		this.symbols = symbols;
	}

	public List<Object> getSymbols(){
		return symbols;
	}

	public void setMedia(List<MediaItem> media){
		this.media = media;
	}

	public List<MediaItem> getMedia(){
		return media;
	}

	public void setUrl(Url url){
		this.url = url;
	}

	public Url getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Entities{" + 
			"description = '" + description + '\'' + 
			",urls = '" + urls + '\'' + 
			",hashtags = '" + hashtags + '\'' + 
			",user_mentions = '" + userMentions + '\'' + 
			",symbols = '" + symbols + '\'' + 
			",media = '" + media + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}