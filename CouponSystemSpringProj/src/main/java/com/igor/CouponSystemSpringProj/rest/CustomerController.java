package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}
	
	@Autowired
	private CustomerService customerService;
	
	
	//Add Coupon (Purchase)
	
	//View All Purchased Coupons
	//View All Purchased Coupons By Type
	//View All Purchased Coupons By Price

}
