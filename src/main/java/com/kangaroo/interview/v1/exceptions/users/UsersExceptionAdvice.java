package com.kangaroo.interview.v1.exceptions.users;


import com.kangaroo.interview.v1.errors.UsersErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersExceptionAdvice {
    
    @ExceptionHandler(UsersExceptionHandler.class)
	public ResponseEntity<UsersErrors> mapException(UsersExceptionHandler ex) {
		UsersErrors error = new UsersErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<UsersErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}