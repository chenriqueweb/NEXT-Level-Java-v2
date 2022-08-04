package br.com.henrique.service.exception;

public class NoNullAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoNullAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoNullAllowedException(String message) {
		super(message); 
	}

}
