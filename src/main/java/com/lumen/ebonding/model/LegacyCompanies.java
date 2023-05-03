package com.lumen.ebonding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ebonding_companies",catalog = "ebonding_sn")
public class LegacyCompanies implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "company_id")
	private int id;
	
	@Column(name= "source_company")
	private String customerName;
	
	@Column(name= "taget_company")
	private String targetCompany;
	
	@Column(name= "provider_name")
	private String providerName;
	
	@Column(name= "status")
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
