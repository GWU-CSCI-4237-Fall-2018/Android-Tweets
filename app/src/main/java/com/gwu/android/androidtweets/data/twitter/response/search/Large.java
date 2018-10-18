package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Large{

	@Json(name = "w")
	private int W;

	@Json(name = "h")
	private int H;

	@Json(name = "resize")
	private String resize;

	public void setW(int W){
		this.W = W;
	}

	public int getW(){
		return W;
	}

	public void setH(int H){
		this.H = H;
	}

	public int getH(){
		return H;
	}

	public void setResize(String resize){
		this.resize = resize;
	}

	public String getResize(){
		return resize;
	}

	@Override
 	public String toString(){
		return 
			"Large{" + 
			"w = '" + W + '\'' + 
			",h = '" + H + '\'' + 
			",resize = '" + resize + '\'' + 
			"}";
		}
}