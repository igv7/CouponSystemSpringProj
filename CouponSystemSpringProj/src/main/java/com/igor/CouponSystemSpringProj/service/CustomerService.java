package com.igor.CouponSystemSpringProj.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;
import com.igor.CouponSystemSpringProj.model.Customer;

@Service
//@Scope("prototype")
@Transactional
public class CustomerService implements Facade {

	private long custId;

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

//	//Purchase Coupon
//	public boolean purchaseCoupon(long id) throws Exception {
//		Optional<Coupon> optional = couponRepository.findById(id);
//		if (optional.isPresent()) {
//			Coupon coupon = couponRepository.getOne(id);
//			if (coupon.getAmount()>0) {
//				Customer customer = customerRepository.getOne(custId);
//				coupon.setAmount(coupon.getAmount()-1);
//				customer.getCoupons().add(coupon);
//				customerRepository.save(customer);
//				couponRepository.save(coupon);
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			throw new Exception("Coupon does not exixts");
//		}
//	}
	
	
	
	
	
	

//	// Purchase Coupon
//	public Coupon purchaseCoupon(long id) throws Exception {
//		Customer customer = customerRepository.findById(custId).get();//
////		Coupon coupon = couponRepository.getOne(id);//
////		Coupon coupon = couponRepository.findById(id).get();
//		Optional<Coupon> optional = couponRepository.findById(id);
//		try {
//			Coupon coupon = optional.get();
//			if (!optional.isPresent()) {
//				throw new Exception("This coupon id " + id + " does not exist in data. ");
//			} else {
//				if (couponRepository.existsByTitle(coupon.getTitle())) {
//					throw new Exception("Customer failed to purchase coupon " + coupon.getTitle()
//							+ " - this coupon  already purchased by customer");
//				} else {
//					if (coupon.getAmount() < 1) {
//						throw new Exception("Customer failed to purchase coupon - wrong amount: " + coupon.getAmount());
//					} else {
//						if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
//							throw new Exception("Customer failed to purchase coupon - the end date already passed. "
//									+ coupon.getEndDate());
//						} else {
//
//							if (customer.getCoupons().contains(coupon)) {
//								throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: "
//										+ id + " - already purchased same coupon. ");
//							} else {
////			if (couponRepository.findCustomerCoupon(customer.getId()).equals(optional.get())) { // id instead of optional.get()?
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
////						+ " - already purchased same coupon. ");
////			}
////			if (couponRepository.findById(customer.getId()).get().equals(optional.get())) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
////						+ " - already purchased same coupon. ");
////			}
//
////			if (coupon.getId() == id) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon title: " + id
////						+ " - already purchased same coupon. ");
////			}
////			if (coupon.getTitle()==((Coupon) couponRepository.findCustomerCoupon(customer.getId())).getTitle()) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon title: " + id
////						+ " - already purchased same coupon. ");
////			}
////			if (((Coupon) couponRepository.findCustomerCoupon(customer.getId())).getTitle().equals(coupon.getTitle())) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon title: " + coupon.getTitle()
////						+ " - already purchased same coupon. ");
////			}
//
////			if (coupon.getAmount() < 1) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
////						+ " - wrong amount: " + coupon.getAmount());
////			}
////			if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
////				throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
////						+ " - this coupon has expired: " + coupon.getEndDate());
////			}
//								if (optional.isPresent()) {
//									coupon = couponRepository.getOne(id);
//									if (coupon.getAmount() > 0) {
//										customer = customerRepository.getOne(custId);
//										coupon.setAmount(coupon.getAmount() - 1);
//										customer.getCoupons().add(coupon);
//										customerRepository.save(customer);
//										couponRepository.save(coupon);
//										System.out.println("Coupon id: " + coupon.getId() + " title: "
//												+ coupon.getTitle() + " was purchased by Customer id: "
//												+ customer.getId() + " name: " + customer.getName());
//										return coupon;
//									}
//
//								} 
////								else {
////									throw new Exception("Coupon does not exixts");
////								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw new Exception("Failed to purchase coupon!");
//		}
//		return null;
//
//	}

	// Purchase Coupon
	public Coupon purchaseCoupon(long id) throws Exception {
		Customer customer = customerRepository.findById(custId).get();//
		System.out.println(customer);
		System.out.println(customer.getCoupons());
		Coupon coupon = null;
		Optional<Coupon> optional = couponRepository.findById(id);
		try {
			coupon = optional.get();
			System.out.println(coupon);

			if (coupon.getAmount() < 1) {
				throw new Exception("Customer failed to purchase coupon - wrong amount: " + coupon.getAmount());
			}
				System.out.println("1");
				if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					throw new Exception(
							"Customer failed to purchase coupon - the end date already passed. " + coupon.getEndDate());
				}
					System.out.println("2");
					System.out.println((customer.getCoupons().contains(coupon)));
//					if (customer.getCoupons().contains(coupon)==true) {
//						throw new Exception("Customer " + customer.getName() + " unable to purchase coupon id: " + id
//								+ " - already purchased same coupon. ");
//					} 
						System.out.println("3");
						if (optional.isPresent()) {
							coupon = couponRepository.getOne(id);
							System.out.println("4");
							if (coupon.getAmount() > 0) {
								System.out.println("5");
								customer = customerRepository.getOne(custId);
								System.out.println("6");
								coupon.setAmount(coupon.getAmount() - 1);
								System.out.println("7");
								customer.getCoupons().add(coupon);
								System.out.println("8");
								customerRepository.save(customer);
								System.out.println("9");
								couponRepository.save(coupon);
								System.out.println("10");
								System.out.println("Coupon id: " + coupon.getId() + " title: " + coupon.getTitle()
										+ " was purchased by Customer id: " + customer.getId() + " name: "
										+ customer.getName());
								return coupon;
							}
						} else {
							throw new Exception("Coupon does not exixts");
						}
		} catch (Exception e) {
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
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
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
//			if (couponRepository.findAll().isEmpty()) {
			if (customer.getCoupons().isEmpty()) {
				throw new Exception("Customer " + customer.getName()
						+ " failed to get all purchased coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
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
//			if (couponRepository.findAll().isEmpty()) {
			if (customer.getCoupons().isEmpty()) {
				throw new Exception("Customer " + customer.getName()
						+ " failed to get all purchased coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
//				coupons = couponRepository.findAllByIdAndPriceLessThanEqual(customer.getId(), price);
				coupons = couponRepository.findCustomerCouponByPrice(customer.getId(), price);
//				for (Coupon coupon: coupons) {
//					if (coupon.getPrice() <= price) {
//						System.out.println(coupon);
//					}
//				}
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception(
					"Customer " + customer.getName() + " failed to get all coupons by price until " + price);
		}

	}

}
