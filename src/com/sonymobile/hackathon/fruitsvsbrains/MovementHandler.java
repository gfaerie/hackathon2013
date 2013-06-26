package com.sonymobile.hackathon.fruitsvsbrains;

public class MovementHandler implements GameStateUpdater {

	public void update(GameState gameState) {
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getGameMovement() != null) {
				gameObject.getPosition().move(
						gameObject.getGameMovement().getxSpeed(),
						gameObject.getGameMovement().getySpeed());
			}
		}
	}

}
