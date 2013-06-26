package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.Collection;
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

	private Map<Long, GameWall> walls = new LinkedHashMap<Long, GameWall>();
	private Map<Long, GameObject> objects = new LinkedHashMap<Long, GameObject>();
	private int maxUserWalls = 1;
	private int remainingFruits = Integer.MAX_VALUE;
	private int targetScore = Integer.MAX_VALUE;
	private int currentScore = 0;

	private int leveln = 0;
	private List<GameStateUpdater> updaters = new LinkedList<GameStateUpdater>();

	public GameState(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		updaters.add(new GameStateReaper());
		updaters.add(new BrainAttractionHandler(7500));
		updaters.add(new MovementHandler());
		updaters.add(new MixerHandler());
		updaters.add(new BrainEaterHandler());
		updaters.add(new WallReflectionHandler());
		updaters.add(new LineRemover());
		updaters.add(new GameOverUpdater());
	}

	public int getMaxUserWalls() {
		return maxUserWalls;
	}

	public void setMaxUserWalls(int maxUserWalls) {
		this.maxUserWalls = maxUserWalls;
	}

	public void newLevel(int leveln) {
		walls.clear();
		objects.clear();
		remainingFruits = 0;
		targetScore = 0;
		currentScore = 0;
		this.leveln = leveln;
	}

	public void update() {
		for (GameStateUpdater updater : updaters) {
			updater.update(this);
		}
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

	public long addObject(GameGraphics graphics, GamePosition position,
			GameObjectType type) {
		GameObject gameObject = new GameObject(graphics, position, type);
		objects.put(gameObject.getId(), gameObject);
		return gameObject.getId();
	}

	public long addWall(GamePosition startPosition, GameWallType type) {
		GameWall gameWall = new GameWall(startPosition, startPosition, type);
		walls.put(gameWall.getId(), gameWall);
		return gameWall.getId();
	}

	public boolean inSideGame(GamePosition gamePosition) {
		return gamePosition.getxPosition() > 0
				&& gamePosition.getxPosition() < xSize
				&& gamePosition.getyPosition() > 0
				&& gamePosition.getyPosition() < ySize;
	}

	public void increaseScore() {
		this.currentScore++;
		if (this.currentScore >=this.targetScore) {
			leveln+=1;
			setLevel(leveln);
		}
	}
	
	public void setLevel(int level){
		GameLevel.buildLevel(level, this);

	}

	public int getRemainingFruits() {
		return remainingFruits;
	}

	public int getTargetScore() {
		return targetScore;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public int getLeveln() {
		return leveln;
	}

	public void setRemainingFruits(int remainingFruits) {
		this.remainingFruits = remainingFruits;
	}

	public void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}

	public void setLeveln(int leveln) {
		this.leveln = leveln;
	}
	
	

	
}
