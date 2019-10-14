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
	public ResponseEntity<?> storeIncome(@RequestBody Income income, @PathVariable("token") String token,
			@PathVariable("client") String client) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				switch (client) {
				case "company":
					incomeService.storeIncome(income);
					return new ResponseEntity<>("Success. Income was stored " + income, HttpStatus.OK);
				case "customer":
					incomeService.storeIncome(income);
					return new ResponseEntity<>("Success. Income was stored " + income, HttpStatus.OK);
				default:
					return new ResponseEntity<>("Unauthorized. ", HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("faild to store income", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	// View All Income
	@PostMapping("/viewAllIncome/{token}/{client}")
	public ResponseEntity<?> viewAllIncome(@RequestBody Income income, @PathVariable("token") String token,
			@PathVariable("client") String client) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				switch (client) {
				case "admin":
					incomeService.viewAllIncome();
					return new ResponseEntity<>("Success on view all income " + income, HttpStatus.OK);
				default:
					return new ResponseEntity<>("Unauthorized. ", HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("faild to view all income", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	// View Income By Company
	@PostMapping("/viewIncomeByCompany/{id}/{token}/{client}")
	public ResponseEntity<?> viewIncomeByCompany(@RequestBody Income income, @PathVariable("token") String token,
			@PathVariable("client") String client, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				switch (client) {
				case "admin":
					incomeService.viewIncomeByCompany(id);
					return new ResponseEntity<>("Success on view income by company " + income, HttpStatus.OK);
				case "company":
					incomeService.viewIncomeByCompany(id);
					return new ResponseEntity<>("Success on view income by company " + income, HttpStatus.OK);
				default:
					return new ResponseEntity<>("Unauthorized. ", HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("faild to view income by company", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	// View Income By Customer
	@PostMapping("/viewIncomeByCustomer/{id}/{token}/{client}")
	public ResponseEntity<?> viewIncomeByCustomer(@RequestBody Income income, @PathVariable("token") String token,
			@PathVariable("client") String client, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				switch (client) {
				case "admin":
					incomeService.viewIncomeByCustomer(id);
					return new ResponseEntity<>("Success on view income by customer " + income, HttpStatus.OK);
				case "customer":
					incomeService.viewIncomeByCustomer(id);
					return new ResponseEntity<>("Success on view income by customer " + income, HttpStatus.OK);
				default:
					return new ResponseEntity<>("Unauthorized. ", HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("faild to view income by customer", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

}
