package com.igor.CouponSystemSpringProj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.exceptions.ObjectNotFoundException;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CouponRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;
//import com.igor.CouponSystemSpringProj.service.ServiceStatus;//

@Service
@Transactional
public class AdminService implements Facade {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
//	@Autowired
//	private ServiceStatus serviceStatus;//
	
	
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
		try {
			Optional<Company> optional = companyRepository.findById(company.getId());
			if (!optional.isPresent()) {
				throw new Exception("Company doesn't exist");
			} else {
				temp = optional.get();
//				temp.setName(company.getName());
				temp.setPassword(company.getPassword());
				temp.setEmail(company.getEmail());
				companyRepository.save(temp);
			}
		} catch (Exception e) {
			throw new Exception("Cannot update company " + e.getMessage());
		}
		return temp;
	}
	
	public Company removeCompany(long id) throws Exception {
		Company temp = null;
		try {
			Optional<Company> optional = companyRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to remove company - this company id doesn't exist: " + id);
			} else {
				temp = optional.get();
				couponRepository.deleteCouponsById(temp.getId());//id    //deleteByCompanyId
				companyRepository.deleteById(temp.getId());//id
//				serviceStatus.setSuccess(true);
//				serviceStatus.setMessage("Success, Admin removed company successfully. company id: " + id);
				System.out.println("Admin removed company successfully. company id: " + id);
//				return serviceStatus;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
//			serviceStatus.setSuccess(false);
//			serviceStatus.setMessage(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Admin failed to remove company. company id: " + id);
		}
		return temp;
	}

	public Company getCompany(long id) throws Exception {
		Company temp = null;
		try {
			Optional<Company> optional = companyRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to get company - this company id doesn't exist: " + id);
			} else {
				temp = optional.get();
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Admin failed to get company - this company id doesn't exist: " + id);
		}
		return temp;
	}

	public List<Company> getAllCompanies() throws Exception {
		List<Company> companies = null;//
		try {
			if (companyRepository.findAll().isEmpty()) {
				throw new Exception("Admin failed to get all companies");
			} else {
				companies = companyRepository.findAll(); //List<Company> companies = companyRepository.findAll();
				System.out.println(companies);
				return companies;
			}
		} catch (Exception e) {
			throw new Exception("Admin failed to get all companies");
		}
//		return null;
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
