package es.upm.pproject.sokoban.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import es.upm.pproject.sokoban.model.interfaces.OutputManagerInterface;
import es.upm.pproject.sokoban.model.objects.Box;
import es.upm.pproject.sokoban.model.objects.GameObject;
import es.upm.pproject.sokoban.model.objects.Player;

public class OutputManager implements OutputManagerInterface{

	private PrintWriter levelWriter;

	public OutputManager(String path) throws FileNotFoundException, UnsupportedEncodingException {
		levelWriter = new PrintWriter(path, "UTF-8");
	}

	@Override
	public void saveFile(GameLevel level, Map map, ActionManager actionManager) {

		levelWriter.println(level.getLevelName());

		int nRows = map.getRows();
		int nColumns = map.getColumns();
		levelWriter.println(nRows + " " + nColumns);

		// We save current map status
		for (int currentRow = 0; currentRow < nRows; currentRow++) {
			StringBuilder line = new StringBuilder();

			for (int currentColumn = 0; currentColumn < nColumns; currentColumn++) {
				GameObject object = map.getBoardPosition(currentRow, currentColumn);
				line.append(transformObjectToChar(object));
			}
			levelWriter.println(line.toString());
		}

		// We save the movement history
		levelWriter.println(actionManager.saveMovementHistory());

		// Now we save current level score and game score
		levelWriter.println(level.getLevelScore());
		levelWriter.println(level.getGameScore());
		levelWriter.println(level.getLevelNumber());

		levelWriter.close();
	}

	private char transformObjectToChar(GameObject obj) {
		char result = 'X'; // X is error, it should be replaced
		if (obj.isEmptySpace())
			result = '.';
		else if (obj.isWall())
			result = '+';
		else if (obj.isFloor())
			result = ' ';
		else if (obj.isGoalPosition())
			result = '*';
		else if (obj.isBox()) {
			if (((Box) obj).isOnGoalPosition())
				result = 'O'; // This box is on a goal position
			else
				result = '#'; // Regular box

		} else if (obj.isPlayer()) {
			if (((Player) obj).isOnGoalPosition())
				result = 'P'; // This player is on a goal position
			else
				result = 'W'; // Regular player
		}
		return result;
	}
}
