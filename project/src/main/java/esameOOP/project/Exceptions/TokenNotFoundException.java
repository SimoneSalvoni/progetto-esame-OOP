package esameOOP.project.Exceptions;

/**
 * Questa classe rappresenta l'eccezione che viene lanciata se l'access token
 * non viene trovato
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class TokenNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TokenNotFoundException() {
		super();
	}

	public TokenNotFoundException(String msg) {
		super(msg);
	}

}
