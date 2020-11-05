package es.upm.pproject.sokoban.model.interfaces;

import es.upm.pproject.sokoban.model.objects.GameObject;
import es.upm.pproject.sokoban.model.objects.Player;

public interface MapInterface {

	public GameObject[][] getBoard();
	public int getRows ();
	public int getColumns ();
	public Player getPlayer ();
	public GameObject getBoardPosition(int i, int j);
	
	public void setBoardPosition(int i, int j, GameObject obj);
}
