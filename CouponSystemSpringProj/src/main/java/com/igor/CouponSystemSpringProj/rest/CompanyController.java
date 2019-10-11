package com.igor.CouponSystemSpringProj.rest;

import java.sql.Date;
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

import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.service.CompanyService;
import com.igor.CouponSystemSpringProj.service.IncomeService;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {
	
	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private IncomeService incomeService;
	
	
	//Add Coupon
	@PostMapping("/addCoupon/{token}")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				companyService.createCoupon(coupon);
				return new ResponseEntity<>(companyService.createCoupon(coupon), HttpStatus.OK); //"Coupon added "
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add coupon by company", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Update Coupon
	@PutMapping("/updateCoupon/{token}/{id}")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				companyService.updateCoupon(coupon);
				return new ResponseEntity<>(companyService.updateCoupon(coupon), HttpStatus.OK); //"Coupon updated "
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update coupon by company", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Delete Coupon
	@DeleteMapping("/deleteCoupon/{token}/{id}")
	public ResponseEntity<?> removeCoupon(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				companyService.removeCoupon(id);
				return new ResponseEntity<>(companyService.removeCoupon(id), HttpStatus.OK); //"Coupon removed "
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete coupon by company", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View Coupon
	@GetMapping("/viewCoupon/{token}/{id}")
	public ResponseEntity<?> getCoupon(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.getCoupon(id), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view company coupon", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Coupons
	@GetMapping("/viewAllCoupons/{token}")
	public ResponseEntity<?> getAllCoupons(@PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (companyService.getAllCoupons(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all company coupons", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Coupons By Type
	@GetMapping("/viewAllCouponsByType/{token}/{type}")
	public ResponseEntity<?> getAllCouponsByType(@PathVariable("token") String token, @PathVariable("type") CouponType type) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (companyService.getAllCouponsByType(type), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all company coupons by type", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Coupons By Price
	@GetMapping("/viewAllCouponsByPrice/{token}/{price}")
	public ResponseEntity<?> getAllCouponsByPrice(@PathVariable("token") String token, @PathVariable("price") double price) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (companyService.getAllCouponsByPrice(price), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all company coupons by price", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Coupons By Date
	@GetMapping("/viewAllCouponsByDate/{token}/{endDate}")
	public ResponseEntity<?> getAllCouponsByDate(@PathVariable("token") String token, @PathVariable("endDate") Date endDate) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (companyService.getAllCouponsByDate(endDate), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view all company coupons by date", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View Income By Company
	@GetMapping("/viewIncomeByCompany/{token}/{id}")
	public ResponseEntity<?> viewIncomeByCompany(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (incomeService.viewIncomeByCompany(id), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to view income by company ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

}
