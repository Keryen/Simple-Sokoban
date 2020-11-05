package es.upm.pproject.sokoban.model.exceptions;

public class IllegalGameObjectException extends Exception{

	private static final long serialVersionUID = 4592422999628968575L;

	public IllegalGameObjectException(String msg) {
		super(msg);
	}
}
