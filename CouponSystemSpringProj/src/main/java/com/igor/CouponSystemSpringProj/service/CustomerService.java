package com.igor.CouponSystemSpringProj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;

@Service
//@Scope("prototype")
@Transactional
public class CustomerService implements Facade {
	
	private long custId;
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;

}
