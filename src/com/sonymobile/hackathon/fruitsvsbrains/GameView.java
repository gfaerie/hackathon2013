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
		state.update();
		renderer.render(state, canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (state == null) {
			int canvasWidth = MeasureSpec.getSize(widthMeasureSpec);
			int canvasHeight = MeasureSpec.getSize(heightMeasureSpec);
			state = new GameState(canvasWidth, canvasHeight);
			state.addObject(GameGraphics.BRAIN, new GamePosition(3*canvasWidth/4,3*canvasHeight/4), GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(canvasWidth/4,3*canvasHeight/4), GameObjectType.BRAIN);
			state.addObject(GameGraphics.BRAIN, new GamePosition(3*canvasWidth/4,canvasHeight/4), GameObjectType.BRAIN);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private boolean insideStartArea(int x, int y) {
		if (x < 300 && y < 300)
			return true;
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();

	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	if (!insideStartArea(x, y)) {
		        	if (currentWall == -1)
		        		currentWall = state.addWall(new GamePosition(x, y), GameWallType.IN_PROGRESS);
		        	else
		        		Log.i("fruits", "invalid DOWN");
	        	}
	            break;

	        case MotionEvent.ACTION_MOVE:
	        	if (!insideStartArea(x, y) && currentWall != -1) {
	        		state.getWall(currentWall).setEnd(new GamePosition(x, y));
	        	}
	        	break;

	        case MotionEvent.ACTION_UP:
	        	if (!insideStartArea(x, y)) {
		        	if (currentWall != -1) {
		        		Log.i("fruits", "new line length=" + state.getWall(currentWall).getLength());
			        	if (state.getWall(currentWall).getLength() < 50)
			        		state.deleteWall(currentWall);
			        	else
			        		state.getWall(currentWall).setType(GameWallType.DONE);
			        	currentWall = -1;
		        	} else
		        		Log.i("fruits", "invalid UP");
	        	} else {
	        		if (currentWall != -1) {
	        			state.deleteWall(currentWall);
	        			currentWall = -1;
	        		} else {
	        			Log.i("fruits", "click");
	        			long object = state.addObject(GameGraphics.APPLE, new GamePosition(x,y), GameObjectType.FRUIT);
	        			state.getObject(object).setGameMovement(new GameMovement(5,5));
	        		}
	        	}
	            break;
	    }

		return true;
	}
}
