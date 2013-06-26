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
	private GameState state;
	private GameRenderer renderer;
	private long currentWall = -1;

	public GameView(Context context) {
		super(context);
	}

	public void setGameRenderer(GameRenderer gameRenderer) {
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
		timer.schedule(task, 1000, 16);
	}

	public void pause() {
		timer.cancel();
		timer = null;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		renderer.render(state, canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (state == null) {
			int canvasWidth = MeasureSpec.getSize(widthMeasureSpec);
			int canvasHeight = MeasureSpec.getSize(heightMeasureSpec);
			state = new GameState(canvasWidth, canvasHeight);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public boolean onTouchEvent(MotionEvent event) {
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	if (currentWall == -1)
	        		currentWall = state.addWall(new GamePosition((int)event.getX(), (int)event.getY()), GameWallType.IN_PROGRESS);
	        	else
	        		Log.i("fruits", "invalid DOWN");
	            break;

	        case MotionEvent.ACTION_MOVE:
	        	state.getWall(currentWall).setEnd(new GamePosition((int)event.getX(), (int)event.getY()));
	            break;

	        case MotionEvent.ACTION_UP:
	        	if (currentWall != -1) {
	        		Log.i("fruits", "new line length=" + state.getWall(currentWall).getLength());
		        	if (state.getWall(currentWall).getLength() < 50)
		        		state.deleteWall(currentWall);
		        	else
		        		state.getWall(currentWall).setType(GameWallType.DONE);
		        	currentWall = -1;
	        	} else
	        		Log.i("fruits", "invalid UP");
	            break;
	    }

		//TODO : Anders
		return true;
	}
}
