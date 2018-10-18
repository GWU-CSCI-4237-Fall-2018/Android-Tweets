package com.gwu.android.androidtweets.data.twitter.response.search;

import com.squareup.moshi.Json;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class User{

	@Json(name = "utc_offset")
	private Object utcOffset;

	@Json(name = "friends_count")
	private int friendsCount;

	@Json(name = "profile_image_url_https")
	private String profileImageUrlHttps;

	@Json(name = "listed_count")
	private int listedCount;

	@Json(name = "profile_background_image_url")
	private String profileBackgroundImageUrl;

	@Json(name = "default_profile_image")
	private boolean defaultProfileImage;

	@Json(name = "favourites_count")
	private int favouritesCount;

	@Json(name = "description")
	private String description;

	@Json(name = "created_at")
	private String createdAt;

	@Json(name = "is_translator")
	private boolean isTranslator;

	@Json(name = "profile_background_image_url_https")
	private String profileBackgroundImageUrlHttps;

	@Json(name = "protected")
	private boolean jsonMemberProtected;

	@Json(name = "screen_name")
	private String screenName;

	@Json(name = "id_str")
	private String idStr;

	@Json(name = "profile_link_color")
	private String profileLinkColor;

	@Json(name = "is_translation_enabled")
	private boolean isTranslationEnabled;

	@Json(name = "translator_type")
	private String translatorType;

	@Json(name = "id")
	private long id;

	@Json(name = "geo_enabled")
	private boolean geoEnabled;

	@Json(name = "profile_background_color")
	private String profileBackgroundColor;

	@Json(name = "lang")
	private String lang;

	@Json(name = "has_extended_profile")
	private boolean hasExtendedProfile;

	@Json(name = "profile_sidebar_border_color")
	private String profileSidebarBorderColor;

	@Json(name = "profile_text_color")
	private String profileTextColor;

	@Json(name = "verified")
	private boolean verified;

	@Json(name = "profile_image_url")
	private String profileImageUrl;

	@Json(name = "time_zone")
	private Object timeZone;

	@Json(name = "url")
	private Object url;

	@Json(name = "contributors_enabled")
	private boolean contributorsEnabled;

	@Json(name = "profile_background_tile")
	private boolean profileBackgroundTile;

	@Json(name = "profile_banner_url")
	private String profileBannerUrl;

	@Json(name = "entities")
	private Entities entities;

	@Json(name = "statuses_count")
	private int statusesCount;

	@Json(name = "follow_request_sent")
	private Object followRequestSent;

	@Json(name = "followers_count")
	private int followersCount;

	@Json(name = "profile_use_background_image")
	private boolean profileUseBackgroundImage;

	@Json(name = "default_profile")
	private boolean defaultProfile;

	@Json(name = "following")
	private Object following;

	@Json(name = "name")
	private String name;

	@Json(name = "location")
	private String location;

	@Json(name = "profile_sidebar_fill_color")
	private String profileSidebarFillColor;

	@Json(name = "notifications")
	private Object notifications;

	public void setUtcOffset(Object utcOffset){
		this.utcOffset = utcOffset;
	}

	public Object getUtcOffset(){
		return utcOffset;
	}

	public void setFriendsCount(int friendsCount){
		this.friendsCount = friendsCount;
	}

	public int getFriendsCount(){
		return friendsCount;
	}

	public void setProfileImageUrlHttps(String profileImageUrlHttps){
		this.profileImageUrlHttps = profileImageUrlHttps;
	}

	public String getProfileImageUrlHttps(){
		return profileImageUrlHttps;
	}

	public void setListedCount(int listedCount){
		this.listedCount = listedCount;
	}

	public int getListedCount(){
		return listedCount;
	}

	public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl){
		this.profileBackgroundImageUrl = profileBackgroundImageUrl;
	}

	public String getProfileBackgroundImageUrl(){
		return profileBackgroundImageUrl;
	}

	public void setDefaultProfileImage(boolean defaultProfileImage){
		this.defaultProfileImage = defaultProfileImage;
	}

	public boolean isDefaultProfileImage(){
		return defaultProfileImage;
	}

	public void setFavouritesCount(int favouritesCount){
		this.favouritesCount = favouritesCount;
	}

	public int getFavouritesCount(){
		return favouritesCount;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIsTranslator(boolean isTranslator){
		this.isTranslator = isTranslator;
	}

	public boolean isIsTranslator(){
		return isTranslator;
	}

	public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps){
		this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
	}

	public String getProfileBackgroundImageUrlHttps(){
		return profileBackgroundImageUrlHttps;
	}

	public void setJsonMemberProtected(boolean jsonMemberProtected){
		this.jsonMemberProtected = jsonMemberProtected;
	}

	public boolean isJsonMemberProtected(){
		return jsonMemberProtected;
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

	public void setProfileLinkColor(String profileLinkColor){
		this.profileLinkColor = profileLinkColor;
	}

	public String getProfileLinkColor(){
		return profileLinkColor;
	}

	public void setIsTranslationEnabled(boolean isTranslationEnabled){
		this.isTranslationEnabled = isTranslationEnabled;
	}

	public boolean isIsTranslationEnabled(){
		return isTranslationEnabled;
	}

	public void setTranslatorType(String translatorType){
		this.translatorType = translatorType;
	}

	public String getTranslatorType(){
		return translatorType;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setGeoEnabled(boolean geoEnabled){
		this.geoEnabled = geoEnabled;
	}

	public boolean isGeoEnabled(){
		return geoEnabled;
	}

	public void setProfileBackgroundColor(String profileBackgroundColor){
		this.profileBackgroundColor = profileBackgroundColor;
	}

	public String getProfileBackgroundColor(){
		return profileBackgroundColor;
	}

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	public void setHasExtendedProfile(boolean hasExtendedProfile){
		this.hasExtendedProfile = hasExtendedProfile;
	}

	public boolean isHasExtendedProfile(){
		return hasExtendedProfile;
	}

	public void setProfileSidebarBorderColor(String profileSidebarBorderColor){
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	public String getProfileSidebarBorderColor(){
		return profileSidebarBorderColor;
	}

	public void setProfileTextColor(String profileTextColor){
		this.profileTextColor = profileTextColor;
	}

	public String getProfileTextColor(){
		return profileTextColor;
	}

	public void setVerified(boolean verified){
		this.verified = verified;
	}

	public boolean isVerified(){
		return verified;
	}

	public void setProfileImageUrl(String profileImageUrl){
		this.profileImageUrl = profileImageUrl;
	}

	public String getProfileImageUrl(){
		return profileImageUrl;
	}

	public void setTimeZone(Object timeZone){
		this.timeZone = timeZone;
	}

	public Object getTimeZone(){
		return timeZone;
	}

	public void setUrl(Object url){
		this.url = url;
	}

	public Object getUrl(){
		return url;
	}

	public void setContributorsEnabled(boolean contributorsEnabled){
		this.contributorsEnabled = contributorsEnabled;
	}

	public boolean isContributorsEnabled(){
		return contributorsEnabled;
	}

	public void setProfileBackgroundTile(boolean profileBackgroundTile){
		this.profileBackgroundTile = profileBackgroundTile;
	}

	public boolean isProfileBackgroundTile(){
		return profileBackgroundTile;
	}

	public void setProfileBannerUrl(String profileBannerUrl){
		this.profileBannerUrl = profileBannerUrl;
	}

	public String getProfileBannerUrl(){
		return profileBannerUrl;
	}

	public void setEntities(Entities entities){
		this.entities = entities;
	}

	public Entities getEntities(){
		return entities;
	}

	public void setStatusesCount(int statusesCount){
		this.statusesCount = statusesCount;
	}

	public int getStatusesCount(){
		return statusesCount;
	}

	public void setFollowRequestSent(Object followRequestSent){
		this.followRequestSent = followRequestSent;
	}

	public Object getFollowRequestSent(){
		return followRequestSent;
	}

	public void setFollowersCount(int followersCount){
		this.followersCount = followersCount;
	}

	public int getFollowersCount(){
		return followersCount;
	}

	public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage){
		this.profileUseBackgroundImage = profileUseBackgroundImage;
	}

	public boolean isProfileUseBackgroundImage(){
		return profileUseBackgroundImage;
	}

	public void setDefaultProfile(boolean defaultProfile){
		this.defaultProfile = defaultProfile;
	}

	public boolean isDefaultProfile(){
		return defaultProfile;
	}

	public void setFollowing(Object following){
		this.following = following;
	}

	public Object getFollowing(){
		return following;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setProfileSidebarFillColor(String profileSidebarFillColor){
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	public String getProfileSidebarFillColor(){
		return profileSidebarFillColor;
	}

	public void setNotifications(Object notifications){
		this.notifications = notifications;
	}

	public Object getNotifications(){
		return notifications;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"utc_offset = '" + utcOffset + '\'' + 
			",friends_count = '" + friendsCount + '\'' + 
			",profile_image_url_https = '" + profileImageUrlHttps + '\'' + 
			",listed_count = '" + listedCount + '\'' + 
			",profile_background_image_url = '" + profileBackgroundImageUrl + '\'' + 
			",default_profile_image = '" + defaultProfileImage + '\'' + 
			",favourites_count = '" + favouritesCount + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",is_translator = '" + isTranslator + '\'' + 
			",profile_background_image_url_https = '" + profileBackgroundImageUrlHttps + '\'' + 
			",protected = '" + jsonMemberProtected + '\'' + 
			",screen_name = '" + screenName + '\'' + 
			",id_str = '" + idStr + '\'' + 
			",profile_link_color = '" + profileLinkColor + '\'' + 
			",is_translation_enabled = '" + isTranslationEnabled + '\'' + 
			",translator_type = '" + translatorType + '\'' + 
			",id = '" + id + '\'' + 
			",geo_enabled = '" + geoEnabled + '\'' + 
			",profile_background_color = '" + profileBackgroundColor + '\'' + 
			",lang = '" + lang + '\'' + 
			",has_extended_profile = '" + hasExtendedProfile + '\'' + 
			",profile_sidebar_border_color = '" + profileSidebarBorderColor + '\'' + 
			",profile_text_color = '" + profileTextColor + '\'' + 
			",verified = '" + verified + '\'' + 
			",profile_image_url = '" + profileImageUrl + '\'' + 
			",time_zone = '" + timeZone + '\'' + 
			",url = '" + url + '\'' + 
			",contributors_enabled = '" + contributorsEnabled + '\'' + 
			",profile_background_tile = '" + profileBackgroundTile + '\'' + 
			",profile_banner_url = '" + profileBannerUrl + '\'' + 
			",entities = '" + entities + '\'' + 
			",statuses_count = '" + statusesCount + '\'' + 
			",follow_request_sent = '" + followRequestSent + '\'' + 
			",followers_count = '" + followersCount + '\'' + 
			",profile_use_background_image = '" + profileUseBackgroundImage + '\'' + 
			",default_profile = '" + defaultProfile + '\'' + 
			",following = '" + following + '\'' + 
			",name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",profile_sidebar_fill_color = '" + profileSidebarFillColor + '\'' + 
			",notifications = '" + notifications + '\'' + 
			"}";
		}
}