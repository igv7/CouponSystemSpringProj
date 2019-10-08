package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.model.Income;
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

	// Store Income
	@PostMapping("/storeIncome/{token}/{client}")
	public ResponseEntity<?> storeIncome(@RequestBody Income income, @PathVariable("token") String token, @PathVariable("client") String client) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				incomeService.storeIncome(income);
				return new ResponseEntity<>("Success. Income was stored " +income, HttpStatus.OK); //"Success. Income was stored" +income
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("faild to store income", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

}
