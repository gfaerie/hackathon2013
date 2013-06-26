package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedList;
import java.util.List;

public class MixerHandler  implements GameStateUpdater{
	public void update(GameState gameState) {
		List<GameObject> mixers = new LinkedList<GameObject>();
		List<GameObject> fruits = new LinkedList<GameObject>();
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getType() == GameObjectType.TARGET_CONTAINER) {
				mixers.add(gameObject);

			} else if (gameObject.getType() == GameObjectType.FRUIT) {
				fruits.add(gameObject);
			}
		}

		for (GameObject mixer : mixers) {
			for (GameObject fruit: fruits) {
				if (fruit.getPosition().distanceTo(mixer.getPosition()) < mixer.getGameGraphics().getSize()) {
					gameState.deleteObject(fruit.getId());
					gameState.increaseScore();
				}	
			}
		}
	}
}
