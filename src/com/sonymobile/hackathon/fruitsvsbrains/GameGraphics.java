package com.sonymobile.hackathon.fruitsvsbrains;

public enum GameGraphics {
	APPLE(100,5),ORANGE(100,4),BANANA(100,7),BRAIN(100,0),MIXER(100,0);
	
	private int size;
	private int speed;

	private GameGraphics(int size, int speed) {
		this.size = size;
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public int getSize() {
		return size;
	}
	
	
}
