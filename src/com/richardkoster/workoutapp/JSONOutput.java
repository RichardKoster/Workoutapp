package com.richardkoster.workoutapp;

import java.lang.reflect.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class JSONOutput {
	
	private String jsonString;
	private JSONObject object;
	private JSONArray array;
	JSONObject detailedobject;
	
	public JSONOutput(String jsonString){
		this.jsonString = jsonString;
	}
	
	private JSONArray detailedArray(){
		
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			array = object.getJSONArray("values");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	
	
	
	public WorkoutValues getWorkoutvalues(int position) throws JSONException{
		JSONArray detailedarray = detailedArray();
		detailedobject = detailedarray.getJSONObject(position);
		
		WorkoutValues wValues = null;
		
		if(detailedobject != null){
			int speed = detailedobject.getInt("speed");
			int calorie = detailedobject.getInt("calorie");
			int heartRate = detailedobject.getInt("heartrate");
			wValues = new WorkoutValues(speed, calorie, heartRate);
		}
		
		return wValues;
	}
	
	
	
}
