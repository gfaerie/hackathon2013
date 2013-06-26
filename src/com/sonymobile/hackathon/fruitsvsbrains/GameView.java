package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {
	private Timer timer;

	public GameView(Context context) {
		super(context);
	}
	
	public void resume() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				postInvalidate();
			}
		};
		timer.schedule(task, 1000, 10);
	}

	public void pause() {
		timer.cancel();
		timer = null;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawText("Fruits vs Brains: "+System.currentTimeMillis(), 100, 100, new Paint());
	}
	

}
