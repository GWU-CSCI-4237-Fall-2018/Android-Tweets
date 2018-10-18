package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class UserMentionsItem{

	@Json(name = "indices")
	private List<Integer> indices;

	@Json(name = "screen_name")
	private String screenName;

	@Json(name = "id_str")
	private String idStr;

	@Json(name = "name")
	private String name;

	@Json(name = "id")
	private int id;

	public void setIndices(List<Integer> indices){
		this.indices = indices;
	}

	public List<Integer> getIndices(){
		return indices;
	}

	public void setScreenName(String screenName){
		this.screenName = screenName;
	}

	public String getScreenName(){
		return screenName;
	}

	public void setIdStr(String idStr){
		this.idStr = idStr;
	}

	public String getIdStr(){
		return idStr;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"UserMentionsItem{" + 
			"indices = '" + indices + '\'' + 
			",screen_name = '" + screenName + '\'' + 
			",id_str = '" + idStr + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}