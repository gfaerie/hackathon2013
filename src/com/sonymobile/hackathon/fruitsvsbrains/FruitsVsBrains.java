package com.sonymobile.hackathon.fruitsvsbrains;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class FruitsVsBrains extends Activity {
	public static FruitsVsBrains MAIN_ACTIVITY;

	private GameView gameView;
	private GameRenderer gameRenderer;
	
	public FruitsVsBrains(){
		MAIN_ACTIVITY=this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameRenderer = new GameRenderer(this);
		gameView = new GameView(this);
		gameView.setGameRenderer(gameRenderer);
		setContentView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_fruits_vs_brains, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		gameView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		gameView.pause();
	}

	public GameView getGameView() {
		return gameView;
	}
	
	
}
