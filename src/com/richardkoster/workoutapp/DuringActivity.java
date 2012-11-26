package com.richardkoster.workoutapp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.util.Config;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class DuringActivity extends Activity implements Runnable {
	
	BluetoothSocket sock;
	boolean connected = false;
	boolean listening = false;
	BluetoothAdapter adapter;
	InputStream in;
	int read = 0;
	byte[] buffer = new byte[256];
	
	BluetoothDevice rower;
	
	TextView timeTextView;
	TextView distanceTextView;
	TextView speedTextView;
	TextView calorieTextView;
	TextView heartrateTextView;
	String resultString;
	
	WorkoutValues wValues;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_during);
		

		
		Thread thread = new Thread(this);
		
		
		
		TextView headerTV = (TextView)findViewById(R.id.header_textview);
		
		adapter = BluetoothAdapter.getDefaultAdapter();
		
		
		try {
			connectToDevice();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		if(connected){
			headerTV.setText(rower.getName());
			try {
				startListening();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			Log.d("+++Socket", "not connected");
		}
		
		if(listening){
			thread.start();
		}
		else{
			Log.d("+++Listen", "not listening");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_device_list, menu);
		return true;
	}
	
	public void connectToDevice() throws Exception{
		if(connected){
			return;
		}
		rower = adapter.getRemoteDevice("BD:B2:03:00:55:11");
		Method m = rower.getClass().getMethod("createRfcommSocket", new Class[] {int.class} );
		sock = (BluetoothSocket)m.invoke(rower, Integer.valueOf(1));
		Log.d("+++Socket", "connecting");
		sock.connect();
		Log.d("+++Socket", "connected");
		connected = true;
	}
	
	public void startListening() throws Exception{
		in = sock.getInputStream();
        Log.d("ZeeTest", "++++ Listening...");
        listening = true;
	}
	
	void getValues(String jsonString, int position){
		JSONOutput output = new JSONOutput(jsonString);
		try {
			 wValues = output.getWorkoutvalues(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void putInTextView(){
		if(wValues != null){
			speedTextView = (TextView)findViewById(R.id.speedTextView);
			speedTextView.setText(Integer.toString(wValues.getSpeed()));
			calorieTextView = (TextView)findViewById(R.id.calorieTextView);
			calorieTextView.setText(Integer.toString(wValues.getCalorie()));
			heartrateTextView = (TextView)findViewById(R.id.heartrateTextView);
			heartrateTextView.setText(Integer.toString(wValues.getHeartrate()));
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			read = in.read(buffer);
			StringBuilder buf = new StringBuilder();
			for(int i=0; i<read; i++){
				int b = buffer[i] & 0xff;
				if (b < 0x10){
					buf.append("0");
				}
			}
			
			byte[] result = new byte[read];
			
			for(int j = 0; j<read;j++){
            	result[j]=buffer[j];
            }
			
			resultString = new String (result);
			Log.d("+++Result", resultString);
			
			getValues(resultString,0);
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					putInTextView();
					
				}
			});
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
