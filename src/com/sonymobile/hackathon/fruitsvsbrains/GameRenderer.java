package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameRenderer {
	private Bitmap bild;
	private Paint brush;
	private Map<GameGraphics, Bitmap> graphicsMap = new LinkedHashMap<GameGraphics, Bitmap>();

	public GameRenderer(Context context) {
		Map<GameGraphics, Integer> loadMap = new LinkedHashMap<GameGraphics, Integer>();
		loadMap.put(GameGraphics.APPLE, R.drawable.apple);
		loadMap.put(GameGraphics.BRAIN, R.drawable.brain);
		loadMap.put(GameGraphics.MIXER, R.drawable.blender);
		for (Entry<GameGraphics, Integer> graphics : loadMap.entrySet()) {
			GameGraphics g = graphics.getKey();
			Bitmap b = BitmapFactory.decodeResource(context.getResources(),
					graphics.getValue());
			float scale;
			if (b.getHeight() > b.getWidth()) {
				scale = (float) g.getSize() / (float) b.getHeight();
			} else {
				scale = (float) g.getSize() / (float) b.getWidth();
			}
			int newHeight = (int) (b.getHeight() * scale);
			int newWidth = (int) (b.getWidth() * scale);

			b = Bitmap.createScaledBitmap(b, newHeight, newWidth, true);
			graphicsMap.put(g, b);
		}

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

		for (GameObject obj : gameState.getObjects()) {
			float diff = obj.getGameGraphics().getSize() / 2.0f;
			canvas.drawBitmap(graphicsMap.get(obj.getGameGraphics()), obj
					.getPosition().getxPosition() - diff, obj.getPosition()
					.getyPosition() - diff, brush);
		}
		canvas.drawText("Fruits vs Brains: " + System.currentTimeMillis(), 100,
				100, brush);
	}
}
