package com.igor.CouponSystemSpringProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.model.Income;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;
import com.igor.CouponSystemSpringProj.repo.IncomeRepository;

@Service
//@Scope("prototype")
@Transactional
public class IncomeService implements Facade {
	
//	private long incomeId;
//
//	public void setIncomeId(long incomeId) {
//		this.incomeId = incomeId;
//	}
	
	private long compId;

	public void setCompId(long compId) {
		this.compId = compId;
	}
	
	private long custId;

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public void storeIncome(Income income) throws Exception {
		try {
			incomeRepository.save(income);
			System.out.println("Succsess. Income was stored " +income);
		} catch (Exception e) {
			System.out.println("Failed to store income: " + income);
			throw new Exception("Failed to store income: " + e.getMessage());
		}
	}
	
	public List<Income> viewAllIncome() throws Exception {
		List<Income> incomes = null;
		try {
			if (incomeRepository.findAll().isEmpty()) {
				throw new Exception("Failed to get all incomes! Data is empty.");
			} else {
				incomes = incomeRepository.findAll();
				System.out.println(incomes);
				return incomes;
			}
		} catch (Exception e) {
			throw new Exception("Failed to get all incomes " + e.getMessage());
		}
	}
	
	public List<Income> viewIncomeByCompany(long id) throws Exception { //compId
		Company company = companyRepository.findById(compId).get();
		List<Income> incomesByCompany = null;
		try {
			if (incomeRepository.findAllByClientId(company.getId()).isEmpty()) {
				throw new Exception("Failed to get all incomes by company! Data is empty.");
			} else {
				incomesByCompany = incomeRepository.findAllByClientId(company.getId());
				System.out.println(incomesByCompany);
				return incomesByCompany;
			}
		} catch (Exception e) {
			throw new Exception("Failed to get all incomes by company " + e.getMessage());
		}
	}
	
	public List<Income> viewIncomeByCustomer(long id) throws Exception { //custId
		Customer customer = customerRepository.findById(custId).get();
		List<Income> incomesByCustomer = null;
		try {
			if (incomeRepository.findAllByClientId(customer.getId()).isEmpty()) {
				throw new Exception("Failed to get all incomes by customer! Data is empty.");
			} else {
				incomesByCustomer = incomeRepository.findAllByClientId(customer.getId());
				System.out.println(incomesByCustomer);
				return incomesByCustomer;
			}
		} catch (Exception e) {
			throw new Exception("Failed to get all incomes by customer " + e.getMessage());
		}
	}

}
