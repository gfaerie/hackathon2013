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
	}

	public void render(GameState gameState, Canvas canvas) {

		canvas.drawBitmap(bild, 0, 0, brush);
		canvas.drawText("Fruits vs Brains: " + System.currentTimeMillis(), 100,
				100, new Paint());
	}

}
