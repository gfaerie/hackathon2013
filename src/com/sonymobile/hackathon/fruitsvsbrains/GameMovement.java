package com.sonymobile.hackathon.fruitsvsbrains;

public class GameMovement {

	private int xSpeed;
	private int ySpeed;
	private int xAcceleration;
	private int yAcceleration;
	public GameMovement(int xSpeed, int ySpeed, int xAcceleration,
			int yAcceleration) {

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.xAcceleration = xAcceleration;
		this.yAcceleration = yAcceleration;
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public int getxAcceleration() {
		return xAcceleration;
	}
	public int getyAcceleration() {
		return yAcceleration;
	}
	
	
	

}
