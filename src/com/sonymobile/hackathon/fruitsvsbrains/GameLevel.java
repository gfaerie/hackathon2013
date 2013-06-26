package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.ArrayList;

public class GameLevel {

	public static void buildLevel(int leveln, GameState state) {
		float canvasWidth = state.getxSize();
		float canvasHeight = state.getySize();

		state.newLevel(leveln);

		switch (leveln) {
		case 1:
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					3 * canvasWidth / 4, 3 * canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					canvasWidth / 4, 3 * canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					3 * canvasWidth / 4, canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.MIXER, new GamePosition(
					2 * canvasWidth / 4, 3 * canvasHeight / 4),
					GameObjectType.TARGET_CONTAINER);

			break;
		case 2:
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					2 * canvasWidth / 4, 2 * canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					canvasWidth / 4, 3 * canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(
					3 * canvasWidth / 4, canvasHeight / 4),
					GameObjectType.BRAIN);
			state.addObject(GameGraphics.MIXER, new GamePosition(
					3 * canvasWidth / 4, 3 * canvasHeight / 4),
					GameObjectType.TARGET_CONTAINER);

			break;
		}
	}
}
