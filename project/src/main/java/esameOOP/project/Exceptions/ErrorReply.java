package esameOOP.project.Exceptions;

import org.springframework.http.HttpStatus;

public class ErrorReply {
	private final HttpStatus HTTPStatus;
	private final String ErrorMessage;

	public ErrorReply(HttpStatus status, String msg) {
		this.HTTPStatus=status;
		this.ErrorMessage=msg;
	}

	public HttpStatus getHTTPStatus() {
		return HTTPStatus;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

}
