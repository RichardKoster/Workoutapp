package com.richardkoster.workoutapp;

public class WorkoutValues {
	
	private int heartrate;
	private int calorie;
	private int speed;
	
	public WorkoutValues(int speed, int calorie, int heartrate){
		this.heartrate = heartrate;
		this.calorie = calorie;
		this.speed = speed;
	}
	
	int getSpeed(){
		return speed;
	}
	
	int getCalorie(){
		return calorie;
	}
	
	int getHeartrate(){
		return heartrate;
	}

}
