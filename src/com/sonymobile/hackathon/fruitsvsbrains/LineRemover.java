package com.sonymobile.hackathon.fruitsvsbrains;

import java.util.LinkedList;
import java.util.List;

public class LineRemover  implements GameStateUpdater{

	public void update(GameState gameState){
		List<Long> walls = new LinkedList<Long>();
		for(GameWall gameWall : gameState.getWalls()){
			if(gameWall.getType()==GameWallType.DONE){
					walls.add(gameWall.getId());
				
			}
		}
		
		for (int i=0;i<walls.size()-gameState.getMaxUserWalls();i++) {
			gameState.deleteWall(walls.get(i));
		}
	}
	
	
}
