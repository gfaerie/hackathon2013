package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.concurrent.atomic.AtomicLong;

public class GameWall {
private static final AtomicLong ID_COUNTER = new AtomicLong();
	
	private long id = ID_COUNTER.incrementAndGet();
	private GamePosition start;
	private GamePosition end;
	public GameWall(GamePosition start, GamePosition end) {
		this.start = start;
		this.end = end;
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
	
	
	
	
	
	
	
	
}
