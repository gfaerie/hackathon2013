package com.sonymobile.hackathon.fruitsvsbrains;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameRenderer {
	private Bitmap bild;
	private Paint brush;

	public GameRenderer(Context context) {
		bild = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.apple);
		brush = new Paint();
		brush.setAntiAlias(true);
		brush.setStrokeWidth(4);
	}

	public void render(GameState gameState, Canvas canvas) {
		gameState.update();
		for (GameWall wall : gameState.getWalls()) {
			canvas.drawLine(wall.getStart().getxPosition(), wall.getStart()
					.getyPosition(), wall.getEnd().getxPosition(), wall
					.getEnd().getyPosition(), brush);
		}
		canvas.drawBitmap(bild, 0, 0, brush);
		canvas.drawText("Fruits vs Brains: " + System.currentTimeMillis(), 100,
				100, new Paint());
	}
}
