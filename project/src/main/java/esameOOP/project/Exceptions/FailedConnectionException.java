package esameOOP.project.Exceptions;

public class FailedConnectionException extends Exception{

	private static final long serialVersionUID = 1L;
	//noSuchElementException viene lanciata se la chiamata non va a buon fine: falla lanciare alla requesteAndParse.

	public FailedConnectionException() {
		super();
	}
	public FailedConnectionException(String msg) {
		super(msg);
	}
}
