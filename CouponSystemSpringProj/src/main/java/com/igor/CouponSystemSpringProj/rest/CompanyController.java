package com.igor.CouponSystemSpringProj.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private Map<String, ClientSession> tokensMap;

	private ClientSession isActive(String token) {
		return tokensMap.get(token);
	}
	
	@Autowired
	private CompanyService companyService;
	
	
	@PostMapping("/addCoupon/{token}")
	public ResponseEntity<String> createCoupon(@RequestBody Coupon coupon, @PathVariable("token") String token) {
		ClientSession clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				companyService.createCoupon(coupon);
				return new ResponseEntity<>("Coupon added ", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

}
