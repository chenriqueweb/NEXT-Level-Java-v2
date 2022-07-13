package br.com.henrique.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)	
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest httpRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(
				Instant.now(), 
				status.value(), 
				"Objeto não encontrado!", 
				ex.getMessage(), 
				httpRequest.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(ObjectFoundException.class)	
	public ResponseEntity<StandardError> objectFoundException(ObjectFoundException ex, HttpServletRequest httpRequest) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError error = new StandardError(
				Instant.now(), 
				status.value(), 
				"Registro duplicado!", 
				ex.getMessage(), 
				httpRequest.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest httpRequest) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(
				Instant.now(), 
				status.value(), 
				"Erro de validação!", 
				"Erros listados abaixo!", 
				httpRequest.getRequestURI());
		
		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addErro(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(error);
	}
	
}
