package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedList;
import java.util.List;

public class BrainEaterHandler  implements GameStateUpdater{

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

		for (GameObject fruit : fruits) {
			for (GameObject brain: brains) {
				if (fruit.getPosition().distanceTo(brain.getPosition()) < brain.getGameGraphics().getSize())
					gameState.deleteObject(fruit.getId());
			}
		}
	}
}
