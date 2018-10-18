package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SearchTweetsResponse{

	@Json(name = "statuses")
	private List<StatusesItem> statuses;

	@Json(name = "search_metadata")
	private SearchMetadata searchMetadata;

	public void setStatuses(List<StatusesItem> statuses){
		this.statuses = statuses;
	}

	public List<StatusesItem> getStatuses(){
		return statuses;
	}

	public void setSearchMetadata(SearchMetadata searchMetadata){
		this.searchMetadata = searchMetadata;
	}

	public SearchMetadata getSearchMetadata(){
		return searchMetadata;
	}

	@Override
 	public String toString(){
		return 
			"SearchTweetsResponse{" + 
			"statuses = '" + statuses + '\'' + 
			",search_metadata = '" + searchMetadata + '\'' + 
			"}";
		}
}