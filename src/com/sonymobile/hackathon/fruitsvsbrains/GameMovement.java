package com.sonymobile.hackathon.fruitsvsbrains;

public class GameMovement {

	private int xSpeed;
	private int ySpeed;
	public GameMovement(int xSpeed, int ySpeed) {

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
}
