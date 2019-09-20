package com.igor.CouponSystemSpringProj.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ClassCastAdvice {
	
	@ExceptionHandler(ClassCastException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public final String handleClassCastException(ClassCastException ex) {
		return "You are not allowed to perform this action!";
	}

}
