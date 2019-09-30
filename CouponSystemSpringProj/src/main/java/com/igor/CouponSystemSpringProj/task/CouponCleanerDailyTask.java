package com.igor.CouponSystemSpringProj.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;

@Component
public class CouponCleanerDailyTask {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	private boolean stop = false;
	
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!stop) {
					for(Coupon coupon : couponRepository.findExpiredCoupons()) {
						removeCoupon(coupon);
					}
					try {
						Thread.sleep(2000);//(1000*60*60*24)
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Error on delete Expired Coupons" +e.getMessage());
					}
				}
				
			}
		}).start();
	}
	
	private void removeCoupon(Coupon coupon) {
		Company company = companyRepository.findCompanyByCoupon(coupon.getId());
		List<Customer> customers = customerRepository.findCustomersByCoupon(coupon.getId());
		if (company != null) {
			company.getCoupons().remove(coupon);
			companyRepository.save(company);
		}
		for(Customer customer : customers) {
			customer.getCoupons().remove(coupon);
			customerRepository.save(customer);
		}
		couponRepository.delete(coupon);
		System.out.println("Coupon id: " +coupon.getId()+ " title: " +coupon.getTitle()+ " removed by Coupon Cleaner Daily Task");
	}
	
	public void stop() {
		this.stop = true;
	}

}
