package es.upm.pproject.sokoban.model.interfaces;

import es.upm.pproject.sokoban.model.ActionManager;
import es.upm.pproject.sokoban.model.GameLevel;
import es.upm.pproject.sokoban.model.Map;

public interface OutputManagerInterface {
	
	public void saveFile(GameLevel level, Map map, ActionManager actionManager);
}
