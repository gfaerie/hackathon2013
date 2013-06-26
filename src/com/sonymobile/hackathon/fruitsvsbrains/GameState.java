package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.Collection;
import java.util.LinkedHashMap;
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

	private Map<Long, GameWall> walls = new LinkedHashMap<Long, GameWall>();
	private Map<Long, GameObject> objects = new LinkedHashMap<Long, GameObject>();

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

	public Collection<GameWall> getWalls() {
		return walls.values();
	}

	public Collection<GameObject> getObjects() {
		return objects.values();
	}

	public void updateWallPosition(long id, GamePosition endPosition) {
		walls.get(id).setEnd(endPosition);
	}

	public void deleteWall(long id) {
		walls.remove(id);
	}

	public void deleteObject(long id) {
		objects.remove(id);
	}

	public GameWall getWall(long id) {
		return walls.get(id);
	}

	public GameObject getObject(long id) {
		return objects.get(id);
	}

	public long addWall(GamePosition startPosition) {
		GameWall gameWall = new GameWall(startPosition, startPosition);
		walls.put(gameWall.getId(), gameWall);
		return gameWall.getId();
	}

	public boolean inSideGame(GamePosition gamePosition) {
		return gamePosition.getxPosition() > 0
				&& gamePosition.getxPosition() < xSize
				&& gamePosition.getyPosition() > 0
				&& gamePosition.getyPosition() < ySize;
	}
}
