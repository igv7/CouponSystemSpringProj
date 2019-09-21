package com.igor.CouponSystemSpringProj.rest;

import org.springframework.stereotype.Component;

import com.igor.CouponSystemSpringProj.service.Facade;

@Component//
public class ClientSession {
	
	private Facade facade;
	private long lastAccessed;
	
	
	public Facade getFacade() {
		return facade;
	}
	public void setFacade(Facade facade) {
		this.facade = facade;
	}
	public long getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

}
