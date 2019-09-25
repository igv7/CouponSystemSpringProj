package com.igor.CouponSystemSpringProj.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.exceptions.CouponSystemException;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;

@Service
@Transactional
public class CompanyService implements Facade {
	
	private long compId;
	public void setCompId(long compId) {
		this.compId = compId;
	}
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	public Coupon createCoupon(Coupon coupon) throws CouponSystemException {
		try {
			if (couponRepository.existsById(coupon.getId())) {
				throw new Exception("Company failed to add coupon - this id already in use: " + coupon.getId());
			} else {
				if (couponRepository.existsByTitle(coupon.getTitle())) {
					throw new Exception("Company failed to add coupon - this coupon already exists: " + coupon.getTitle());
				} else {
					if (coupon.getAmount() < 1) {
						throw new Exception("Company failed to add coupon - wrong amount: " + coupon.getAmount());
					} else {
						if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
							throw new Exception("Company failed to add coupon - the end date already passed. " + coupon.getAmount());
						} else {
							couponRepository.save(coupon);
							Company company = companyRepository.findById(compId).get();
							company.getCoupons().add(coupon);
							companyRepository.save(company);
							System.out.println("Success to add Coupon: "+ coupon);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("Cannot create coupon " + e.getMessage());
		}
		return coupon;
	}

}
