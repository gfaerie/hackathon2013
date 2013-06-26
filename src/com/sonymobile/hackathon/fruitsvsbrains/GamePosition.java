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
	
	
	public double dotProduct(GamePosition gamePosition){
		return this.xPosition*gamePosition.getxPosition()+this.yPosition*gamePosition.getyPosition();
	}
	
	public GamePosition add(GamePosition gamePosition){
		return new GamePosition(this.xPosition+gamePosition.getxPosition(),this.yPosition+gamePosition.getyPosition());
	}
	
	public GamePosition subtract(GamePosition gamePosition){
		return new GamePosition(this.xPosition-gamePosition.getxPosition(),this.yPosition-gamePosition.getyPosition());
	}
	
	public GamePosition scale(float scale){
		return new GamePosition(this.xPosition*scale,this.yPosition*scale);
	}
	


	@Override
	public String toString() {
		return "GamePosition [xPosition=" + xPosition + ", yPosition="
				+ yPosition + "]";
	}
	
	

}
