package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameRenderer {
	private Bitmap bild;
	private Paint brush;
	private Map<GameGraphics, Bitmap> graphicsMap = new LinkedHashMap<GameGraphics, Bitmap>();

	public GameRenderer(Context context) {
		graphicsMap.put(GameGraphics.APPLE, BitmapFactory.decodeResource(
				context.getResources(), R.drawable.apple));
		graphicsMap.put(GameGraphics.BRAIN, BitmapFactory.decodeResource(
				context.getResources(), R.drawable.apple));
		graphicsMap.put(GameGraphics.MIXER, BitmapFactory.decodeResource(
				context.getResources(), R.drawable.apple));
		brush = new Paint();
		brush.setAntiAlias(true);

	}

	public void render(GameState gameState, Canvas canvas) {
		brush.setStrokeWidth(Math.max(gameState.getxSize(),
				gameState.getySize()) / 200.0f);
		for (GameWall wall : gameState.getWalls()) {
			if (wall.getType() == GameWallType.IN_PROGRESS) {
				brush.setAlpha(100);
				brush.setColor(Color.CYAN);
			} else {
				brush.setAlpha(255);
				brush.setColor(Color.BLACK);
			}
			canvas.drawLine(wall.getStart().getxPosition(), wall.getStart()
					.getyPosition(), wall.getEnd().getxPosition(), wall
					.getEnd().getyPosition(), brush);
		}
		canvas.drawBitmap(graphicsMap.get(GameGraphics.APPLE), 0, 0, brush);
		canvas.drawText("Fruits vs Brains: " + System.currentTimeMillis(), 100,
				100, brush);
	}
}
