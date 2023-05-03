package com.lumen.ebonding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.CustomerConfigurationDTO;
import com.lumen.ebonding.dto.CustomerDTO;
import com.lumen.ebonding.model.Customer;
import com.lumen.ebonding.model.CustomerConfiguration;
import com.lumen.ebonding.repository.CustomerConfigRepository;
import com.lumen.ebonding.repository.CustomerRepository;
import com.lumen.ebonding.service.CustomerConfigService;

/**
 * Service implementation for customer configurations
 * 
 * @author Devesh Joshi
 * @since Oct 13, 2020
 */

@Service
public class CustomerConfigServiceImpl extends
		GenericServiceImpl<CustomerConfiguration, Integer, CustomerConfigurationDTO> implements CustomerConfigService {

	@Autowired
	private CustomerConfigRepository customerConfigRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerServiceImpl customerService;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public CustomerConfiguration toEntity(CustomerConfigurationDTO dto) {
		CustomerConfiguration configEntity = new CustomerConfiguration();
		mapper.map(dto, configEntity);

		return configEntity;
	}

	@Override
	public CustomerConfigurationDTO toDTO(CustomerConfiguration entity) {
		CustomerConfigurationDTO configDTO = new CustomerConfigurationDTO();
		mapper.map(entity, configDTO);

		return configDTO;
	}

	@Override
	public JpaRepository getRepository() {
		return customerConfigRepository;
	}

	@Override
	public List<CustomerConfigurationDTO> getConfigByCustomerId(Integer id) {
		List<CustomerConfiguration> configs = new ArrayList<CustomerConfiguration>();

		if (getRepository() != null) {
			Customer cust = new Customer();
			cust.setId(id);
			configs = customerConfigRepository.findAllByCustomer(cust);
		}

		return toDTOList(configs);
	}

	@Override
	public List<CustomerConfigurationDTO> getCustomerConfigByCustomerIdAndBusinessProcess(Integer customerId,
			String businessProcess) {
		//CustomerDTO Customer = customerService.get(customerId);
		List<CustomerConfiguration> custConfigDtosList = customerConfigRepository
				.findByCustomerIdAndBusinessProcess(customerId, businessProcess);
		
		 return toDTOList(custConfigDtosList);
		
	}

	@Override
	public List<CustomerConfigurationDTO> getCustomerConfigByCustomerName(String customerName) {
		Customer cust = customerRepository.findByCustomerName(customerName);
		List<CustomerConfiguration> custConfigDtosList = new ArrayList<CustomerConfiguration>();
		if (cust != null) {
			custConfigDtosList = customerConfigRepository.findByCustomerId(cust.getId());
		}
		return toDTOList(custConfigDtosList);
	}

	@Override
	public List<CustomerConfigurationDTO> getCustomerConfigByCustNameBPSourceAndTarget(String customerName,
			String businessProcess,String source,String target) {
		Customer cust = customerRepository.findByCustomerName(customerName);
		List<CustomerConfiguration> custConfigDtosList = customerConfigRepository
				.findByCustomerIdAndBusinessProcessAndSourceAndTarget(cust.getId(), businessProcess,source,target);
		
		 return toDTOList(custConfigDtosList);
		
	}
}
