package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Sizes{

	@Json(name = "small")
	private Small small;

	@Json(name = "large")
	private Large large;

	@Json(name = "thumb")
	private Thumb thumb;

	@Json(name = "medium")
	private Medium medium;

	public void setSmall(Small small){
		this.small = small;
	}

	public Small getSmall(){
		return small;
	}

	public void setLarge(Large large){
		this.large = large;
	}

	public Large getLarge(){
		return large;
	}

	public void setThumb(Thumb thumb){
		this.thumb = thumb;
	}

	public Thumb getThumb(){
		return thumb;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	@Override
 	public String toString(){
		return 
			"Sizes{" + 
			"small = '" + small + '\'' + 
			",large = '" + large + '\'' + 
			",thumb = '" + thumb + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}
}