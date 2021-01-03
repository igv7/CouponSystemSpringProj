package com.igor.CouponSystemSpringProj.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.enums.ClientType;
import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.enums.IncomeType;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.model.Income;

@Service
//@Scope("prototype")
@Transactional
public class CustomerService implements Facade {

	private long custId;

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	public double sum = 0;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private IncomeService incomeService;


	// Purchase Coupon
	public Coupon purchaseCoupon(long id) throws Exception {
		Customer customer = customerRepository.findById(custId).get();//
		System.out.println("Customer" +customer);
		System.out.println("Customer coupons" +customer.getCoupons());
		Coupon coupon = null;
		Optional<Coupon> optional = couponRepository.findById(id);
		try {
			coupon = optional.get();
			System.out.println("This coupon to purchase" +coupon);

			if (coupon.getAmount() < 1) {
				throw new Exception("Customer failed to purchase coupon - wrong amount: " + coupon.getAmount());
			}
			if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
				throw new Exception(
						"Customer failed to purchase coupon - the end date already passed. " + coupon.getEndDate());
			}
			System.out.println((customer.getCoupons().contains(coupon)));
			if (customer.getCoupons().contains(coupon)) {
				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
						+ " - already purchased same coupon. ");
			}
			if (optional.isPresent()) {
				coupon = couponRepository.getOne(id);
				if (coupon.getAmount() > 0) {
					customer = customerRepository.getOne(custId);
					coupon.setAmount(coupon.getAmount() - 1);
					customer.getCoupons().add(coupon);
					customerRepository.save(customer);
					couponRepository.save(coupon);
					Income income = new Income();
					income.setClientId(customer.getId());
					income.setClientName("Customer " +customer.getName());
					income.setOperationDate(Date.valueOf(LocalDate.now()));
					income.setDescription(IncomeType.CUSTOMER_PURCHASE);
					income.setAmount(coupon.getPrice());
					income.setTotalAmount(sum += coupon.getPrice());
//					income.setTotalCommonAmount(incomeService.totalCommonAmount());
					incomeService.storeIncome(income);
					income.setTotalCommonAmount(incomeService.totalCommonAmount());
					incomeService.storeIncome(income);
					System.out.println("Coupon id: " + coupon.getId() + " title: " + coupon.getTitle()
							+ " was purchased by Customer id: " + customer.getId() + " name: " + customer.getName());
					return coupon;
				}
			} else {
				throw new Exception("Coupon does not exixts");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Failed to purchase coupon!");
		}
		return null;

	}

	// Get All Purchased Coupons
	public List<Coupon> getAllPurchasedCoupons() throws Exception {
		Customer customer = customerRepository.findById(custId).get();
		List<Coupon> coupons = null;
		try {
			if (customer.getCoupons().isEmpty()) {
				throw new Exception("Customer " + customer.getName()
						+ " failed to get all purchased coupons. Coupons do not exist");
			} else {
				coupons = couponRepository.findCustomerCoupon(customer.getId());
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Customer " + customer.getName() + " failed to get all purchased coupons");
		}
	}

	// Get All Purchased Coupons By Type
	public List<Coupon> getAllPurchasedCouponsByType(CouponType type) throws Exception {
		Customer customer = customerRepository.findById(custId).get();
		List<Coupon> coupons = null;//
		try {
			if (customer.getCoupons().isEmpty()) {
				throw new Exception("Customer " + customer.getName()
						+ " failed to get all purchased coupons. Coupons do not exist");
			} else {
				coupons = couponRepository.findCustomerCouponByType(customer.getId(), type);
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception(
					"Customer " + customer.getName() + " failed to get all purchased coupons by type " + type);
		}

	}

	// Get All Purchased Coupons By Price
	public List<Coupon> getAllPurchasedCouponsByPrice(double price) throws Exception {
		Customer customer = customerRepository.findById(custId).get();
		List<Coupon> coupons = null;//
		try {
			if (customer.getCoupons().isEmpty()) {
				throw new Exception("Customer " + customer.getName()
						+ " failed to get all purchased coupons. Coupons do not exist");
			} else {
				coupons = couponRepository.findCustomerCouponByPrice(customer.getId(), price);
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception(
					"Customer " + customer.getName() + " failed to get all coupons by price until " + price);
		}

	}

}
