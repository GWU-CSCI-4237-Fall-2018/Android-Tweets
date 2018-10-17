package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class Place{

	@Json(name = "country_code")
	private String countryCode;

	@Json(name = "country")
	private String country;

	@Json(name = "contained_within")
	private List<Object> containedWithin;

	@Json(name = "full_name")
	private String fullName;

	@Json(name = "bounding_box")
	private BoundingBox boundingBox;

	@Json(name = "place_type")
	private String placeType;

	@Json(name = "name")
	private String name;

	@Json(name = "attributes")
	private Attributes attributes;

	@Json(name = "id")
	private String id;

	@Json(name = "url")
	private String url;

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setContainedWithin(List<Object> containedWithin){
		this.containedWithin = containedWithin;
	}

	public List<Object> getContainedWithin(){
		return containedWithin;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setBoundingBox(BoundingBox boundingBox){
		this.boundingBox = boundingBox;
	}

	public BoundingBox getBoundingBox(){
		return boundingBox;
	}

	public void setPlaceType(String placeType){
		this.placeType = placeType;
	}

	public String getPlaceType(){
		return placeType;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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
			"Place{" + 
			"country_code = '" + countryCode + '\'' + 
			",country = '" + country + '\'' + 
			",contained_within = '" + containedWithin + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",bounding_box = '" + boundingBox + '\'' + 
			",place_type = '" + placeType + '\'' + 
			",name = '" + name + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}