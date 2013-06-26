package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	private Timer timer;
	private GameState gameState;
	private GameRenderer renderer;
	private float x,y;

	public GameView(Context context, GameRenderer gameRenderer) {
		super(context);
		this.renderer = gameRenderer;
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
		renderer.render(gameState, canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (gameState == null) {
			int canvasWidth = MeasureSpec.getSize(widthMeasureSpec);
			int canvasHeight = MeasureSpec.getSize(heightMeasureSpec);
			gameState = new GameState(canvasWidth, canvasHeight);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public boolean onTouchEvent(MotionEvent event) {
		//Log.d("sb", "x: " + event.getX() + " y:" + event.getY());

	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	x=event.getX();
	        	y=event.getY();
	            break;

	        case MotionEvent.ACTION_MOVE:
	            break;

	        case MotionEvent.ACTION_UP:
	        	Log.i("fb", "new line xlen=" + (x - event.getX()) + " ylen=" + (y - event.getY()));
	            break;
	    }

		//TODO : Anders
		return true;
	}

}
