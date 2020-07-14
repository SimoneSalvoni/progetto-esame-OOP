package esameOOP.project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import esameOOP.project.Exceptions.*;

/**
 * Questa classe ha il compito di gestire le eventuali eccezioni lanciate
 * dall'applicazione
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
@ControllerAdvice
public class ExceptionHandlerClass {

	/**
	 * Questo metodo gestisce le eccezioni di tipo FilterNotFoundException,
	 * restituendo un oggetto di tipo ResponseEntity al chiamante
	 * 
	 * @param e l'eccezione lanciata
	 * @return Un oggetto della classe ResponseEntity, con HttpStatus BAD_REQUEST e
	 *         con il messaggio passato al lancio dell'eccezione
	 * @see FilterNotFoundException
	 * @see ErrorReply
	 * @see org.springframework.http.ResponseEntity
	 */
	@ExceptionHandler(value = { FilterNotFoundException.class })
	public ResponseEntity<Object> handleFilterNotFoundException(FilterNotFoundException e) {
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Questo metodo gestisce le eccezioni di tipo InvalidFilterException,
	 * restituendo un oggetto di tipo ResponseEntity al chiamante
	 * 
	 * @param e l'eccezione lanciata
	 * @return Un oggetto della classe ResponseEntity, con HttpStatus BAD_REQUEST e
	 *         con il messaggio passato al lancio dell'eccezione
	 * @see InvalidFilterException,ErrorReply
	 * @see ErrorReply
	 * @see org.springframework.http.ResponseEntity
	 */
	@ExceptionHandler(value = { InvalidFilterException.class })
	public ResponseEntity<Object> handleInvalidFilterException(InvalidFilterException e) {
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Questo metodo gestisce le eccezioni di tipo FailedConnectionException,
	 * restituendo un oggetto di tipo ResponseEntity al chiamante
	 * 
	 * @param e l'eccezione lanciata
	 * @return Un oggetto della classe ResponseEntity, con HttpStatus BAD_REQUEST e
	 *         con il messaggio passato al lancio dell'eccezione
	 * @see FailedConnectionException
	 * @see ErrorReply
	 * @see org.springframework.http.ResponseEntity
	 */
	@ExceptionHandler(value = { FailedConnectionException.class })
	public ResponseEntity<Object> handleFailedConnectionException(FailedConnectionException e) {
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Questo metodo gestisce le eccezioni di tipo TokenNotFoundException,
	 * restituendo un oggetto di tipo ResponseEntity al chiamante
	 * 
	 * @param e l'eccezione lanciata
	 * @return Un oggetto della classe ResponseEntity, con HttpStatus
	 *         INTERNAL_SERVER_ERROR e con il messaggio passato al lancio
	 *         dell'eccezione
	 * @see TokenNotFoundException
	 * @see ErrorReply
	 * @see org.springframework.http.ResponseEntity
	 */
	@ExceptionHandler(value = { TokenNotFoundException.class })
	public ResponseEntity<Object> handleInternalServerException(TokenNotFoundException e) {
		ErrorReply reply = new ErrorReply(HttpStatus.INTERNAL_SERVER_ERROR,
				"An internal error has occured while trying to find the access token");
		return new ResponseEntity<>(reply, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Questo metodo gestisce le eccezioni di tipo EmptyFeedException, restituendo
	 * un oggetto di tipo ResponseEntity al chiamante
	 * 
	 * @param e l'eccezione lanciata
	 * @return Un oggetto della classe ResponseEntity, con HttpStatus BAD_REQUEST e
	 *         con il messaggio passato al lancio dell'eccezione
	 * @see EmptyFeedException
	 * @see ErrorReply
	 * @see org.springframework.http.ResponseEntity
	 */
	@ExceptionHandler(value = { EmptyFeedException.class })
	public ResponseEntity<Object> handleEmptyFeedException(EmptyFeedException e) {
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
	}

}
