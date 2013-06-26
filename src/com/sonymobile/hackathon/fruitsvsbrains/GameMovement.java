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
}
