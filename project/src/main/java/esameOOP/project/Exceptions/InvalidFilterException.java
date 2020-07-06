package esameOOP.project.Exceptions;

public class InvalidFilterException extends IllegalArgumentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFilterException() {
		super();
	}
	public InvalidFilterException(String msg) {
		super(msg);
	}
}
