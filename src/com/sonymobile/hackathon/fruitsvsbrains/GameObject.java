package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.concurrent.atomic.AtomicLong;

public class GameObject {
	private static final AtomicLong ID_COUNTER = new AtomicLong();
	
	private long id = ID_COUNTER.incrementAndGet();
	private GameGraphics gameGraphics;
	private GamePosition position;
	private GameObjectType type;
	
	public GameObject(GameGraphics gameGraphics, GamePosition position,
			GameObjectType type) {
		this.gameGraphics = gameGraphics;
		this.position = position;
		this.type = type;
	}

	public GameObjectType getType() {
		return type;
	}
	
	public GameGraphics getGameGraphics() {
		return gameGraphics;
	}

	public void setGameGraphics(GameGraphics gameGraphics) {
		this.gameGraphics = gameGraphics;
	}

	public GamePosition getPosition() {
		return position;
	}

	public void setPosition(GamePosition position) {
		this.position = position;
	}

	public long getId() {
		return id;
	}
	
	
	
	
	
	
	
}
