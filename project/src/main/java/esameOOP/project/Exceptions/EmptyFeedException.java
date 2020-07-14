package esameOOP.project.Exceptions;

/**
 * Questa classe rappresenta l'eccezione che viene lanciata quando vengono
 * richieste statistiche su un feed privo di Post
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class EmptyFeedException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyFeedException() {
		super();
	}

	public EmptyFeedException(String msg) {
		super(msg);
	}

}
