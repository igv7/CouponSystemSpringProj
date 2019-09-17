package com.igor.CouponSystemSpringProj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.igor.CouponSystemSpringProj.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findCustomerByNameAndPassword(String userName, String password);
	
	@Query("SELECT customer FROM Customer as customer join customer.coupons As c WHERE c.id=:id")
	List<Customer> findCustomersByCoupon(long id);

}
