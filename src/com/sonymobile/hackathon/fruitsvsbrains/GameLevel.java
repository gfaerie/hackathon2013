package com.sonymobile.hackathon.fruitsvsbrains;

public class GameLevel {

	public static void addWall(GameState state, GamePosition startPosition,
			GamePosition endPosition) {
		long id = state.addWall(startPosition, GameWallType.PREMANENT);
		state.getWall(id).setEnd(endPosition);
	}

	public static void addEdgeWalls(GameState state) {
		float boardSize = Math.max(state.getxSize(),
				state.getySize());
		float padding = boardSize / 100f;
		GameLevel.addWall(state, new GamePosition(padding, padding), new GamePosition(state.getxSize() - padding, padding));
		GameLevel.addWall(state, new GamePosition(padding, padding), new GamePosition(padding, state.getySize() - padding));
		GameLevel.addWall(state, new GamePosition(state.getxSize() - padding, padding), new GamePosition(state.getxSize() - padding, state.getySize() - padding));
		GameLevel.addWall(state, new GamePosition(padding, state.getySize() - padding), new GamePosition(state.getxSize() - padding, state.getySize() - padding));
	}

	public static void buildLevel(int leveln, GameState state) {
		float canvasWidth = state.getxSize();
		float canvasHeight = state.getySize();

		state.newLevel(leveln);
		GameLevel.addEdgeWalls(state);
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
			state.setMaxUserWalls(2);
			state.setTargetScore(3);
			state.setRemainingFruits(10);
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
			state.setMaxUserWalls(2);
			state.setTargetScore(5);
			state.setRemainingFruits(10);
			break;
		}
	}
}
