package com.sonymobile.hackathon.fruitsvsbrains;

public class MixerHandler {
	private GameObject getMixer(GameState gameState) {
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getType() == GameObjectType.TARGET_CONTAINER)
				return gameObject;
		}
		return null;
	}
	public void handleMixer(GameState gameState) {
		GameObject mixer = getMixer(gameState);
		if (mixer == null)
			return;
		for (GameObject gameObject : gameState.getObjects()) {
			if (gameObject.getPosition().distanceTo(mixer.getPosition()) < mixer.getGameGraphics().getSize())
				gameState.deleteObject(gameObject.getId());
		}
	}
}
