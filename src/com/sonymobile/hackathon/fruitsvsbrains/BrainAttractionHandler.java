package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedList;
import java.util.List;

public class BrainAttractionHandler implements GameStateUpdater{

	public double staticScaleFactor;

	public BrainAttractionHandler(double staticScaleFactor) {
		this.staticScaleFactor = staticScaleFactor;
	}

	public void update(GameState gameState) {
		List<GameObject> brains = new LinkedList<GameObject>();
		List<GameObject> fruits = new LinkedList<GameObject>();
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getType() == GameObjectType.BRAIN) {
				brains.add(gameObject);

			} else if (gameObject.getType() == GameObjectType.FRUIT) {
				fruits.add(gameObject);

			}
		}

		for (GameObject brain : brains) {
			for (GameObject fruit : fruits) {
				double distance = brain.getPosition().distanceTo(
						fruit.getPosition());
				double scaleFactor = staticScaleFactor / (distance * distance);
				double xAttraction = brain.getPosition().getxPosition()
						- fruit.getPosition().getxPosition();
				double yAttraction = brain.getPosition().getyPosition()
						- fruit.getPosition().getyPosition();
				double attractionSize = Math.sqrt(xAttraction * xAttraction
						+ yAttraction * yAttraction);
				if (fruit.getGameMovement() == null) {
					fruit.setGameMovement(new GameMovement(
							(float) (xAttraction / attractionSize * scaleFactor),
							(float) (yAttraction / attractionSize * scaleFactor)));
				} else {
					fruit.getGameMovement()
							.update((float) (xAttraction / attractionSize * scaleFactor),
									(float) (yAttraction / attractionSize * scaleFactor));

				}

			}
		}

	}
}
