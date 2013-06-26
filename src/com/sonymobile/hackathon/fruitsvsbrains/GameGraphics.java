package com.sonymobile.hackathon.fruitsvsbrains;

public enum GameGraphics {
	APPLE(100,6),ORANGE(100,3),BANANA(100,9),BRAIN(100,0),MIXER(100,0);
	
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
