package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class HashtagsItem{

	@Json(name = "indices")
	private List<Integer> indices;

	@Json(name = "text")
	private String text;

	public void setIndices(List<Integer> indices){
		this.indices = indices;
	}

	public List<Integer> getIndices(){
		return indices;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"HashtagsItem{" + 
			"indices = '" + indices + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}