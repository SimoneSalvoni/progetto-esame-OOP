package esameOOP.project.Exceptions;

public class TokenNotFoundException extends InternalServerException {

	//se non c'è il file del token
	private static final long serialVersionUID = 1L;

	public TokenNotFoundException() {
		super();
	}
	public TokenNotFoundException(String msg) {
		super(msg);
	}

}
