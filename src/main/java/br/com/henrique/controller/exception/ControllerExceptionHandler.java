package br.com.henrique.controller.exception;

import java.time.Instant;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.henrique.service.exception.NoNullAllowedException;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

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
	
    @ExceptionHandler(NoNullAllowedException.class)   
    public ResponseEntity<StandardError> NoNullAllowedException(NoNullAllowedException ex, 
                                                                HttpServletRequest httpRequest) {
        
        System.out.println(">>>>>>>>>> teste 222");   
        
        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
        StandardError error = new StandardError(
                Instant.now(), 
                status.value(), 
                "Valor informado não pode ser nulo !", 
                ex.getMessage(), 
                httpRequest.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }       
    
// OK OK    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handle(ConstraintViolationException constraintViolationException, 
    		                                    HttpServletRequest httpRequest) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(
				Instant.now(), 
				status.value(), 
				"Erro de validação",
				"Erros listados abaixo", 
				httpRequest.getRequestURI());

        violations.forEach(violation -> error.addErro(violation.getPropertyPath().toString(), violation.getMessage()));
        return ResponseEntity.status(status).body(error);
        
//        String errorMessage = "";
//        if (!violations.isEmpty()) {
//            StringBuilder builder = new StringBuilder();
//            violations.forEach(violation -> builder.append("\n " + violation.getMessage()));
//            errorMessage = builder.toString();
//        } else {
//            errorMessage = "Ocorreu uma violação de conteúdo campo.";
//        }
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
    
// OK OK     
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
	
	
//  @ExceptionHandler(ConstraintViolationException.class)
//  public ResponseEntity<StandardError> methodConstraintViolationException(ConstraintViolationException ex, HttpServletRequest httpRequest) {
//		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
//		ValidationError error = new ValidationError(
//				Instant.now(), 
//				status.value(), 
//				"Erro de validação!", 
//				"Erros listados abaixo!", 
//				httpRequest.getRequestURI());
//		
//		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
//			error.addErro(x.getField(), x.getDefaultMessage());
//		}
//	  
////      Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
////      String errorMessage = "";
////      if (!violations.isEmpty()) {
////          StringBuilder builder = new StringBuilder();
////          violations.forEach(violation -> builder.append("\n " + violation.getMessage()));
////          errorMessage = builder.toString();
////      } else {
////          errorMessage = "Ocorreu uma violação de conteúdo campo.";
////      }
//      return ResponseEntity.status(status).body(error);
//  }	
	
	
	
	
	
//    @ExceptionHandler(DataIntegrityViolationException.class)   
//    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException ex, 
//                                                                               HttpServletRequest httpRequest) {
//        
//        System.out.println(">>>>>>>>>> teste 111");   
//        
//        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
//        StandardError error = new StandardError(
//                Instant.now(), 
//                status.value(), 
//                "Violação de Integridade Referencial !", 
//                ex.getMessage(), 
//                httpRequest.getRequestURI());
//        return ResponseEntity.status(status).body(error);
//    }	
//
//    @ExceptionHandler(TransactionSystemException.class)   
//    public ResponseEntity<StandardError> handleTransactionSystemException(TransactionSystemException ex, 
//                                                                          HttpServletRequest httpRequest) {
//        
//        System.out.println(">>>>>>>>>> teste 333");     
//        
//        HttpStatus status = HttpStatus.CONFLICT;   // INTERNAL_SERVER_ERROR;
//        StandardError error = new StandardError(
//                Instant.now(), 
//                status.value(), 
//                "Violação de Integridade Referencial !", 
//                ex.getMessage(), 
//                httpRequest.getRequestURI());
//        return ResponseEntity.status(status).body(error);
//    }      
	
}
