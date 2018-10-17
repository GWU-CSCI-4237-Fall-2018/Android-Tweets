package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class MediaItem{

	@Json(name = "display_url")
	private String displayUrl;

	@Json(name = "indices")
	private List<Integer> indices;

	@Json(name = "sizes")
	private Sizes sizes;

	@Json(name = "id_str")
	private String idStr;

	@Json(name = "expanded_url")
	private String expandedUrl;

	@Json(name = "media_url_https")
	private String mediaUrlHttps;

	@Json(name = "id")
	private long id;

	@Json(name = "type")
	private String type;

	@Json(name = "media_url")
	private String mediaUrl;

	@Json(name = "url")
	private String url;

	public void setDisplayUrl(String displayUrl){
		this.displayUrl = displayUrl;
	}

	public String getDisplayUrl(){
		return displayUrl;
	}

	public void setIndices(List<Integer> indices){
		this.indices = indices;
	}

	public List<Integer> getIndices(){
		return indices;
	}

	public void setSizes(Sizes sizes){
		this.sizes = sizes;
	}

	public Sizes getSizes(){
		return sizes;
	}

	public void setIdStr(String idStr){
		this.idStr = idStr;
	}

	public String getIdStr(){
		return idStr;
	}

	public void setExpandedUrl(String expandedUrl){
		this.expandedUrl = expandedUrl;
	}

	public String getExpandedUrl(){
		return expandedUrl;
	}

	public void setMediaUrlHttps(String mediaUrlHttps){
		this.mediaUrlHttps = mediaUrlHttps;
	}

	public String getMediaUrlHttps(){
		return mediaUrlHttps;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setMediaUrl(String mediaUrl){
		this.mediaUrl = mediaUrl;
	}

	public String getMediaUrl(){
		return mediaUrl;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"MediaItem{" + 
			"display_url = '" + displayUrl + '\'' + 
			",indices = '" + indices + '\'' + 
			",sizes = '" + sizes + '\'' + 
			",id_str = '" + idStr + '\'' + 
			",expanded_url = '" + expandedUrl + '\'' + 
			",media_url_https = '" + mediaUrlHttps + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",media_url = '" + mediaUrl + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}