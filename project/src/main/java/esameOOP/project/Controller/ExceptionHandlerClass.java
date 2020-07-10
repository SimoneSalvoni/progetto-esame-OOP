package esameOOP.project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import esameOOP.project.Exceptions.*;

@ControllerAdvice
public class ExceptionHandlerClass {
	
	@ExceptionHandler(value = {FilterNotFoundException.class})
	public ResponseEntity<Object> handleFilterNotFoundException(FilterNotFoundException e){
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST,e.getMessage());
		return new ResponseEntity<>(reply,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {InvalidFilterException.class})
	public ResponseEntity<Object> handleInvalidFilterException(InvalidFilterException e){
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST,e.getMessage());
		return new ResponseEntity<>(reply,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {FailedConnectionException.class})
	public ResponseEntity<Object> handleFailedConnectionException(FailedConnectionException e){
		ErrorReply reply = new ErrorReply(HttpStatus.BAD_REQUEST,e.getMessage());
		return new ResponseEntity<>(reply,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {InternalServerException.class})
	public ResponseEntity<Object> handleInternalServerException(InternalServerException e){
		ErrorReply reply = new ErrorReply(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		return new ResponseEntity<>(reply,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

