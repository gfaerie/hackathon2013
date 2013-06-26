package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.HashSet;
import java.util.Set;

import android.util.Log;

public class GameStateReaper {

	public void reapObjects(GameState gameState) {
		Set<Long> toRemove = new HashSet<Long>();
		for (GameObject gameObject : gameState.getObjects()) {
			if (!gameState.inSideGame(gameObject.getPosition())) {
				toRemove.add(gameObject.getId());
			}
		}
		for (Long id : toRemove) {
			Log.d("fruits", "Removing object "+gameState.getObject(id));
			gameState.deleteObject(id);
		}
	}

}
