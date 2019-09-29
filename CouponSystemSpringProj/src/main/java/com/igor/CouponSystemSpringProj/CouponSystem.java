package com.igor.CouponSystemSpringProj;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.igor.CouponSystemSpringProj.enums.ClientType;
import com.igor.CouponSystemSpringProj.model.Company;
import com.igor.CouponSystemSpringProj.model.Customer;
import com.igor.CouponSystemSpringProj.repo.CompanyRepository;
import com.igor.CouponSystemSpringProj.repo.CustomerRepository;
import com.igor.CouponSystemSpringProj.service.AdminService;
import com.igor.CouponSystemSpringProj.service.CompanyService;
import com.igor.CouponSystemSpringProj.service.CustomerService;
import com.igor.CouponSystemSpringProj.service.Facade;
import com.igor.CouponSystemSpringProj.task.CouponCleanerDailyTask;
import com.igor.CouponSystemSpringProj.task.SessionTimeoutHandler;

@Service
public class CouponSystem {
	
//	@Autowired
//	private ConfigurableApplicationContext context;
	
	@Autowired
	private ApplicationContext context;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponCleanerDailyTask task;

	@Autowired
	private SessionTimeoutHandler sessionTask;
	
	@Autowired
	private CustomerService customerService;

	@PostConstruct
	public void init() {
		System.out.println("Coupon Cleaner Daily Task in ACTION...");
		task.start();
		sessionTask.start();
	}

	@PreDestroy
	public void destroy() {
		System.out.println("The system is shut down.");
		task.stop();
		sessionTask.stop();
//		context.close();
	}

	public Facade login(String userName, String password, ClientType type) throws Exception { //throws CouponSystemException
		switch (type) {
		case ADMIN:
			if (userName.equals("admin") && password.equals("1234")) {
				System.out.println("Welcome Admin! You're logged in system");
				return adminService;
			}
		case COMPANY:
			Company company = companyRepository.findCompanyByNameAndPassword(userName, password);
			if (company != null) {
				CompanyService companyService = context.getBean(CompanyService.class);
				companyService.setCompId(company.getId());
				System.out.println("Welcome " +company.getName()+ " company! You're logged in system");
				return companyService;
			}
		case CUSTOMER:
			Customer customer = customerRepository.findCustomerByNameAndPassword(userName, password);
			if (customer != null) {
				CustomerService customerService = context.getBean(CustomerService.class);
				customerService.setCustId(customer.getId());
				System.out.println("Welcome customer " +customer.getName()+ "! You're logged in system");
				return customerService;
			}
		}
		throw new Exception("Client not found. Check your data");//throw new CouponSystemException
	}

}
