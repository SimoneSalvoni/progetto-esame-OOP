package esameOOP.project.Exceptions;

/**
 * Questa classe rappresenta l'eccezione lanciata quando il body della richiesta
 * passata dal client è nullo
 * 
 * @author Simone Salvoni
 * @author Daniele Saffolani
 */
public class FilterNotFoundException extends NullPointerException {

	private static final long serialVersionUID = 1L;

	public FilterNotFoundException() {
		super();
	}

	public FilterNotFoundException(String msg) {
		super(msg);
	}

}
