package com.igor.CouponSystemSpringProj.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;

@Component
public class ScheduledTasks {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 1000*30)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		System.out.println("About to delete : "+ couponRepository.findExpiredCoupons());
		for (Coupon coupon : couponRepository.findExpiredCoupons()) {
			removeCoupon(coupon);
		}
	}

	private void removeCoupon(Coupon coupon) {
		Company company = companyRepository.findCompanyByCoupon(coupon.getId());
		List<Customer> customers = customerRepository.findCustomersByCoupon(coupon.getId());
		if (company != null) {
			company.getCoupons().remove(coupon);
			companyRepository.save(company);
		}
		for (Customer customer : customers) {
			customer.getCoupons().remove(coupon);
			customerRepository.save(customer);
		}
		couponRepository.delete(coupon);
		System.out.println("Coupon id: " + coupon.getId() + " title: " + coupon.getTitle()
				+ " expired and removed by Coupon Cleaner Daily Task");
	}
}
