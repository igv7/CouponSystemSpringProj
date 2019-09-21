package com.igor.CouponSystemSpringProj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;

@Service
@Transactional
public class AdminService implements Facade {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	//for CompanyController

	public Company createCompany(Company company) throws Exception {
		try {
			if (companyRepository.existsById(company.getId())) {
				throw new Exception("Admin failed to add company - this id already in use " + company.getId());
			} else {
				if (companyRepository.existsByName(company.getName())) {
					throw new Exception(
							"Admin failed to add company - this company already exists: " + company.getName());
				} else {
					companyRepository.save(company);
				}
			}
		} catch (Exception e) {
			throw new Exception("Cannot create company " + e.getMessage());
		}
		return company;
	}
	
	public Company updateCompany(Company company) throws Exception {
		Company temp = null;
		Optional<Company> optional = companyRepository.findById(company.getId());
		if (!optional.isPresent()) {
			throw new Exception("Company doesn't exist");
		} else {
			temp = optional.get();
			temp.setName(company.getName());
			temp.setPassword(company.getPassword());
			temp.setEmail(company.getEmail());
			companyRepository.save(temp);
		}
		return temp;
	}
	
	public Company removeCompany(long id) {
		return null;
	}

	public Company getCompany() {
		return null;
	}

	public List<Company> getAllCompanies() {
		return null;
	}
	
	
	//for CustomerController

	public Customer createCustomer() {
		return null;
	}

	public Customer updateCustomer() {
		return null;
	}

	public Customer removeCustomer() {
		return null;
	}

	public Customer getCustomer() {
		return null;
	}

	public List<Customer> getAllCustomers() {
		return null;
	}

}
