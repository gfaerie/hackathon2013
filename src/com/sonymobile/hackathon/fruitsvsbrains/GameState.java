package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedList;
import java.util.List;

/**
 * //TODO : Mans
 * 
 * @author 23060208
 * 
 */
public class GameState {

	private final int xSize;
	private final int ySize;
	
	private List<GameWall> staticWalls = new LinkedList<GameWall>();
	private List<GameWall> mobileWalls = new LinkedList<GameWall>();

	public GameState(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public List<GameWall> getStaticWalls() {
		return staticWalls;
	}

	public List<GameWall> getMobileWalls() {
		return mobileWalls;
	}
	
	public void updateLastWall(GamePosition endPosition){
		if(!mobileWalls.isEmpty()){
			mobileWalls.get(0).setEnd(endPosition);
		}
	}
	
	public void addMobileWall(GamePosition startPosition){
		mobileWalls.add(new GameWall(startPosition, startPosition));
	}
}
