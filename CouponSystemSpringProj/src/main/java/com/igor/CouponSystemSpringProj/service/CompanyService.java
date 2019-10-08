package com.igor.CouponSystemSpringProj.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.enums.CouponType;
import com.igor.CouponSystemSpringProj.enums.IncomeType;
import com.igor.CouponSystemSpringProj.exceptions.CouponSystemException;
import com.igor.CouponSystemSpringProj.exceptions.ObjectNotFoundException;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.model.Income;
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
	
	@Autowired
	private IncomeService incomeService;
	
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
							throw new Exception("Company failed to add coupon - the end date already passed. " + coupon.getEndDate());
						} else {
							couponRepository.save(coupon);
							Company company = companyRepository.findById(compId).get();
							company.getCoupons().add(coupon);
							companyRepository.save(company);
							Income income = new Income();
							income.setClientId(company.getId()); //compId
							income.setClientName("Company " + company.getName());
							income.setOperationDate(Date.valueOf(LocalDate.now()));
							income.setDescription(IncomeType.COMPANY_NEW_COUPON);
							income.setAmount(100.0);
							incomeService.storeIncome(income);
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
		Company company = companyRepository.findById(compId).get();
		//check if coupon exists
		Coupon temp = null;
		Optional<Coupon> optional = couponRepository.findById(coupon.getId());
		if (!optional.isPresent()) {
			throw new CouponSystemException("Coupon does not exist");
		} else {
			temp = optional.get();
		}
		try {
			temp.setEndDate(coupon.getEndDate());
			temp.setPrice(coupon.getPrice());
			//update
			couponRepository.save(temp);
			Income income = new Income();
			income.setClientId(company.getId()); //compId
			income.setClientName("Company " + company.getName());
			income.setOperationDate(Date.valueOf(LocalDate.now()));
			income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
			income.setAmount(10.0);
			incomeService.storeIncome(income);
			System.out.println("Success to update Coupon: "+temp);
		} catch (Exception e) {
			throw new CouponSystemException("Could not update coupon endDate! ", e);
		}
		return temp;
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
//				company.getCoupons().remove(coupon.getId());
				companyRepository.save(company);
			}
			for(Customer customer : customers) {
				customer.getCoupons().remove(coupon);
//				customer.getCoupons().remove(coupon.getId());
				customerRepository.save(customer);
			}
//			couponRepository.delete(coupon);
			couponRepository.deleteById(id);
			System.out.println("Success to remove Coupon id: "+id);
		} catch (Exception e) {
			throw new CouponSystemException("Could not delete coupon! ", e);
		}
		return coupon;
	}
	
	//Get Coupon
	public Coupon getCoupon(long id) throws Exception {
		Company company = companyRepository.findById(compId).get();
		Coupon temp = null;
		try {
			Optional<Coupon> optional = couponRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Company " +company.getName()+ " failed to get coupon - this coupon id doesn't exist: " + id);
			} else {
				temp = optional.get();
				System.out.println(temp);
//				return temp;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Company " +company.getName()+ " failed to get coupon - this coupon id doesn't exist: " + id);
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
				throw new Exception("Company " +company.getName()+ " failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
				coupons = couponRepository.findCompanyCoupon(company.getId());
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company " +company.getName()+ " failed to get all coupons");
		}
//		return null;
	}
	
	//Get All Coupons By Type
	public List<Coupon> getAllCouponsByType(CouponType type) throws Exception {
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company " +company.getName()+ " failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
				coupons = couponRepository.findCompanyCouponByType(company.getId(), type);
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company " +company.getName()+ " failed to get all coupons by type " +type);
		}
//		return null;
	}
	
	//Get All Coupons By Price
	public List<Coupon> getAllCouponsByPrice(double price) throws Exception {
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company " +company.getName()+ " failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
//				coupons = couponRepository.findAllByIdAndPriceLessThanEqual(company.getId(), price);
				coupons = couponRepository.findCompanyCouponByPrice(company.getId(), price);
//				coupons = couponRepository.findCompanyCoupon(company.getId());
//				for (Coupon coupon: coupons) {
//					if (coupon.getPrice() <= price) {
//						System.out.println(coupon);
//					}
//				}
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company " +company.getName()+ " failed to get all coupons by price until " +price);
		}
//		return null;
	}
	
	//Get All Coupons By Date
	public List<Coupon> getAllCouponsByDate(Date endDate) throws Exception { //endDate
		Company company = companyRepository.findById(compId).get();
		List<Coupon> coupons = null;//
		try {
//			if (couponRepository.findAll().isEmpty()) {
//			if (couponRepository.findCompanyCoupon(company.getId() == 0)) {
			if (company.getCoupons().isEmpty()) {
				throw new Exception("Company " +company.getName()+ " failed to get all coupons. Coupons do not exist");
			} else {
//				coupons = couponRepository.findAll(); //List<Coupon> coupons = couponRepository.findAll();
//				coupons = couponRepository.findAllByIdAndEndDateLessThanEqual(company.getId(), untilDate);
				coupons = couponRepository.findCompanyCouponByEndDate(company.getId(), endDate);
				
				System.out.println(coupons);
				return coupons;
			}
		} catch (Exception e) {
			throw new Exception("Company " +company.getName()+ " failed to get all couponsby date until " +endDate);
		}
//		return null;
	}
	

}
