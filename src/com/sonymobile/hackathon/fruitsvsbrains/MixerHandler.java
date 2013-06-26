package com.sonymobile.hackathon.fruitsvsbrains;

public class MixerHandler {
	public void handleMixer(GameState gameState) {
		GameObject mixer = null;
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getType() == GameObjectType.TARGET_CONTAINER)
				mixer = gameObject;
		}
		if (mixer == null)
			return;
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getPosition().distanceTo(mixer.getPosition()) < mixer.getGameGraphics().getSize())
				gameState.deleteObject(gameObject.getId());
		}
	}
}
