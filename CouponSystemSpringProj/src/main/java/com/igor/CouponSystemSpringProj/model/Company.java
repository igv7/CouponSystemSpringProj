package com.igor.CouponSystemSpringProj.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table
//@JsonIgnoreProperties(ignoreUnknown = true, value = {"coupons"})
@JsonIgnoreProperties(value = {"coupons"})
public class Company {
	
	private long id;
	private String name;
	private String password;
	private String email;
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
	public Company() {
		
	}

	public Company(long id, String name, String password, String email, List<Coupon>coupons) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", updatable = true, nullable = false, unique = true) //unique = true
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "password", updatable = true, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "email", updatable = true, nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany
//	@OneToMany(fetch = FetchType.LAZY)//fetch = FetchType.EAGER//, mappedBy = "company"
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", coupons="
				+ coupons + "]";
	}

	
	

}
