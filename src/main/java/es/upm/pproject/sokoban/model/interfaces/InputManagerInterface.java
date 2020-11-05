package es.upm.pproject.sokoban.model.interfaces;

public interface InputManagerInterface {

	public void readLevel();
	public void readSavedFile();
	public char readBoardCharacter(int posChar);
	public void close();
	
	public String getLevelName();
	public int getRows();
	public int getColumns();
	public String getMovementHistory();
	public int getLevelScore();
	public int getGameScore();
	public int getLevelNumber();
}
