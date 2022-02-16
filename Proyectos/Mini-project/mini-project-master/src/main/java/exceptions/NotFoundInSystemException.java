package exceptions;

public class NotFoundInSystemException extends Exception {
	
	private static final String MSG = "Error: It is not found in the system.";
	
	public NotFoundInSystemException() {
		super(MSG);
	}

}
