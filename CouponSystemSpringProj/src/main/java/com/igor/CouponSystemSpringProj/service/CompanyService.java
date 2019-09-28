package com.igor.CouponSystemSpringProj.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.exceptions.CouponSystemException;
import com.igor.CouponSystemSpringProj.exceptions.ObjectNotFoundException;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.model.Customer;
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
	
	//Create Coupon
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
	
	//Update Coupon
	public Coupon updateCoupon(Coupon coupon) throws CouponSystemException {
		//check if coupon exists
		coupon = null;
		Optional<Coupon> optional = couponRepository.findById(coupon.getId());
		if (!optional.isPresent()) {
			throw new CouponSystemException("Coupon does not exist");
		} else {
			coupon = optional.get();
		}
		try {
			coupon.setEndDate(coupon.getEndDate());
			coupon.setPrice(coupon.getPrice());
			//update
			couponRepository.save(coupon);
		} catch (Exception e) {
			throw new CouponSystemException("Could not update coupon endDate! ", e);
		}
		return coupon;
	}
	
//	public Coupon updateCouponEndDate(long id, Date endDate) throws CouponSystemException {
//		//check if coupon exists
//		Coupon coupon = null;
//		Optional<Coupon> optional = couponRepository.findById(id);
//		if (!optional.isPresent()) {
//			throw new CouponSystemException("Coupon does not exist");
//		} else {
//			coupon = optional.get();
//		}
//		try {
//			coupon.setEndDate(endDate);
//			//update
//			couponRepository.save(coupon);
//		} catch (Exception e) {
//			throw new CouponSystemException("Could not update coupon endDate! ", e);
//		}
//		return coupon;
//	}
//	
//	public Coupon updateCouponPrice(long id, double price) throws CouponSystemException {
//		//check if coupon exists
//		Coupon coupon = null;
//		Optional<Coupon> optional = couponRepository.findById(id);
//		if (!optional.isPresent()) {
//			throw new CouponSystemException("Coupon does not exist");
//		} else {
//			coupon = optional.get();
//		}
//		try {
//			coupon.setPrice(price);
//			//update
//			couponRepository.save(coupon);
//		} catch (Exception e) {
//			throw new CouponSystemException("Could not update coupon price! ", e);
//		}
//		return coupon;
//	}
	
	//Remove Coupon
	public Coupon removeCoupon(long id) throws CouponSystemException {
		Company company = companyRepository.findCompanyByCoupon(id);
		List<Customer> customers = customerRepository.findCustomersByCoupon(id);
		//check if coupon exists
		Coupon coupon = null;
		Optional<Coupon> optional = couponRepository.findById(id);
		if (!optional.isPresent()) {
			throw new CouponSystemException("Coupon does not exist");
		} else {
			coupon = optional.get();
		}
		try {
			if (company != null) {
				company.getCoupons().remove(coupon);
				companyRepository.save(company);
			}
			for(Customer customer : customers) {
				customer.getCoupons().remove(coupon);
				customerRepository.save(customer);
			}
			couponRepository.delete(coupon);
		} catch (Exception e) {
			throw new CouponSystemException("Could not delete coupon! ", e);
		}
		return coupon;
	}
	
	//Get Coupon
	public Coupon getCoupon(long id) throws Exception {
		Coupon temp = null;
		try {
			Optional<Coupon> optional = couponRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Company failed to get coupon - this coupon id doesn't exist: " + id);
			} else {
				temp = optional.get();
				System.out.println(temp);
//				return temp;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Company failed to get coupon - this coupon id doesn't exist: " + id);
		}
		return temp;
	}
	
	//Get All Coupons
	public List<Coupon> getAllCoupons() throws Exception {
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
				coupons = couponRepository.findCompanyCoupon(company.getId());
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company failed to get all coupons");
		}
//		return null;
	}
	
	//Get Coupon By Type
	public List<Coupon> getAllCouponsByType(CouponType type) throws Exception {
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
				coupons = couponRepository.findCompanyCouponByType(company.getId(), type);
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company failed to get all coupons");
		}
//		return null;
	}
	
	//Get Coupon By Price
	public List<Coupon> getAllCouponsByPrice(double price) throws Exception {
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
//				coupons = couponRepository.findAllByCompanyIdAndPriceLessThanEqual(company.getId(), price);
				coupons = couponRepository.findCompanyCouponByPrice(company.getId(), price);
				for (Coupon coupon: coupons) {
					if (coupon.getPrice() <= price) {
						System.out.println(coupon);
					}
				}
//				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company failed to get all coupons");
		}
//		return null;
	}
	//Get Coupon By Date

}
