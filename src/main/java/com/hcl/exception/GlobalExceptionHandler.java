package com.hcl.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleTrainNotFoundException(EmployeeNotFoundException cs, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(cs.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(" Train Not Found", details);
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handleConstraintViolation(ConstraintViolationException ex) {
		
		 List<String> details = new ArrayList<>();
	        for(ConstraintViolation<?> error : ex.getConstraintViolations()) {
	            details.add(error.getMessage());
	        }
	        ErrorResponse error = new ErrorResponse("Validation Failed", details);
	        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		
	}	

}
