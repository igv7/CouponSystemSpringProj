package com.igor.CouponSystemSpringProj.service;

import org.springframework.stereotype.Component;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;

@Component//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class ServiceStatus {
	
	private boolean success;
	private String message;
	
	public ServiceStatus() {
		
	}

	public ServiceStatus(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
