package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

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
	private long id;

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

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
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