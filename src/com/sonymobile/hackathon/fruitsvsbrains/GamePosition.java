package com.sonymobile.hackathon.fruitsvsbrains;

public class GamePosition {
	private int xPosition;
	private int yPosition;

	public GamePosition(int xPosition, int yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public int distanceTo(GamePosition gamePosition){
		int xDiff =gamePosition.getxPosition()-xPosition;
		int yDiff =gamePosition.getyPosition()-yPosition;
		return (int) Math.round(Math.sqrt(xDiff*xDiff+yDiff*yDiff));
	}
	
	public void move(int x, int y){
		xPosition+=x;
		yPosition+=y;
	}

}
