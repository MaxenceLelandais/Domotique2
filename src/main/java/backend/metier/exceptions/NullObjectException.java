package backend.metier.exceptions;

public class NullObjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public NullObjectException(String errorMessage) {
		
		super(errorMessage);
	}

}
