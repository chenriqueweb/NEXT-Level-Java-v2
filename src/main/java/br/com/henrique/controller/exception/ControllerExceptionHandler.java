package br.com.henrique.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.henrique.service.exception.ConstraintViolationException;
import br.com.henrique.service.exception.DataIntegrityViolationException;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;
import br.com.henrique.service.exception.TransactionSystemException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)	
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, 
	                                                             HttpServletRequest httpRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(
				Instant.now(), 
				status.value(), 
				"Objeto não encontrado !", 
				ex.getMessage(), 
				httpRequest.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(ObjectFoundException.class)	
	public ResponseEntity<StandardError> objectFoundException(ObjectFoundException ex, 
	                                                          HttpServletRequest httpRequest) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError error = new StandardError(
				Instant.now(), 
				status.value(), 
				"Registro duplicado !", 
				ex.getMessage(), 
				httpRequest.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
    @ExceptionHandler(DataIntegrityViolationException.class)   
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException ex, 
                                                                               HttpServletRequest httpRequest) {
        
        System.out.println(">>>>>>>>>> teste 111");   
        
        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
        StandardError error = new StandardError(
                Instant.now(), 
                status.value(), 
                "Violação de Integridade Referencial !", 
                ex.getMessage(), 
                httpRequest.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }	
    
    @ExceptionHandler(ConstraintViolationException.class)   
    public ResponseEntity<StandardError> handleConstraintViolationException(ConstraintViolationException ex, 
                                                                            HttpServletRequest httpRequest) {
        
        System.out.println(">>>>>>>>>> teste 222");   
        
        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
        StandardError error = new StandardError(
                Instant.now(), 
                status.value(), 
                "Violação de Integridade Referencial !", 
                ex.getMessage(), 
                httpRequest.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }       
    
    @ExceptionHandler(TransactionSystemException.class)   
    public ResponseEntity<StandardError> handleTransactionSystemException(TransactionSystemException ex, 
                                                                          HttpServletRequest httpRequest) {
        
        System.out.println(">>>>>>>>>> teste 333");   
        
        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
        StandardError error = new StandardError(
                Instant.now(), 
                status.value(), 
                "Violação de Integridade Referencial !", 
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
