package com.richardkoster.workoutapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OverviewActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		TextView headerTV = (TextView)findViewById(R.id.header_textview);
		
		headerTV.setText(R.string.overviewtitle);
	}
	
	
}
