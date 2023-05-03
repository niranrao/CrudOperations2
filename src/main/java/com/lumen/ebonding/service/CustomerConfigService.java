package com.lumen.ebonding.service;

import java.util.List;

import com.lumen.ebonding.dto.CustomerConfigurationDTO;
import com.lumen.ebonding.model.Customer;
import com.lumen.ebonding.model.CustomerConfiguration;

/**
 * Service interface for Customer configurations
 * 
 * @author Devesh Joshi
 * @since Oct 13, 2020
 */
public interface CustomerConfigService
		extends GenericService<CustomerConfiguration, Integer, CustomerConfigurationDTO> {

	List<CustomerConfigurationDTO> getConfigByCustomerId(Integer id);
	List<CustomerConfigurationDTO> getCustomerConfigByCustomerIdAndBusinessProcess(Integer customerId,
			String businessProcess) ;
	List<CustomerConfigurationDTO> getCustomerConfigByCustomerName(String customerName);
	 List<CustomerConfigurationDTO> getCustomerConfigByCustNameBPSourceAndTarget(String customerName,
			String businessProcess,String source,String target);
}
