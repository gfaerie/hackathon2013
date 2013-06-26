package com.sonymobile.hackathon.fruitsvsbrains;

import android.util.Log;

public class CollisionHandler {

	public void handleCollisions(GameState gameState) {
		for (GameObject gameObject : gameState.getObjects()) {
			if (isReflectable(gameObject)) {
				for (GameWall gameWall : gameState.getWalls()) {
					if (isReflectable(gameWall)
							&& isWithinDistance(gameObject, gameWall)
							&& isMovingTowardsWall(gameObject, gameWall)) {
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
		return gameWall.getType() == GameWallType.DONE;
	}

	private boolean isWithinDistance(GameObject gameObject, GameWall gameWall) {
		return getDistanceToWall(gameObject, gameWall) < gameObject
				.getGameGraphics().getSize();
	}

	private double getDistanceToWall(GameObject gameObject, GameWall gameWall) {
		return getDistanceToLine(gameObject.getPosition(), gameWall.getStart(),
				gameWall.getEnd());
	}

	private double getDistanceToLine(GamePosition point,
			GamePosition lineStart, GamePosition lineEnd) {
		double dist = crossProduct(lineStart, lineEnd, point)
				/ lineStart.distanceTo(lineEnd);
		double dot1 = dotProduct(lineStart, lineEnd, point);
		if (dot1 > 0) {
			return lineEnd.distanceTo(point);
		}
		double dot2 = dotProduct(lineEnd, lineStart, point);
		if (dot2 > 0) {
			return lineStart.distanceTo(point);
		}
		return Math.abs(dist);
	}

	private boolean isMovingTowardsWall(GameObject gameObject, GameWall gameWall) {
		if (gameObject.getGameMovement() != null) {
			double currentDistance = getDistanceToWall(gameObject, gameWall);
			double next = getDistanceToLine(
					new GamePosition(
							(float) (gameObject.getPosition().getxPosition() + 0.01 * gameObject
									.getGameMovement().getxSpeed()),
							(float) (gameObject.getPosition().getyPosition() + 0.01 * gameObject
									.getGameMovement().getySpeed())),
					gameWall.getStart(), gameWall.getEnd());
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

			double projectionScale = dotProduct(origin, lineVector, origin,
					speedVector)
					/ dotProduct(origin, lineVector, origin, lineVector);
			double newXspeed = 2.0 * projectionScale
					* lineVector.getxPosition() - speedVector.getxPosition();
			double newYspeed = 2.0 * projectionScale
					* lineVector.getyPosition() - speedVector.getyPosition();
			gameObject.setGameMovement(new GameMovement((float) newXspeed, (float) newYspeed));
			Log.d("fruits", "Reflected object "+gameObject+ " in "+gameWall);

		}
	}

	private double dotProduct(GamePosition start, GamePosition endOne,
			GamePosition endTwo) {
		return dotProduct(start, endOne, start, endTwo);
	}

	private double dotProduct(GamePosition startOne, GamePosition endOne,
			GamePosition startTwo, GamePosition endTwo) {
		double[] matrixOne = new double[2];
		double[] matrixTwo = new double[2];
		matrixOne[0] = endOne.getxPosition() - endOne.getxPosition();
		matrixOne[1] = endOne.getyPosition() - endTwo.getyPosition();
		matrixTwo[0] = endTwo.getxPosition() - endTwo.getxPosition();
		matrixTwo[1] = endTwo.getyPosition() - endTwo.getyPosition();
		double dot = matrixOne[0] * matrixTwo[0] + matrixOne[1] * matrixTwo[1];
		return dot;
	}

	private double crossProduct(GamePosition start, GamePosition endOne,
			GamePosition endTwo) {
		double[] matrixOne = new double[2];
		double[] matrixTwo = new double[2];
		matrixOne[0] = endOne.getxPosition() - start.getxPosition();
		matrixOne[1] = endOne.getyPosition() - start.getyPosition();
		matrixTwo[0] = endTwo.getxPosition() - start.getxPosition();
		matrixTwo[1] = endTwo.getyPosition() - start.getyPosition();
		double cross = matrixOne[0] * matrixTwo[1] - matrixOne[1]
				* matrixTwo[0];
		return cross;
	}

}
