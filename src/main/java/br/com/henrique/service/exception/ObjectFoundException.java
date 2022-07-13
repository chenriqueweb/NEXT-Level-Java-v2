package br.com.henrique.service.exception;

public class ObjectFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectFoundException(String message) {
		super(message);
	}

}
