package com.igor.CouponSystemSpringProj;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.igor.CouponSystemSpringProj.model.Income;
import com.igor.CouponSystemSpringProj.rest.ClientSession;
import com.igor.CouponSystemSpringProj.service.IncomeService;

@SpringBootApplication//(scanBasePackages="com.igor.CouponSystemSpringProj")
@EnableJpaRepositories//
@EnableScheduling
public class CouponSystemSpringProjApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringProjApplication.class, args);
		SpringApplication.run(CouponSystemSpringProjApplication.class, args);
		System.out.println("Welcome to the Coupon System!");
		
//		IncomeService incomeService = context.getBean(IncomeService.class);
//		System.out.println("*********************totalAmount***********************");
//		System.out.println(incomeService.totalCommonAmount());
	}
	
//	@Bean
//	public HashMap<String, ClientSession> getHashMap(){
//		return new HashMap<String, ClientSession>();
//	}

}
