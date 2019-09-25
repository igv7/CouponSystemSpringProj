package com.igor.CouponSystemSpringProj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.igor.CouponSystemSpringProj.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	public Coupon deleteCouponsById(long id);
	
	//for Expired Coupons
		@Query("SELECT c from Coupon As c WHERE c.endDate <= CURRENT_DATE")
		List<Coupon> findExpiredCoupons();
		
		public boolean existsByTitle(String title);

}
