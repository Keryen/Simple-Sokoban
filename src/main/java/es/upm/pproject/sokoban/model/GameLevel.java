package es.upm.pproject.sokoban.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import java.util.logging.Level;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.exceptions.IllegalGameObjectException;
import es.upm.pproject.sokoban.model.exceptions.InvalidLevelException;
import es.upm.pproject.sokoban.model.interfaces.GameLevelInterface;
import es.upm.pproject.sokoban.model.objects.*;

public class GameLevel implements GameLevelInterface {

	private String texture;
	private String levelName;
	private int levelNumber;
	private Map map;
	private boolean isFinished;
	private int gameScore;
	private int levelScore;

	private ActionManager actionManager;

	private int contBoxes;
	private int contGoalPositions;
	private int contBoxesOnGoalPosition;

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.GameLevel");
	private static final String EXCEPTIONMSG = "An exception has ocurr";

	// This constructor is for a new level
	public GameLevel(int levelNumber, int gameScore, String texture) throws IllegalGameObjectException, InvalidLevelException, FileNotFoundException {
	
		this.texture = texture;
		this.levelNumber = levelNumber;
		this.gameScore = gameScore;
		levelScore = 0;

		InputManager inManager = new InputManager("resources/levels/level_" + levelNumber + ".txt");
		generateLevel(inManager);

		inManager.close();
	}

	// This constructor is for loading saved games
	public GameLevel(int saveNumber, String texture) throws IllegalGameObjectException, InvalidLevelException, FileNotFoundException {
		this.texture = texture;
		InputManager inManager = new InputManager("resources/saves/game/save_" + saveNumber + ".txt");
		generateLevel(inManager);

		inManager.readSavedFile();
		// Now we want to load the movements done in the game at a movement history
		actionManager.loadMovementHistory(inManager.getMovementHistory());

		// Finally we will load current scores
		levelScore = inManager.getLevelScore();
		gameScore = inManager.getGameScore();
		levelNumber = inManager.getLevelNumber();

		inManager.close();
	}

	private void generateLevel(InputManager inManager) throws IllegalGameObjectException, InvalidLevelException {

		actionManager = new ActionManager();
		contBoxes = 0;
		contGoalPositions = 0;
		contBoxesOnGoalPosition = 0;

		// Local variables
		GameObject obj;
		Player player = null;

		inManager.readLevel();
		levelName = inManager.getLevelName();
		int nRows = inManager.getRows();
		int nColumns = inManager.getColumns();
		GameObject[][] board = new GameObject[nRows][nColumns];

		// Map builder
		for (int currentRow = 0; currentRow < nRows; currentRow++) {
			for (int currentColumn = 0; currentColumn < nColumns; currentColumn++) {

				char objChar = inManager.readBoardCharacter(currentColumn);
				obj = transformCharToObject(objChar);

				if (obj != null && obj.isPlayer()) {
					// If the player has been created and the next object is another player, it throws an exception
					if (player != null)
						throw new InvalidLevelException("Level cannot have two players");
					else {
						player = (Player) obj;
						player.setI(currentRow);
						player.setJ(currentColumn);
					}
				}

				updateObjectCounters(objChar);
				board[currentRow][currentColumn] = obj;
			}
		}

		if (player == null)
			throw new InvalidLevelException("The level needs one player");
		
		if (contBoxes != contGoalPositions)
			throw new InvalidLevelException("Different number of boxes than goal positions");

		if (contGoalPositions == 0) //Both nGoalPositions and nBoxes is 0
			throw new InvalidLevelException("The game cannot be finished");

		map = new Map(board, nRows, nColumns, player);
	}

	private GameObject transformCharToObject(char character) throws IllegalGameObjectException {
		GameObject obj = null;

		try {
			switch (character) {
			case ' ':
				obj = new Floor();
				break;
			case '+':
				obj = new Wall();
				break;
			case 'W':
				obj = new Player(texture);
				break;
			case 'P': // This is a player on a goal position
				obj = new Player(texture);
				((Player) obj).setOnGoalPosition(true);
				break;
			case '#':
				obj = new Box();
				break;
			case 'O': // This is a box on a goal position
				obj = new Box();
				((Box) obj).setOnGoalPosition(true);
				break;
			case '.':
				obj = new EmptySpace();
				break;
			case '*':
				obj = new GoalPosition();
				break;
			default:
				throw new IllegalGameObjectException("This character is not con considered as a level object");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, EXCEPTIONMSG, e);
		}
		return obj;
	}

	private void updateObjectCounters(char objChar) {

		switch (objChar) {
		case 'P': // This is a player on a goal position
			contGoalPositions++;
			break;
		case '#':
			contBoxes++;
			break;
		case 'O': // This is a box on a goal position
			contBoxes++;
			contGoalPositions++;
			contBoxesOnGoalPosition++;
			break;
		case '*':
			contGoalPositions++;
			break;
		default:
		}
	}

	@Override
	public boolean saveLevel(int saveNumber) {

		File savesDir = new File("resources/saves/game");

		// If the directory does not exist, create it
		if (!savesDir.exists()) 
			savesDir.mkdir();

		try {
			if(saveNumber < 1 || saveNumber > 3) {
				throw new FileNotFoundException();
			}
			OutputManager outManager = new OutputManager("resources/saves/game/save_" + saveNumber + ".txt");
			outManager.saveFile(this, map, actionManager);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean movePlayer(Direction direction) {
		boolean playerHasMoved = updateBoxCounters(actionManager.movePlayer(map, direction));
		if(playerHasMoved) { 
			levelScore++;
		}
		return playerHasMoved;
	}

	@Override
	public boolean undoMovement() {
		if (levelScore != 0) {
			levelScore--;
		}
		return updateBoxCounters(actionManager.undoMovement(map));
	}

	private boolean updateBoxCounters(int status) {
		boolean moveDone;

		switch (status) {
		case 1:
			moveDone = true;
			break;
		case 2:
			contBoxesOnGoalPosition--;
			moveDone = true;
			break;
		case 3:
			contBoxesOnGoalPosition++;
			// Updates the boolean that indicates if the game has finished
			isFinished = contBoxesOnGoalPosition == contBoxes;
			moveDone = true;
			break;
		default:
			moveDone = false;
		}
		return moveDone;
	}	

	@Override
	public String getLevelName() {
		return levelName;
	}

	@Override
	public int getLevelNumber() {
		return levelNumber;
	}

	@Override
	public GameObject[][] getMap() {
		return map.getBoard();
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public int getGameScore() {
		return gameScore;
	}

	@Override
	public int getLevelScore() {
		return levelScore;
	}
}
