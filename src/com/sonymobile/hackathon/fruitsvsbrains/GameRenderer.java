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
import android.graphics.RectF;

public class GameRenderer {
	private Paint brush, scorePaint;
	private Map<GameGraphics, Bitmap> graphicsMap = new LinkedHashMap<GameGraphics, Bitmap>();
	private Bitmap background;

	public GameRenderer(Context context) {
		background = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruits);
		Map<GameGraphics, Integer> loadMap = new LinkedHashMap<GameGraphics, Integer>();
		loadMap.put(GameGraphics.APPLE, R.drawable.apple);
		loadMap.put(GameGraphics.ORANGE, R.drawable.orange);
		loadMap.put(GameGraphics.BANANA, R.drawable.banana);
		loadMap.put(GameGraphics.BRAIN, R.drawable.brain);
		loadMap.put(GameGraphics.MIXER, R.drawable.blender);
		for (Entry<GameGraphics, Integer> graphics : loadMap.entrySet()) {
			GameGraphics g = graphics.getKey();
			Bitmap b = BitmapFactory.decodeResource(context.getResources(),
					graphics.getValue());
			float scale;
			if (b.getHeight() > b.getWidth()) {
				scale = (float) g.getSize() * 2 / (float) b.getHeight();
			} else {
				scale = (float) g.getSize() * 2 / (float) b.getWidth();
			}
			int newHeight = (int) (b.getHeight() * scale);
			int newWidth = (int) (b.getWidth() * scale);

			b = Bitmap.createScaledBitmap(b, newHeight, newWidth, true);
			graphicsMap.put(g, b);
		}

		brush = new Paint();
		brush.setAntiAlias(true);
		scorePaint = new Paint();
		scorePaint.setColor(Color.BLUE);
	}

	public void render(GameState gameState, Canvas canvas) {
		float boardSize = Math.max(gameState.getxSize(),
				gameState.getySize());
		
		canvas.drawBitmap(background, null, new RectF(0f,0f,(float)canvas.getWidth(),(float)canvas.getHeight()), null);

		scorePaint.setTextSize(boardSize / 20.0f);
		brush.setStrokeWidth(boardSize / 140.0f);
		for (GameWall wall : gameState.getWalls()) {
			if (wall.getType() == GameWallType.IN_PROGRESS) {
				brush.setAlpha(100);
				brush.setColor(Color.CYAN);
			} else {
				brush.setAlpha(255);
				brush.setColor(Color.GREEN);
			}
			canvas.drawLine(wall.getStart().getxPosition(), wall.getStart()
					.getyPosition(), wall.getEnd().getxPosition(), wall
					.getEnd().getyPosition(), brush);
		}

		for (GameObject obj : gameState.getObjects()) {
			float diff = obj.getGameGraphics().getSize();
			/*canvas.drawCircle(obj.getPosition().getxPosition(), obj
					.getPosition().getyPosition(), diff, brush);*/
			canvas.drawBitmap(graphicsMap.get(obj.getGameGraphics()), obj
					.getPosition().getxPosition() - diff, obj.getPosition()
					.getyPosition() - diff, brush);
		}
		canvas.drawText("Fruits mixed: " + gameState.getCurrentScore()+"/"+gameState.getTargetScore(), 0, gameState.getySize(), scorePaint);
		canvas.drawText("Remaining fruits: " + gameState.getRemainingFruits(), 100, gameState.getySize(), scorePaint);

	}
}
