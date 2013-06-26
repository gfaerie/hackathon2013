package com.sonymobile.hackathon.fruitsvsbrains;

import android.graphics.Canvas;
import android.graphics.Paint;

public class GameRenderer {

	public void render(GameState gameState, Canvas canvas) {
		// TODO : Ernst
		canvas.drawText("Fruits vs Brains: " + System.currentTimeMillis(), 100,
				100, new Paint());
	}

}
