package es.upm.pproject.sokoban.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import es.upm.pproject.sokoban.model.interfaces.InputManagerInterface;

public class InputManager implements InputManagerInterface{

	// Level information
	private String levelName;
	private int nRows;
	private int nColumns;
	private String movementHistory;
	private int levelScore;
	private int gameScore;
	private int levelNumber;

	// Scanner attributes
	private Scanner levelReader;
	private String line;

	public InputManager(String filePath) throws FileNotFoundException{
			levelReader = new Scanner(new File(filePath));
	}

	@Override
	public void readLevel() {
		// We read the first two lines of the file, the first one for the level name and
		// the second one for the map dimensions
		levelName = readNextLine();
		String[] levelDimensions = readNextLine().split(" ");
		nRows = Integer.parseInt(levelDimensions[0]);
		nColumns = Integer.parseInt(levelDimensions[1]);
	}

	@Override
	public void readSavedFile () {
		movementHistory = readNextLine();
		levelScore = Integer.parseInt(readNextLine());
		gameScore = Integer.parseInt(readNextLine());
		levelNumber = Integer.parseInt(readNextLine());
	}

	@Override
	public char readBoardCharacter(int posChar){
		if(posChar==0)
			readNextLine();
		return line.charAt(posChar);
	}

	private String readNextLine() {
		line = levelReader.nextLine();
		return line;
	}
	
	@Override
	public void close() {
		levelReader.close();
	}
	
	@Override
	
	public String getLevelName() {
		return levelName;
	}

	@Override
	public int getRows() {
		return nRows;
	}

	@Override
	public int getColumns() {
		return nColumns;
	}

	@Override
	public String getMovementHistory() {
		return movementHistory;
	}

	@Override
	public int getLevelScore() {
		return levelScore;
	}

	@Override
	public int getGameScore() {
		return gameScore;
	}

	@Override
	public int getLevelNumber() {
		return levelNumber;
	}
}