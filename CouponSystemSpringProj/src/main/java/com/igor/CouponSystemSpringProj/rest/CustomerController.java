package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}
	
	@Autowired
	private CustomerService customerService;
	
	
	//Purchase Coupon
	@PostMapping("/purchaseCoupon/{token}/{id}")
	public ResponseEntity<?> purchaseCoupon(@PathVariable("token") String token, @PathVariable("id") long id) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				customerService.purchaseCoupon(id);
				return new ResponseEntity<>(customerService.purchaseCoupon(id), HttpStatus.OK); //"Success. Coupon purchased by customer"
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Purchased Coupons
	@GetMapping("/viewAllPurchasedCoupons/{token}")
	public ResponseEntity<?> getAllPurchasedCoupons(@PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				customerService.getAllPurchasedCoupons();
				return new ResponseEntity<> (customerService.getAllPurchasedCoupons(), HttpStatus.OK); //"Success on get all purchased coupons"
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Purchased Coupons By Type
	@GetMapping("/viewAllPurchasedCouponsByType/{token}/{type}")
	public ResponseEntity<?> getAllPurchasedCouponsByType(@PathVariable("token") String token, @PathVariable("type") CouponType type) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				customerService.getAllPurchasedCouponsByType(type);
				return new ResponseEntity<> (customerService.getAllPurchasedCouponsByType(type), HttpStatus.OK); //"Success on get customer coupons by type"
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//View All Purchased Coupons By Price
	@GetMapping("/viewAllPurchasedCouponsByPrice/{token}/{price}")
	public ResponseEntity<?> getAllPurchasedCouponsByPrice(@PathVariable("token") String token, @PathVariable("price") double price) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
//				customerService.getAllPurchasedCouponsByPrice(price);
				return new ResponseEntity<> (customerService.getAllPurchasedCouponsByPrice(price), HttpStatus.OK); //"Success on get customer coupons by price"
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

}
