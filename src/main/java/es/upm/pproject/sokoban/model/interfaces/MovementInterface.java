package es.upm.pproject.sokoban.model.interfaces;

import es.upm.pproject.sokoban.model.Map;
import es.upm.pproject.sokoban.model.auxiliar.Direction;

public interface MovementInterface {

	public int movePlayer(Map map, Direction direction);
	public int undoMovement(Map map);
	
	public String saveMovementHistory();
	public void loadMovementHistory(String dequeString);
}
