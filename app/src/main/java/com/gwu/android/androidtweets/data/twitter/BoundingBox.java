package com.gwu.android.androidtweets.data.twitter;

import java.util.List;
import javax.annotation.Generated;
import com.squareup.moshi.Json;

@Generated("com.robohorse.robopojogenerator")
public class BoundingBox{

	@Json(name = "coordinates")
	private List<List<List<Double>>> coordinates;

	@Json(name = "type")
	private String type;

	public void setCoordinates(List<List<List<Double>>> coordinates){
		this.coordinates = coordinates;
	}

	public List<List<List<Double>>> getCoordinates(){
		return coordinates;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"BoundingBox{" + 
			"coordinates = '" + coordinates + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}