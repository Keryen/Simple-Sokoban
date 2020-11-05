package es.upm.pproject.sokoban.model;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.auxiliar.DirectionsFactory;
import es.upm.pproject.sokoban.model.auxiliar.Pair;
import es.upm.pproject.sokoban.model.interfaces.MovementInterface;
import es.upm.pproject.sokoban.model.objects.*;

public class ActionManager implements MovementInterface {

	private DirectionsFactory factory;

	// We have a stack with the initial position of the moved object and its new position
	private Deque<Pair<Pair<Integer, Integer>,Pair<Integer, Integer>>> movementHistory;

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.MovementManager");
	private static final String EXCEPTIONMSG = "An exception has ocurr";

	public ActionManager() {
		factory = new DirectionsFactory();
		movementHistory = new ArrayDeque<>();
	}

	@Override
	public int movePlayer(Map map, Direction direction) {	
		// It returns 0 (any move has been done), 1 (player or box has been moved),
		// 2 (box has been moved from a GoalPosition to a Floor) or 3 (box has been moved from a Floor to a GoalPosition)

		Player player = map.getPlayer();
		int actI = player.getI();
		int actJ = player.getJ();

		// Look for the next position given the direction
		Pair<Integer, Integer> nextPosition = factory.nextPosition(actI, actJ, direction);
		int nextI = nextPosition.getLeft();
		int nextJ = nextPosition.getRight();

		// Look if the player was on a goal position previously
		GameObject actualObj = null;
		try {
			if (player.isOnGoalPosition())
				actualObj = new GoalPosition();
			else
				actualObj = new Floor();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, EXCEPTIONMSG, e);
		}

		// Look for the next object to the player
		GameObject nextObj = map.getBoardPosition(nextI, nextJ);
		if (nextObj.isWall()) // If there is a wall in front of the player, it returns an empty list
			return 0;
		else if (nextObj.isFloor()) 
			player.setOnGoalPosition(false);
		else if (nextObj.isGoalPosition()) 
			player.setOnGoalPosition(true);
		else if (nextObj.isBox()) 
			return pushBox(map, direction, nextI, nextJ); // For when we want to push a box we will return this methods result

		map.setBoardPosition(actI, actJ, actualObj);
		map.setBoardPosition(nextI, nextJ, player);

		movementHistory.push(new Pair<>(new Pair <>(actI, actJ), new Pair <>(nextI, nextJ)));

