package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.model.interfaces.MapInterface;
import es.upm.pproject.sokoban.model.objects.GameObject;
import es.upm.pproject.sokoban.model.objects.Player;

public class Map implements MapInterface{

	private GameObject[][] board;
	private int nRows;
	private int nColumns;
	private Player player;

	public Map (GameObject[][] board, int nRows, int nColumns, Player player) {
		this.board = board;
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.player = player;
	}

	public GameObject[][] getBoard(){
		GameObject[][] copyBoard = new GameObject[nRows][nColumns];
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		return copyBoard;
	}

	public int getRows () {
		return nRows;
	}

	public int getColumns () {
		return nColumns;
	}

	public Player getPlayer () {
		return player;
	}

	public GameObject getBoardPosition(int i, int j) {
		return board[i][j];
	}

	public void setBoardPosition(int i, int j, GameObject obj) {
		if(obj.isPlayer()) {
			player.setI(i);
			player.setJ(j);
			board[i][j] = player;
		} else {
			board[i][j] = obj;
		}
	}
}
