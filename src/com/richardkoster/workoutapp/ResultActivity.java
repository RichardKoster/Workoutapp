package com.richardkoster.workoutapp;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		TextView headerTV = (TextView)findViewById(R.id.header_textview);
		
		headerTV.setText(R.string.resulttitle);
	}
}
