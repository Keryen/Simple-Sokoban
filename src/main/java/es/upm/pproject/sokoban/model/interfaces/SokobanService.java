package es.upm.pproject.sokoban.model.interfaces;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.objects.GameObject;

public interface SokobanService {

	public int generateLevel(int levelNumber);
	
	public boolean movePlayer (Direction direction);
	public boolean isFinished ();
	public boolean undoMovement ();
	public boolean saveLevel(int levelNumber);
	public boolean loadLevel(int levelNumber);
	public String readInstructions();
	public void resetGameScore();
	public void music();
	
	public String getLevelName();
	public int getLevelNumber();
	public GameObject[][] getMap ();
	public int getGameScore();
	public int getLevelScore();
	void setTexture(int texture);

}
