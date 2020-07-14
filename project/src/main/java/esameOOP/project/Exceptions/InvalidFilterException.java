package esameOOP.project.Exceptions;

/**
 * Questa classe rappresenta l'eccezione lanciata quando il filtro passato dal
 * client contiene degli errori
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class InvalidFilterException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public InvalidFilterException() {
		super();
	}

	public InvalidFilterException(String msg) {
		super(msg);
	}
}
