package com.igor.CouponSystemSpringProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.model.Income;
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
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	
	public void storeIncome(Income income) throws Exception {
		try {
			incomeRepository.save(income);
			System.out.println("Succsess. Income was stored " +income);
		} catch (Exception e) {
			System.out.println("Failed to store income: " + income);
			e.getMessage();
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
			throw new Exception("Failed to get all incomes");
		}
	}
	
	

}
