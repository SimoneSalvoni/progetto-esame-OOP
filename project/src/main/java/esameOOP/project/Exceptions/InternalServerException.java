package esameOOP.project.Exceptions;

public class InternalServerException extends Exception {

 static final long serialVersionUID = 1L;

	public InternalServerException() {
		super();
	}
	public InternalServerException(String msg) {
		super(msg);
	}

}
