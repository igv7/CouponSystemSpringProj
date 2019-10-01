package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.service.AdminService;
import com.igor.CouponSystemSpringProj.service.CompanyService;
import com.igor.CouponSystemSpringProj.service.CustomerService;
import com.igor.CouponSystemSpringProj.service.IncomeService;

@RestController
@RequestMapping("/income")
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeController {
	
	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}
	
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CustomerService customerService;
	
	
	

}
