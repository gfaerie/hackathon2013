package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * //TODO : Mans
 * 
 * @author 23060208
 * 
 */
public class GameState {

	private final int xSize;
	private final int ySize;
	
	
	
	private Map<Long,GameWall> walls = new LinkedHashMap<Long, GameWall>();
	private Map<Long,GameObject> objects = new LinkedHashMap<Long, GameObject>();

	
	
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

	public Map<Long, GameWall> getWalls() {
		return walls;
	}

	public Map<Long, GameObject> getObjects() {
		return objects;
	}

	public void updateWallPosition(long id,GamePosition endPosition){
			walls.get(id).setEnd(endPosition);
		
	}
	
	public long addMobileWall(GamePosition startPosition){
		GameWall gameWall = new GameWall(startPosition, startPosition);
		walls.put(gameWall.getId(), gameWall);
		return gameWall.getId();
	}
}
