package br.com.henrique.controller.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldErrorMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Instant timestamp, int status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
		
	public List<FieldErrorMessage> getErrors() {
		return errors;
	}
	
	public void addErro(String field, String message) {
		errors.add(new FieldErrorMessage(field, message));
	}
	
}
