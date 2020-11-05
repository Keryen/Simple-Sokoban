package es.upm.pproject.sokoban.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.auxiliar.Music;
import es.upm.pproject.sokoban.model.exceptions.IllegalGameObjectException;
import es.upm.pproject.sokoban.model.exceptions.InvalidLevelException;
import es.upm.pproject.sokoban.model.interfaces.SokobanService;
import es.upm.pproject.sokoban.model.objects.GameObject;

public class SokobanImpl implements SokobanService {

	private int gameScore;
	private GameLevel level;
	private String texture = "Player";
	
	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.SokobanImpl");
	private static final String EXCEPTION_MSG = "An exception has ocurr";

	public SokobanImpl() {
		gameScore = 0;
	}

	@Override
	public int generateLevel(int levelNumber) {
		try {
			level = new GameLevel(levelNumber, gameScore, texture);
		} catch (IllegalGameObjectException | InvalidLevelException e) {
			LOGGER.log(Level.SEVERE, EXCEPTION_MSG, e);
			return -1;
		}catch(FileNotFoundException e) {
			return 0;
		}
		return 1;
	}

	@Override
	public boolean movePlayer(Direction direction) {
		return level.movePlayer(direction);
	}

	@Override
	public boolean isFinished() {
		boolean finished = level.isFinished();
		if (finished)
			gameScore += level.getLevelScore();

		return finished;
	}

	@Override
	public boolean undoMovement() {
		return level.undoMovement();
	}

	@Override
	public boolean saveLevel(int levelNumber) {
		return level.saveLevel(levelNumber);
	}

	@Override
	public boolean loadLevel(int number) {
		try {
			GameLevel loadLevel = new GameLevel(number,texture);
			this.level = loadLevel;
			this.gameScore = loadLevel.getGameScore();
			return true;
		} catch (IllegalGameObjectException | InvalidLevelException | FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, EXCEPTION_MSG, e);
			return false;
		}
	}

	@Override
	public String readInstructions() {
		StringBuilder res = new StringBuilder();
		try (Scanner scan = new Scanner(new File("resources/Instructions.txt"))) {
			res.append("<html>");
			while (scan.hasNext()) {
				res.append(scan.nextLine() + "<br/>");		
			}
			res.append("</html>");
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, EXCEPTION_MSG, e);
		}
		return res.toString();
	}
	
	public void music() {
		Music music = new Music();
		music.start();
	}
	
	@Override
	public String getLevelName() {
		return level.getLevelName();
	}

	@Override
	public int getLevelNumber() {
		return level.getLevelNumber();
	}

	@Override
	public GameObject[][] getMap() {
		return level.getMap();
	}

	@Override
	public int getGameScore() {
		return gameScore;
	}

	@Override
	public int getLevelScore() {
		return level.getLevelScore();
	}
	
	@Override
	public void resetGameScore() {
		gameScore = 0;
	}
	
	@Override
	public void setTexture(int texture) {
		switch(texture) {
		case 1:
			this.texture = "Chocobo";
			break;
		case 2:
			this.texture = "Mario";
			break;
		case 3:
			this.texture = "Red";
			break;
		case 4:
			this.texture = "Sans";
			break;
		default:
			this.texture = "Player";
		}
	}
	
	public String getTexture() {
		return this.texture;
	}
}
