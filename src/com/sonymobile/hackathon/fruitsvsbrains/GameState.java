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
	private GameStateReaper reaper;
	private BrainAttractionHandler brainAttractionHandler;
	private MovementHandler movementHandler;
	private MixerHandler mixerHandler;
	private WallReflectionHandler wallReflectionHandler;

	public GameState(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.reaper = new GameStateReaper();
		this.brainAttractionHandler = new BrainAttractionHandler(7500);
		this.movementHandler = new MovementHandler();
		this.mixerHandler = new MixerHandler();
		this.wallReflectionHandler= new WallReflectionHandler();
	}

	public void update() {
		reaper.reapObjects(this);
		brainAttractionHandler.doBrainAttraction(this);
		movementHandler.moveObjects(this);
		mixerHandler.handleMixer(this);
		wallReflectionHandler.handleCollisions(this);
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
}
