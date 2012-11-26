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
	Array values;
	
	public JSONOutput(String jsonString){
		this.jsonString = jsonString;
	}
	
	private JSONObject primaryObject(){
		
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	private JSONArray primaryArray(String arrayName){
		if(object != null){
			try {
				array = primaryObject().getJSONArray(arrayName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return array;
	}
	
	public Array getValues(int position, String arrayName) throws JSONException{
		JSONArray detailedarray = primaryArray(arrayName);
		detailedobject = detailedarray.getJSONObject(position);
		
		if(detailedobject != null){
			int speed = detailedobject.getInt("speed");
			
			values.setInt(values, position, speed) = speed;
			
			
		}
		
		return values;
	}
	
	
	
}
