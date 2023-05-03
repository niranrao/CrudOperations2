package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lumen.ebonding.model.LegacyCompanies;
import com.lumen.ebonding.model.LegacyEbondingHistory;

public interface LegacyCompaniesRepository extends JpaRepository<LegacyCompanies, Integer>{

	@Query(nativeQuery = true,value = "select company_id,source_company,taget_company,provider_name,status from ebonding_sn.ebonding_companies")
	public List<LegacyCompanies> getCustomerNames();
	
	@Query(nativeQuery = true,value = "select taget_company from ebonding_sn.ebonding_companies where source_company = :customerName")
	public String getTargetCustomers(@Param("customerName") String customerName);

}
