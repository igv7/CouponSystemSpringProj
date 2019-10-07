package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
//		System.out.println("token " +token);
//		System.out.println(tokensMap.size());
		return tokensMap.get(token);
	}

	@Autowired
	private AdminService adminService;
	
	
	//for Company
	//Add Company
	@PostMapping("/addCompany/{token}")
	public ResponseEntity<?> createCompany(@RequestBody Company company, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
//		System.out.println(tokensMap.size());
//		System.out.println(clientSession.getFacade()+" "+clientSession.getLastAccessed());
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.createCompany(company);
//			((AdminService) clientSession.getFacade()).createCompany(company);
				return new ResponseEntity<>(adminService.createCompany(company), HttpStatus.OK); //"Company added"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add company by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//Update Company
	@PutMapping("/updateCompany/{token}/{id}")
	public ResponseEntity<?> updateCompany(@RequestBody Company company, @PathVariable("token") String token, @PathVariable long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.updateCompany(company);
//			((AdminService) clientSession.getFacade()).updateCompany(company);
				return new ResponseEntity<>(adminService.updateCompany(company), HttpStatus.OK); //"Company updated"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update company by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//Delete Company
	@DeleteMapping("/deleteCompany/{token}/{id}")
	public ResponseEntity<?> removeCompany(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.removeCompany(id);
//			((AdminService) clientSession.getFacade()).removeCompany(id);
				return new ResponseEntity<>(adminService.removeCompany(id), HttpStatus.OK); //"Company removed"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete company by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//View Company
	@GetMapping("/viewCompany/{token}/{id}")
	public ResponseEntity<?> getCompany(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getCompany(id), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getCompany(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view company by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//View All Companies
	@GetMapping("/viewAllCompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getAllCompanies(), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getAllCompanies(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all companies by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	
	//for Customer
	//Add Customer
	@PostMapping("/addCustomer/{token}")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.createCustomer(customer);
//			((AdminService) clientSession.getFacade()).createCustomer(customer);
				return new ResponseEntity<>(adminService.createCustomer(customer), HttpStatus.OK); //"Customer added"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add customer by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//Update Customer
	@PutMapping("/updateCustomer/{token}/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("token") String token, @PathVariable long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.updateCustomer(customer);
//			((AdminService) clientSession.getFacade()).updateCustomer(customer);
				return new ResponseEntity<>(adminService.updateCustomer(customer), HttpStatus.OK); //"Customer updated"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update customer by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//Delete Customer
	@DeleteMapping("/deleteCustomer/{token}/{id}")
	public ResponseEntity<?> removeCustomer(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				adminService.removeCustomer(id);
//			((AdminService) clientSession.getFacade()).removeCustomer(id);
				return new ResponseEntity<>(adminService.removeCustomer(id), HttpStatus.OK); //"Customer removed"
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete customer by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//View Customer
	@GetMapping("/viewCustomer/{token}/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getCustomer(id), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getCustomer(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view customer by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	//View All Customers
	@GetMapping("/viewAllCustomers/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getAllCustomers(), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getAllCustomers(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all customers by admin", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

}
