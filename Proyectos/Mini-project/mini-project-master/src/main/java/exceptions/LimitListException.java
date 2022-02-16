package exceptions;

public class LimitListException extends Exception {
	
	private static final String MSG = "Error: Reached the limit of the list.";

	public LimitListException() {
		super(MSG);
	}
}
