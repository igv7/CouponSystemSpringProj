package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/addCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable("token") String token) {
//		System.out.println("a1");
		ClientSession clientSession = isActive(token);
//		System.out.println("a2");
//		System.out.println(tokensMap.size());
//		System.out.println("a3");
//		System.out.println(clientSession.getFacade()+" "+clientSession.getLastAccessed());
//		System.out.println("a4");
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

	@PutMapping("/updateCompany/{token}/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable("token") String token, @PathVariable long id) {
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

	@GetMapping("/viewCompany/{token}/{id}")
	public ResponseEntity<?> getCompany(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getCompany(id), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getCompany(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@GetMapping("/viewAllCompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getAllCompanies(), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getAllCompanies(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}
	
	
	//for Customer

	@PostMapping("/addCustomer/{token}")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.createCustomer(customer);
//			((AdminService) clientSession.getFacade()).createCustomer(customer);
				return new ResponseEntity<>("Customer added", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@PutMapping("/updateCustomer/{token}/{id}")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable("token") String token, @PathVariable long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.updateCustomer(customer);
//			((AdminService) clientSession.getFacade()).updateCustomer(customer);
				return new ResponseEntity<>("Customer updated", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@DeleteMapping("/deleteCustomer/{token}/{id}")
	public ResponseEntity<String> removeCustomer(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				adminService.removeCustomer(id);
//			((AdminService) clientSession.getFacade()).removeCustomer(id);
				return new ResponseEntity<>("Customer removed", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@GetMapping("/viewCustomer/{token}/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getCustomer(id), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getCustomer(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

	@GetMapping("/viewAllCustomers/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getAllCustomers(), HttpStatus.OK);
//				return new ResponseEntity<> (((AdminService) clientSession.getFacade()).getAllCustomers(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED); // GATEWAY_TIMEOUT
		}
	}

}
