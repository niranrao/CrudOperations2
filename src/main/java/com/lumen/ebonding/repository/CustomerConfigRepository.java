package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.Customer;
import com.lumen.ebonding.model.CustomerConfiguration;

/**
 * Repository interface for Customer configurations
 * 
 * @author Devesh Joshi
 * @since Oct 13, 2020
 */
@Repository
public interface CustomerConfigRepository extends JpaRepository<CustomerConfiguration, Integer> {

	List<CustomerConfiguration> findAllByCustomer(Customer cust);
	
//	@Query("SELECT new com.lumen.ebonding.dto.CustomerConfigurationDTO(o.custConfigId,o.businessProcess,o.action,o.source,o.target,o.modifedBy,o.modifiedDate,o.status,o.customerName,o.ciClass, o.ciType,o.customerId,o.customerUserId,o.customerPassword,o.ciTypeSubType,o.ticketSysCompanyName,o.srcToolDateFormat,o.srcTicketCreateDateField,o.srcTicketUpdateDateField,o.sbComponent,o.targetToolUserId,o.targetUserId,o.targetPassword,"
//			+ "o.srcTicketIdField,o.srcExternalIdField,o.targetTicketIdField,o.targetExternalIdField) FROM conf_customer_configuration o where o.customerId = :customerId AND o.businessProcess= :businessProcess AND o.status='Active'" )
//	List<CustomerConfiguration>	getCustomerConfigByCustomerIdAndBusinessProcess(Integer customerId,
//			String businessProcess);
//	
	//@Query("SELECT new com.lumen.ebonding.dto.CustomerConfigurationDTO(o.custConfigId) FROM conf_customer_configuration o where o.customerName = :customerName")
//	List<CustomerConfiguration>	getCustomerConfigByCustomerName(Customer customerName);
	List<CustomerConfiguration> findByCustomerId(Integer id);
	
	List<CustomerConfiguration> findByCustomerIdAndBusinessProcess(Integer id, String bp);
	List<CustomerConfiguration> findByCustomerIdAndBusinessProcessAndSourceAndTarget(Integer id, String bp,String source,String target);
}
