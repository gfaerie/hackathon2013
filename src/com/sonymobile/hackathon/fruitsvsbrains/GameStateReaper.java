package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.HashSet;
import java.util.Set;

public class GameStateReaper {

	public void reapObjects(GameState gameState) {
		Set<Long> toRemove = new HashSet<Long>();
		for (GameObject gameObject : gameState.getObjects()) {
			if (!gameState.inSideGame(gameObject.getPosition())) {
				toRemove.add(gameObject.getId());
			}
		}
		for (Long id : toRemove) {
			gameState.deleteObject(id);
		}
	}

}
