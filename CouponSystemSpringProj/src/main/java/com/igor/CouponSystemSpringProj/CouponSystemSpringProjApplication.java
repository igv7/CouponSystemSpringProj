package com.igor.CouponSystemSpringProj;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.igor.CouponSystemSpringProj.rest.ClientSession;

@SpringBootApplication//(scanBasePackages="com.igor.CouponSystemSpringProj")
@EnableJpaRepositories//
public class CouponSystemSpringProjApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringProjApplication.class, args);
		SpringApplication.run(CouponSystemSpringProjApplication.class, args);
		System.out.println("Welcome to Coupon System!");
	}
	
	@Bean
	public HashMap<String, ClientSession> getHashMap(){
		return new HashMap<String, ClientSession>();
	}

}
