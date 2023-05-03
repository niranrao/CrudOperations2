package com.lumen.ebonding.dto;

import javax.persistence.Column;

public class LegacyCompaniesDTO {


	private int id;
	
	
	private String customerName;
	
	
	private String targetCompany;
	
	
	private String providerName;
	
	
	private String status;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getTargetCompany() {
		return targetCompany;
	}


	public void setTargetCompany(String targetCompany) {
		this.targetCompany = targetCompany;
	}


	public String getProviderName() {
		return providerName;
	}


	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
