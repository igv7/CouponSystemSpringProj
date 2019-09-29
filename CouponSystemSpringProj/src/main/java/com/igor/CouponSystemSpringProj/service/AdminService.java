package com.igor.CouponSystemSpringProj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.CouponSystemSpringProj.exceptions.ObjectNotFoundException;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Coupon;
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
	//Create Company
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
					System.out.println("Success to add Company: "+company);
				}
			}
		} catch (Exception e) {
			throw new Exception("Cannot create company " + e.getMessage());
		}
		return company;
	}
	
	//Update Company
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
				System.out.println("Success to update Company: "+temp);
			}
		} catch (Exception e) {
			throw new Exception("Cannot update company " + e.getMessage());
		}
		return temp;
	}
	
	//Remove Company
	public Company removeCompany(long id) throws Exception {
		List<Coupon> coupons = couponRepository.findAll();
		Company temp = null;
		try {
			Optional<Company> optional = companyRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to remove company - this company id doesn't exist: " + id);
			} else {
				temp = optional.get();
				couponRepository.deleteCouponsById(temp.getId());//id    //deleteByCompanyId
				couponRepository.saveAll(coupons);
				companyRepository.deleteById(id);//temp.getId()
//				serviceStatus.setSuccess(true);
//				serviceStatus.setMessage("Success, Admin removed company successfully. company id: " + id);
				System.out.println("Admin removed company successfully. company id: " +id+ " company name: " +temp.getName());
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
	
	//Get Company
	public Company getCompany(long id) throws Exception {
		Company temp = null;
		try {
			Optional<Company> optional = companyRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to get company - this company id doesn't exist: " + id);
			} else {
				temp = optional.get();
				System.out.println(temp);
//				return temp;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Admin failed to get company - this company id doesn't exist: " + id);
		}
		return temp;
	}
	
	//Get All Companies
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
	//Create Customer
	public Customer createCustomer(Customer customer) throws Exception {
		try {
			if (customerRepository.existsById(customer.getId())) {
				throw new Exception("Admin failed to add customer - this id already in use " + customer.getId());
			} else {
				if (customerRepository.existsByName(customer.getName())) {
					throw new Exception(
							"Admin failed to add customer - this customer already exists: " + customer.getName());
				} else {
					customerRepository.save(customer);
					System.out.println("Success to add Customer: "+customer);
				}
			}
		} catch (Exception e) {
			throw new Exception("Cannot create customer " + e.getMessage());
		}
		return customer;
	}
	
	//Update Customer
	public Customer updateCustomer(Customer customer) throws Exception {
		Customer temp = null;
		try {
			Optional<Customer> optional = customerRepository.findById(customer.getId());
			if (!optional.isPresent()) {
				throw new Exception("Customer doesn't exist");
			} else {
				temp = optional.get();
//				temp.setName(customer.getName());
				temp.setPassword(customer.getPassword());
				customerRepository.save(temp);
				System.out.println("Success to update Customer: "+temp);
			}
		} catch (Exception e) {
			throw new Exception("Cannot update customer " + e.getMessage());
		}
		return temp;
	}
	
	//Remove Customer
	public Customer removeCustomer(long id) throws Exception {
		List<Coupon> coupons = couponRepository.findAll();//
		Customer temp = null;
		try {
			Optional<Customer> optional = customerRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to remove customer - this customer id doesn't exist: " + id);
			} else {
				temp = optional.get();
				couponRepository.deleteCouponsById(temp.getId());//id    //deleteByCustomerId
				couponRepository.saveAll(coupons);//
				customerRepository.deleteById(temp.getId());//id
//				serviceStatus.setSuccess(true);
//				serviceStatus.setMessage("Success, Admin removed customer successfully. customer id: " + id);
				System.out.println("Admin removed customer successfully. customer id: " + id);
//				return serviceStatus;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
//			serviceStatus.setSuccess(false);
//			serviceStatus.setMessage(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Admin failed to remove customer. customer id: " + id);
		}
		return temp;
	}
	
	//Get Customer
	public Customer getCustomer(long id) throws Exception {
		Customer temp = null;
		try {
			Optional<Customer> optional = customerRepository.findById(id);
			if (!optional.isPresent()) {
				throw new Exception("Admin failed to get customer - this customer id doesn't exist: " + id);
			} else {
				temp = optional.get();
				System.out.println(temp);
//				return temp;
			}
		} catch (ObjectNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Admin failed to get customer - this customer id doesn't exist: " + id);
		}
		return temp;
	}
	
	//Get All Customers
	public List<Customer> getAllCustomers() throws Exception {
		List<Customer> customers = null;//
		try {
			if (customerRepository.findAll().isEmpty()) {
				throw new Exception("Admin failed to get all customers");
			} else {
				customers = customerRepository.findAll(); //List<Customer> customers = customerRepository.findAll();
				System.out.println(customers);
				return customers;
			}
		} catch (Exception e) {
			throw new Exception("Admin failed to get all customers");
		}
//		return null;
	}

}
