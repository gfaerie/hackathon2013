package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.HashSet;
import java.util.Set;

import android.util.Log;

public class LineRemover  implements GameStateUpdater{

	public void update(GameState gameState){
		int nrWalls=0; 
		Set<Long> toRemove = new HashSet<Long>();
		for(GameWall gameWall : gameState.getWalls()){
			if(gameWall.getType()==GameWallType.DONE){
				nrWalls++;
				if(nrWalls>gameState.getMaxUserWalls()){
					toRemove.add(gameWall.getId());
				}
			}
		}
		
		for (Long id : toRemove) {
			Log.d("fruits", "Removing wall "+gameState.getObject(id));
			gameState.deleteWall(id);
		}
	}
	
	
}
