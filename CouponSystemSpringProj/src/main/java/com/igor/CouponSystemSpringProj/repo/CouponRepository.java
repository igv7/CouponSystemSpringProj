package com.igor.CouponSystemSpringProj.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.igor.CouponSystemSpringProj.model.Coupon;
import com.igor.CouponSystemSpringProj.enums.CouponType;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	public boolean existsByTitle(String title);
	
//	public Coupon deleteCouponsById(long id);
	
	public List<Coupon> deleteCouponsById(long id);
	
//	public boolean findCompanyCoupon(boolean b);
	
	public List<Coupon> findAllById(long id); 
	
	public List<Coupon> findAllByIdAndType(long id, CouponType type);
	
	public List<Coupon> findAllByIdAndPriceLessThanEqual(long id, double priceTop);
	
	public List<Coupon> findAllByIdAndEndDateLessThanEqual(long id, Date untilDate);
	
	
	
	//for CompanyService
	@Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id")
	public List<Coupon> findCompanyCoupon(long id);
		
	@Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id AND c.type=:type")
	public List<Coupon> findCompanyCouponByType(long id, CouponType type);
		
	@Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id AND c.price=:price")
	public List<Coupon> findCompanyCouponByPrice(long id, double price);
		
	@Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id AND c.endDate=:endDate")
	public List<Coupon> findCompanyCouponByEndDate(long id, Date endDate);
	
	
	
	//for CustomerService
	@Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:id")
	public List<Coupon> findCustomerCoupon(long id);
		
	@Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:id AND c.type=:type")
	public List<Coupon> findCustomerCouponByType(long id, CouponType type);
		
	@Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:id AND c.price=:price")
	public List<Coupon> findCustomerCouponByPrice(long id, double price);

	
	
	
	//for Expired Coupons
	@Query("SELECT c from Coupon As c WHERE c.endDate <= CURRENT_DATE")
	public List<Coupon> findExpiredCoupons();
		
		
	
}
