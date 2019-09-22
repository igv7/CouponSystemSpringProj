package com.igor.CouponSystemSpringProj.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.service.AdminService;
import com.igor.CouponSystemSpringProj.rest.ClientSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}

	@Autowired
	private AdminService adminService;
	
	
	//for Company

	@PostMapping("/addCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.createCompany(company);
//			((AdminService) clientSession.getFacade()).createCompany(company);
				return new ResponseEntity<>("Company added", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@PutMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.updateCompany(company);
//			((AdminService) clientSession.getFacade()).updateCompany(company);
				return new ResponseEntity<>("Company updated", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@DeleteMapping("/deleteCompany/{token}/{id}")
	public ResponseEntity<String> removeCompany(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.removeCompany(id);
//			((AdminService) clientSession.getFacade()).removeCompany(id);
				return new ResponseEntity<>("Company removed", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	public Company getCompany() {
		return null;
	}

	public List<Company> getAllCompanies() {
		return null;
	}
	
	
	//for Customer

	public Customer createCustomer() {
		return null;
	}

	public Customer updateCustomer() {
		return null;
	}

	public Customer removeCustomer() {
		return null;
	}

	public Customer getCustomer() {
		return null;
	}

	public List<Customer> getAllCustomers() {
		return null;
	}

}
