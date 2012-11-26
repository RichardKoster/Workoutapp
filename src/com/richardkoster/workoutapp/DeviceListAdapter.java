package com.richardkoster.workoutapp;

import java.util.List;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
	
	private Context context;
	private List<BluetoothDevice> devices;
	
	public DeviceListAdapter(Context context, List<BluetoothDevice> devices) {
		super(context, R.layout.activity_device_list, devices);
		this.devices = devices;
		this.context = context;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		DeviceView deviceView = new DeviceView(context);
		deviceView.setDevice(devices.get(position));
		
		return deviceView;
		
	}
	
}

