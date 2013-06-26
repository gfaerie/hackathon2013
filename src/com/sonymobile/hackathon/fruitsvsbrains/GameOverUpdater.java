package com.sonymobile.hackathon.fruitsvsbrains;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class GameOverUpdater implements GameStateUpdater {

	@Override
	public void update(final GameState gameState) {
		if (gameState.getRemainingFruits() <= 0) {
			FruitsVsBrains.MAIN_ACTIVITY.getGameView().pause();
			AlertDialog.Builder builder = new AlertDialog.Builder(FruitsVsBrains.MAIN_ACTIVITY);
			builder.setMessage(
					"Game over! Play a new game?")
					.setCancelable(false)
					.setNegativeButton("Exit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									FruitsVsBrains.MAIN_ACTIVITY.finish();
								}
							})
					.setPositiveButton("New Game",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									gameState.setLevel(1);
									FruitsVsBrains.MAIN_ACTIVITY.getGameView()
											.resume();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}

	}

}
