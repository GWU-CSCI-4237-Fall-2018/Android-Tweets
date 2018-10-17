package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

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