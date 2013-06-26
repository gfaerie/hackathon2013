package com.sonymobile.hackathon.fruitsvsbrains;

public enum GameGraphics {
	APPLE(100),BRAIN(100),MIXER(100);
	
	private int size;

	private GameGraphics(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
	
	
}
