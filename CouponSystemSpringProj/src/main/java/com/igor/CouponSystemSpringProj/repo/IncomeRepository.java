package com.igor.CouponSystemSpringProj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.CouponSystemSpringProj.model.Income;
import com.igor.CouponSystemSpringProj.service.ServiceStatus;//

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	public List<Income> findAllByClientId (long clientId);
	
	public void findByIncomeId(long incomeId);
	
	
//	public void storeIncome(Income income) throws Exception;//
//	public void storeIncome(long incomeId);
	
	
//	public List<Income> viewAllIncome() throws Exception;
//	public List<Income> findAll();
	
//	public List<Income> viewIncomeByCustomer(long customerId) throws Exception;
	
//	public List<Income> viewIncomeByCompany(long companyId) throws Exception;

}
