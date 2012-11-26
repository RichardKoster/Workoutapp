package com.richardkoster.workoutapp;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeviceView extends LinearLayout {
	
	public DeviceView(Context context){
		super(context);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.device_layout, this);
	}
	
	public void setDevice (BluetoothDevice device){
		TextView deviceName = (TextView)findViewById(R.id.deviceName);
		deviceName.setText(device.getName());
	}
	
}

