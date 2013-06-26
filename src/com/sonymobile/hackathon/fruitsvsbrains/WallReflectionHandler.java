package com.sonymobile.hackathon.fruitsvsbrains;

import android.util.Log;

public class WallReflectionHandler  implements GameStateUpdater{

	public void update(GameState gameState) {
		for (GameObject gameObject : gameState.getObjects()) {
			if (isReflectable(gameObject)) {
				for (GameWall gameWall : gameState.getWalls()) {
					if (isReflectable(gameWall)
							&& isWithinCollisionArea(gameObject, gameWall)
							&& isWithinDistance(gameObject, gameWall)) {
						reflectInWall(gameObject, gameWall);
					}
				}
			}
		}
	}

	private boolean isReflectable(GameObject gameObject) {
		return gameObject.getType() == GameObjectType.FRUIT;
	}

	private boolean isReflectable(GameWall gameWall) {
		return gameWall.getType() != GameWallType.IN_PROGRESS;
	}

	private boolean isWithinDistance(GameObject gameObject, GameWall gameWall) {
		double distance = getDistanceToWall(gameObject, gameWall);
		Log.d("fruit", "Distance to line is " + distance);
		return distance < gameObject.getGameGraphics().getSize()
				&& isMovingTowardsWall(distance, gameObject, gameWall);
	}

	private double getDistanceToWall(GameObject gameObject, GameWall gameWall) {
		return getDistanceToLine(gameObject.getPosition(), gameWall.getStart(),
				gameWall.getEnd());
	}

	private double getDistanceToLine(GamePosition lineStart,
			GamePosition lineEnd, GamePosition point) {
		double lineLength = lineEnd.distanceTo(lineStart);
		double t = point.subtract(lineStart).dotProduct(
				lineEnd.subtract(lineStart))
				/ (lineLength * lineLength);
		GamePosition projection = lineStart.add(lineEnd.subtract(lineStart)
				.scale((float) t));
		return point.distanceTo(projection);
	}

	private boolean isWithinCollisionArea(GameObject gameObject,
			GameWall gameWall) {
		double wallLength = gameWall.getLength()
				+ gameObject.getGameGraphics().getSize();
		return gameObject.getPosition().distanceTo(gameWall.getStart()) < wallLength
				&& gameObject.getPosition().distanceTo(gameWall.getEnd()) < wallLength;
	}

	private boolean isMovingTowardsWall(double currentDistance,
			GameObject gameObject, GameWall gameWall) {
		if (gameObject.getGameMovement() != null) {
			double next = getDistanceToLine(
					new GamePosition(
							(float) (gameObject.getPosition().getxPosition() + 0.1 * gameObject
									.getGameMovement().getxSpeed()),
							(float) (gameObject.getPosition().getyPosition() + 0.1 * gameObject
									.getGameMovement().getySpeed())),
					gameWall.getStart(), gameWall.getEnd());
			Log.d("fruit", "isMovingTowardsWall: " + (next < currentDistance));
			return next < currentDistance;
		}
		return false;

	}

	private void reflectInWall(GameObject gameObject, GameWall gameWall) {
		if (gameObject.getGameMovement() != null) {

			GamePosition origin = new GamePosition(0, 0);

			GamePosition speedVector = new GamePosition(gameObject
					.getGameMovement().getxSpeed(), gameObject
					.getGameMovement().getySpeed());
			GamePosition lineVector = new GamePosition(gameWall.getEnd()
					.getxPosition() - gameWall.getStart().getxPosition(),
					gameWall.getEnd().getyPosition()
							- gameWall.getStart().getyPosition());

			double normalizedVector = dotProduct(origin, lineVector, origin,
					lineVector);
			double dot = dotProduct(origin, speedVector, origin, lineVector);
			float projectionScale = (float) (dot / normalizedVector);
			float newXspeed = (float) (2.0 * projectionScale
					* lineVector.getxPosition() - speedVector.getxPosition());
			float newYspeed = (float) (2.0 * projectionScale
					* lineVector.getyPosition() - speedVector.getyPosition());
			gameObject.setGameMovement(new GameMovement((float) newXspeed,
					(float) newYspeed));
			Log.d("fruits", "Normalize " + normalizedVector);
			Log.d("fruits", "Dor product " + dot);
			Log.d("fruits", "Speed vector " + speedVector);
			Log.d("fruits", "Line vector " + lineVector);
			Log.d("fruits", "Projection " + projectionScale);
			Log.d("fruits", "Reflected object " + gameObject + " in "
					+ gameWall);

		}
	}

	private double dotProduct(GamePosition startOne, GamePosition endOne,
			GamePosition startTwo, GamePosition endTwo) {
		double[] matrixOne = new double[2];
		double[] matrixTwo = new double[2];
		matrixOne[0] = endOne.getxPosition() - startOne.getxPosition();
		matrixOne[1] = endOne.getyPosition() - startOne.getyPosition();
		matrixTwo[0] = endTwo.getxPosition() - startTwo.getxPosition();
		matrixTwo[1] = endTwo.getyPosition() - startTwo.getyPosition();
		double dot = matrixOne[0] * matrixTwo[0] + matrixOne[1] * matrixTwo[1];
		return dot;
	}

	

}
