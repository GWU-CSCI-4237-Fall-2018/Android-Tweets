package com.gwu.android.androidtweets.data.twitter;

import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class RetweetedStatus{

	@Json(name = "metadata")
	private Metadata metadata;

	@Json(name = "in_reply_to_status_id_str")
	private String inReplyToStatusIdStr;

	@Json(name = "in_reply_to_status_id")
	private long inReplyToStatusId;

	@Json(name = "created_at")
	private String createdAt;

	@Json(name = "in_reply_to_user_id_str")
	private String inReplyToUserIdStr;

	@Json(name = "source")
	private String source;

	@Json(name = "retweet_count")
	private int retweetCount;

	@Json(name = "retweeted")
	private boolean retweeted;

	@Json(name = "geo")
	private Object geo;

	@Json(name = "in_reply_to_screen_name")
	private String inReplyToScreenName;

	@Json(name = "is_quote_status")
	private boolean isQuoteStatus;

	@Json(name = "id_str")
	private String idStr;

	@Json(name = "in_reply_to_user_id")
	private int inReplyToUserId;

	@Json(name = "favorite_count")
	private int favoriteCount;

	@Json(name = "id")
	private long id;

	@Json(name = "text")
	private String text;

	@Json(name = "place")
	private Object place;

	@Json(name = "lang")
	private String lang;

	@Json(name = "favorited")
	private boolean favorited;

	@Json(name = "possibly_sensitive")
	private boolean possiblySensitive;

	@Json(name = "coordinates")
	private Object coordinates;

	@Json(name = "truncated")
	private boolean truncated;

	@Json(name = "entities")
	private Entities entities;

	@Json(name = "contributors")
	private Object contributors;

	@Json(name = "user")
	private User user;

	@Json(name = "quoted_status")
	private QuotedStatus quotedStatus;

	@Json(name = "quoted_status_id")
	private long quotedStatusId;

	@Json(name = "quoted_status_id_str")
	private String quotedStatusIdStr;

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setInReplyToStatusIdStr(String inReplyToStatusIdStr){
		this.inReplyToStatusIdStr = inReplyToStatusIdStr;
	}

	public String getInReplyToStatusIdStr(){
		return inReplyToStatusIdStr;
	}

	public void setInReplyToStatusId(long inReplyToStatusId){
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public long getInReplyToStatusId(){
		return inReplyToStatusId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setInReplyToUserIdStr(String inReplyToUserIdStr){
		this.inReplyToUserIdStr = inReplyToUserIdStr;
	}

	public String getInReplyToUserIdStr(){
		return inReplyToUserIdStr;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setRetweetCount(int retweetCount){
		this.retweetCount = retweetCount;
	}

	public int getRetweetCount(){
		return retweetCount;
	}

	public void setRetweeted(boolean retweeted){
		this.retweeted = retweeted;
	}

	public boolean isRetweeted(){
		return retweeted;
	}

	public void setGeo(Object geo){
		this.geo = geo;
	}

	public Object getGeo(){
		return geo;
	}

	public void setInReplyToScreenName(String inReplyToScreenName){
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public String getInReplyToScreenName(){
		return inReplyToScreenName;
	}

	public void setIsQuoteStatus(boolean isQuoteStatus){
		this.isQuoteStatus = isQuoteStatus;
	}

	public boolean isIsQuoteStatus(){
		return isQuoteStatus;
	}

	public void setIdStr(String idStr){
		this.idStr = idStr;
	}

	public String getIdStr(){
		return idStr;
	}

	public void setInReplyToUserId(int inReplyToUserId){
		this.inReplyToUserId = inReplyToUserId;
	}

	public int getInReplyToUserId(){
		return inReplyToUserId;
	}

	public void setFavoriteCount(int favoriteCount){
		this.favoriteCount = favoriteCount;
	}

	public int getFavoriteCount(){
		return favoriteCount;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setPlace(Object place){
		this.place = place;
	}

	public Object getPlace(){
		return place;
	}

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	public void setFavorited(boolean favorited){
		this.favorited = favorited;
	}

	public boolean isFavorited(){
		return favorited;
	}

	public void setPossiblySensitive(boolean possiblySensitive){
		this.possiblySensitive = possiblySensitive;
	}

	public boolean isPossiblySensitive(){
		return possiblySensitive;
	}

	public void setCoordinates(Object coordinates){
		this.coordinates = coordinates;
	}

	public Object getCoordinates(){
		return coordinates;
	}

	public void setTruncated(boolean truncated){
		this.truncated = truncated;
	}

	public boolean isTruncated(){
		return truncated;
	}

	public void setEntities(Entities entities){
		this.entities = entities;
	}

	public Entities getEntities(){
		return entities;
	}

	public void setContributors(Object contributors){
		this.contributors = contributors;
	}

	public Object getContributors(){
		return contributors;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setQuotedStatus(QuotedStatus quotedStatus){
		this.quotedStatus = quotedStatus;
	}

	public QuotedStatus getQuotedStatus(){
		return quotedStatus;
	}

	public void setQuotedStatusId(long quotedStatusId){
		this.quotedStatusId = quotedStatusId;
	}

	public long getQuotedStatusId(){
		return quotedStatusId;
	}

	public void setQuotedStatusIdStr(String quotedStatusIdStr){
		this.quotedStatusIdStr = quotedStatusIdStr;
	}

	public String getQuotedStatusIdStr(){
		return quotedStatusIdStr;
	}

	@Override
 	public String toString(){
		return 
			"RetweetedStatus{" + 
			"metadata = '" + metadata + '\'' + 
			",in_reply_to_status_id_str = '" + inReplyToStatusIdStr + '\'' + 
			",in_reply_to_status_id = '" + inReplyToStatusId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",in_reply_to_user_id_str = '" + inReplyToUserIdStr + '\'' + 
			",source = '" + source + '\'' + 
			",retweet_count = '" + retweetCount + '\'' + 
			",retweeted = '" + retweeted + '\'' + 
			",geo = '" + geo + '\'' + 
			",in_reply_to_screen_name = '" + inReplyToScreenName + '\'' + 
			",is_quote_status = '" + isQuoteStatus + '\'' + 
			",id_str = '" + idStr + '\'' + 
			",in_reply_to_user_id = '" + inReplyToUserId + '\'' + 
			",favorite_count = '" + favoriteCount + '\'' + 
			",id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			",place = '" + place + '\'' + 
			",lang = '" + lang + '\'' + 
			",favorited = '" + favorited + '\'' + 
			",possibly_sensitive = '" + possiblySensitive + '\'' + 
			",coordinates = '" + coordinates + '\'' + 
			",truncated = '" + truncated + '\'' + 
			",entities = '" + entities + '\'' + 
			",contributors = '" + contributors + '\'' + 
			",user = '" + user + '\'' + 
			",quoted_status = '" + quotedStatus + '\'' + 
			",quoted_status_id = '" + quotedStatusId + '\'' + 
			",quoted_status_id_str = '" + quotedStatusIdStr + '\'' + 
			"}";
		}
}