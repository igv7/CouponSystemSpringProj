package com.igor.CouponSystemSpringProj.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igor.CouponSystemSpringProj.enums.IncomeType;


@Entity
@Table
public class Income {
	
	private long incomeId;
	private long clientId;
	private String clientName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date operationDate = Date.valueOf(LocalDate.now());
	private IncomeType description;
	private double amount;
	private double totalAmount;
	private double totalCommonAmount;

	public Income() {
		
	}

	public Income(long incomeId, String clientName, long clientId, Date operationDate,
			IncomeType description, double amount, double totalAmount, double totalCommonAmount) {
		this.incomeId = incomeId;
		this.clientName = clientName;
		this.clientId = clientId;
		this.operationDate = operationDate;
		this.description = description;
		this.amount = amount;
		this.totalAmount = totalAmount;
		this.totalCommonAmount = totalCommonAmount;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "incomeId", updatable = false, nullable = false)
	public long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}

	@Column(name = "clientName", updatable = true, nullable = false)
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name = "clientId", updatable = true, nullable = false)
	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	@Column(name = "operationDate", updatable = true, nullable = false)
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "description", updatable = true, nullable = false)
	public IncomeType getDescription() {
		return description;
	}

	public void setDescription(IncomeType description) {
		this.description = description;
	}

	@Column(name = "amount", updatable = true, nullable = false)
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name = "totalAmount", updatable = true, nullable = false)
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name = "totalCommonAmount", updatable = true, nullable = false)
	public double getTotalCommonAmount() {
		return totalCommonAmount;
	}

	public void setTotalCommonAmount(double totalCommonAmount) {
		this.totalCommonAmount = totalCommonAmount;
	}

	@Override
	public String toString() {
		return "Income [incomeId=" + incomeId + ", clientName=" + clientName + ", clientId=" + clientId
				+ ", operationDate=" + operationDate + ", description=" + description + ", amount=" + amount 
				+ ", totalAmount=" + totalAmount + ", totalCommonAmount=" + totalCommonAmount +"]";
	}
	

}
