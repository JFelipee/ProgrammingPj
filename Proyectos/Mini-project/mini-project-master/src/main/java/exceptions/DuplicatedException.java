package exceptions;

public class DuplicatedException extends Exception {
	private static final String MSG = "Error: Duplicated Element ";

	public DuplicatedException() {
		super(MSG);
	}
}
