package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ExtendedEntities{

	@Json(name = "media")
	private List<MediaItem> media;

	public void setMedia(List<MediaItem> media){
		this.media = media;
	}

	public List<MediaItem> getMedia(){
		return media;
	}

	@Override
 	public String toString(){
		return 
			"ExtendedEntities{" + 
			"media = '" + media + '\'' + 
			"}";
		}
}