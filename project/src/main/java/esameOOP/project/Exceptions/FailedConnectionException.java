package esameOOP.project.Exceptions;

/**
 * Questa classe rappresenta l'eccezione lanciata quando la connesione a FB
 * fallisce
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 *
 */
public class FailedConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public FailedConnectionException() {
		super();
	}

	public FailedConnectionException(String msg) {
		super(msg);
	}
}
