package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.concurrent.atomic.AtomicLong;

public class GameWall {
private static final AtomicLong ID_COUNTER = new AtomicLong();
	
	private final long id = ID_COUNTER.incrementAndGet();
	private GamePosition start;
	private GamePosition end;
	private GameWallType type;
	
	public GameWall(GamePosition start, GamePosition end,
			GameWallType type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}



	public GameWallType getType() {
		return type;
	}



	public void setType(GameWallType type) {
		this.type = type;
	}



	public GamePosition getStart() {
		return start;
	}
	public GamePosition getEnd() {
		return end;
	}
	public void setEnd(GamePosition end) {
		this.end = end;
	}
	public long getId() {
		return id;
	}
	
	public int getLength(){
		return start.distanceTo(end);
	}
	
	
	
	
	
	
}
