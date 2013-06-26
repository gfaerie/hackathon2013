package com.sonymobile.hackathon.fruitsvsbrains;

public class GamePosition {
	private float xPosition;
	private float yPosition;

	public GamePosition(float xPosition, float yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public float getxPosition() {
		return xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}
	
	public int distanceTo(GamePosition gamePosition){
		float xDiff =gamePosition.getxPosition()-xPosition;
		float yDiff =gamePosition.getyPosition()-yPosition;
		return (int) Math.round(Math.sqrt(xDiff*xDiff+yDiff*yDiff));
	}
	
	public void move(float x, float y){
		xPosition+=x;
		yPosition+=y;
	}

	@Override
	public String toString() {
		return "GamePosition [xPosition=" + xPosition + ", yPosition="
				+ yPosition + "]";
	}
	
	

}
