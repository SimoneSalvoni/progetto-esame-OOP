package esameOOP.project.Exceptions;

public class FilterNotFoundException extends NullPointerException {

	private static final long serialVersionUID = 1L;

	public FilterNotFoundException() {
		super();
	}
	public FilterNotFoundException(String msg) {
		super(msg);
	}

}
