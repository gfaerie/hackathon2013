package com.sonymobile.hackathon.fruitsvsbrains;

public class GameMovement {

	private float xSpeed;
	private float ySpeed;
	public GameMovement(float xSpeed, float ySpeed) {

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public float getxSpeed() {
		return xSpeed;
	}
	public float getySpeed() {
		return ySpeed;
	}
	@Override
	public String toString() {
		return "GameMovement [xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + "]";
	}
	
	public void update(float x, float y){
		xSpeed+=x;
		ySpeed+=y;
	}
}
