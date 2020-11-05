package es.upm.pproject.sokoban.model.interfaces;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.objects.GameObject;

public interface GameLevelInterface {	
	
	public boolean saveLevel(int saveName);
	
	public boolean movePlayer(Direction direction);
	public boolean undoMovement();
	
	public String getLevelName();
	public int getLevelNumber();
	public GameObject[][] getMap();
	public boolean isFinished();
	public int getGameScore();
	public int getLevelScore();
	
}