		return 1;
	}

	private int pushBox(Map map, Direction direction, int boxI, int boxJ) {

		Box box = (Box) map.getBoardPosition(boxI, boxJ); // We already know this must be a box

		// We will use these booleans to determine whether we will decrement or increment the total boxes score
		boolean wasOnPoint = false;
		boolean isOnPoint = false;

		// Look for the next position given the direction
		Pair<Integer, Integer> nextPosition = factory.nextPosition(boxI, boxJ, direction);
		int nextI = nextPosition.getLeft();
		int nextJ = nextPosition.getRight();

		// Look if the box was on a goal position previously
		GameObject actualObj = null;

		try {
			if (box.isOnGoalPosition()) {
				actualObj = new GoalPosition();
				wasOnPoint = true; // Box was on point, we will now check if the box moves or not
			} else {
				actualObj = new Floor();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, EXCEPTIONMSG, e);
		}

		// Look for the next object to the box
		GameObject nextObj = map.getBoardPosition(nextI, nextJ);
		if (nextObj.isWall()) { // If there is a wall in front of the box, it returns an empty list
			return 0;
		} else if (nextObj.isFloor()) {
			box.setOnGoalPosition(false);
		} else if (nextObj.isGoalPosition()) {
			box.setOnGoalPosition(true);
			isOnPoint = true;
		} else if (nextObj.isBox()) {
			return 0; // A box can't push another box
		}

		map.setBoardPosition(boxI, boxJ, actualObj);
		map.setBoardPosition(nextI, nextJ, box);

		movementHistory.push(new Pair<>(new Pair <>(boxI, boxJ), new Pair <>(nextI, nextJ)));

		if (wasOnPoint && !isOnPoint)  // The box has been moved from a GoalPosition to a Floor (decrement)
			return 2;
		else if (!wasOnPoint && isOnPoint) // The box has been moved from a Floor to a GoalPosition (increment)
			return 3;
		else
			return 1;
	}

	@Override
	public int undoMovement(Map map) {
		// It returns 0 (any move done previously), 1 (move undone but any counter change), 2 (box has been moved from a GoalPosition to a Floor) or 3 (box has been moved from a Floor to a GoalPosition)
		int retValue = 1;

		try {
			Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> lastMove = movementHistory.pop();

			// After a move, at the previous position will be a Tile and at the actual a Player or a Box
			int prevI = lastMove.getLeft().getLeft();
			int prevJ = lastMove.getLeft().getRight();
			int actI = lastMove.getRight().getLeft();
			int actJ = lastMove.getRight().getRight();

			// PorB is Player or Box, Tile is Floor or GoalPosition
			GameObject porb = map.getBoardPosition(actI, actJ);
			GameObject tile = map.getBoardPosition(prevI, prevJ);

			boolean porbWasOnGoal;
			boolean previousTileWasGoal = false;

			if(tile.isGoalPosition())
				porbWasOnGoal = true;
			else 
				porbWasOnGoal = false;

			// We change PorB properties and change its map position
			if(porb.isPlayer()) {
				Player p = (Player) porb;
				p.setI(prevI);
				p.setJ(prevJ);
				previousTileWasGoal = p.isOnGoalPosition();
				p.setOnGoalPosition(porbWasOnGoal);
				map.setBoardPosition(prevI, prevJ, p);
			}
			else if(porb.isBox()) {
				Box b = (Box) porb;
				previousTileWasGoal = b.isOnGoalPosition();
				b.setOnGoalPosition(porbWasOnGoal);
				map.setBoardPosition(prevI, prevJ, b);
				if(previousTileWasGoal && !porbWasOnGoal) // The box has been moved from a GoalPosition to a Floor (decrement)
					retValue = 2;
				else if (!previousTileWasGoal && porbWasOnGoal) // The box has been moved from a Floor to a GoalPosition (increment)
					retValue = 3;
			}

			// We generate a new tile and insert it into the map
			GameObject newTile = null;
			if(previousTileWasGoal)		
				newTile = new GoalPosition();	
			else 
				newTile = new Floor();

			map.setBoardPosition(actI, actJ, newTile);

		} catch (NoSuchElementException e) { // If the stack is empty
			retValue = 0;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, EXCEPTIONMSG, e);
		}

		return retValue;
	}

	@Override
	public String saveMovementHistory() {
		/* We save total number of movements in this format:
		 *
		 * To keep in mind about the format: We know all GameObjects lists will have two
		 * elements, previous position and new position
		 * 
		 * dequeue ->(Which means)-> 
		 * PrevMove1; PrevMove2;.. -> (Which means)-> 
		 * PrevPos1,NewPos1;PrevPos2,NewPos2;.. ->(Which means)->
		 * PrevPosI1-PrevPosJ1,NewPosI1-NewPosJ1;PrevPosI2-PrevPosJ2,NewPosI2-NewPosJ2;..
		 */
		StringBuilder movementsLine = new StringBuilder();
		for (Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> prevMove: movementHistory) {

			// PrevPosI
			movementsLine.append(prevMove.getLeft().getLeft());
			movementsLine.append('-');
			// PrevPosJ
			movementsLine.append(prevMove.getLeft().getRight());
			movementsLine.append(',');
			// NewPosI
			movementsLine.append(prevMove.getRight().getLeft());
			movementsLine.append('-');
			// NewPosJ
			movementsLine.append(prevMove.getRight().getRight());
			movementsLine.append(';');
		}
		return movementsLine.toString();
	}

	@Override
	public void loadMovementHistory(String dequeString) {
		String[] dequeList = dequeString.split(";");
		for (int counter = dequeList.length-1; counter >= 0  && !dequeString.isEmpty(); counter--) {

			String[] objectList = dequeList[counter].split(",");

			String[] prevPos = objectList[0].split("-");
			int prevPosI = Integer.parseInt(prevPos[0]);
			int prevPosJ = Integer.parseInt(prevPos[1]);

			String[] newPos = objectList[1].split("-");
			int newPosI = Integer.parseInt(newPos[0]);
			int newPosJ = Integer.parseInt(newPos[1]);

			movementHistory.push(new Pair<>(new Pair <>(prevPosI, prevPosJ), new Pair <>(newPosI, newPosJ)));
		}
	}
}
