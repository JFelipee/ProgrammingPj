package exceptions;

public class EmptyListException extends Exception {
	private static final String MSG = "Error: Empty List ";

	public EmptyListException() {
		super(MSG);
	}

}
