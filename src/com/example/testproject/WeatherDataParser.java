package com.example.testproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataParser {

	public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex) throws JSONException{
		JSONObject json=new JSONObject(weatherJsonStr);
		
		JSONObject objectForTheDay = (JSONObject)json.getJSONArray("list").get(dayIndex);
		
		JSONObject tempObject = objectForTheDay.getJSONObject("temp");
		 
		double max = tempObject.getDouble("max");
		
		return  max;
	}
}
 