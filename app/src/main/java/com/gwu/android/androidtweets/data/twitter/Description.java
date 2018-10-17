package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

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