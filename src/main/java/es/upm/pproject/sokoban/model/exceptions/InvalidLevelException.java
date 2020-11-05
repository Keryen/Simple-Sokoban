package es.upm.pproject.sokoban.model.exceptions;

public class InvalidLevelException extends Exception{

	private static final long serialVersionUID = -2131066534461096183L;

	public InvalidLevelException(String msg) {
		super(msg);
	}
}
