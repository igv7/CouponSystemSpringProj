package com.igor.CouponSystemSpringProj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.igor.CouponSystemSpringProj.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public Company findCompanyByNameAndPassword(String userName, String password);
	
	@Query("SELECT company from Company as company join company.coupons As c WHERE c.id=:id")
	public Company findCompanyByCoupon(long id);

}
