package es.upm.pproject.sokoban.controllers;

import java.awt.Color;
import java.io.Serializable;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.interfaces.SokobanService;
import es.upm.pproject.sokoban.views.DialogGameFinish;
import es.upm.pproject.sokoban.views.FrameHome;
import es.upm.pproject.sokoban.views.FrameLevel;
import es.upm.pproject.sokoban.views.FrameMenu;
import es.upm.pproject.sokoban.views.DialogNextLevel;
import es.upm.pproject.sokoban.views.DialogPopUp;

public class SokobanController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private FrameLevel levelView;
	private SokobanService model;

	public SokobanController(SokobanService model) {
		this.model = model;
	}

	public void deleteLevelView() {
		levelView = null;
	}

	public void createLevel(int levelNumber, boolean resetGameScore) {
		
		if(resetGameScore){
			model.resetGameScore();
		}
		
		int res = model.generateLevel(levelNumber);
		
		switch(res) {
		case -1:
			new DialogPopUp("Create level Warning", "Something went wrong generating the level :(", Color.RED);
			model.generateLevel(levelNumber+1);
			levelView = new FrameLevel(this, model.getLevelName(), model.getMap(), model.getGameScore(), 0);
			break;
		case 0:
			new DialogGameFinish(this,model.getGameScore());
			break;
		default:
			levelView = new FrameLevel(this, model.getLevelName(), model.getMap(), model.getGameScore(), 0);

		}
	}

	public void onMove(char key) {

		Direction direction = null;

		if (key == 'a' || key == 'A')
			direction = Direction.LEFT;
		else if (key == 'd' || key == 'D')
			direction = Direction.RIGHT;
		else if (key == 'w' || key == 'W')
			direction = Direction.UP;
		else if (key == 's' || key == 'S')
			direction = Direction.DOWN;

		if (direction != null && model.movePlayer(direction)) {
			levelView.updateInterface(model.getMap());
			levelView.updateScores(model.getGameScore(), model.getLevelScore());
		}

		if (model.isFinished()) {
			levelView.setVisible(false);
			new DialogNextLevel(this, model.getLevelName(), model.getLevelNumber(), model.getLevelScore(), model.getGameScore());
		}
	}

	public void onUndo() {
		if (model.undoMovement()) {
			levelView.updateInterface(model.getMap());
			levelView.updateScores(model.getGameScore(), model.getLevelScore());
		}
	}

	public void onReset() {
		levelView.setVisible(false);
		createLevel(model.getLevelNumber(), false);
	}

	public void saveGame(int levelNumber) {
		if(model.saveLevel(levelNumber))
			new DialogPopUp("Succesfull action", "The game has been saved succesfully :D", Color.BLUE);
		else
			new DialogPopUp("Save level Warning", "Something went wrong saving the level :(", Color.RED);

	}

	public void loadGame(int levelNumber) {
		if (!model.loadLevel(levelNumber)) {
			if(levelView == null) 
				new FrameHome(this);
			else
				new FrameMenu(this, levelView);

			new DialogPopUp("Load level Warning", "Something went wrong loading the level :(", Color.RED);
		}
		else {
			if (levelView != null)
				levelView.setVisible(false);

			levelView = new FrameLevel(this, model.getLevelName(), model.getMap(), model.getGameScore(),model.getLevelScore());
		}
	}
	
	public void initMusic() {
		model.music();
	}

	public void readInstructions() {
		new DialogPopUp("Sokoban Instructions", model.readInstructions(), Color.BLACK);
	}
	
	public void setTexture(int texture) {
		model.setTexture(texture);
	}
	
}
