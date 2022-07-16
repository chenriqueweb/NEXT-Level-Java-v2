package br.com.henrique.service.exception;

public class ConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConstraintViolationException(String message) {
		super(message);
	}

}
