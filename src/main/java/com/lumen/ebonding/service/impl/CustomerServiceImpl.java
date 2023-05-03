package com.lumen.ebonding.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.CustomerDTO;
import com.lumen.ebonding.model.Customer;
import com.lumen.ebonding.repository.CustomerRepository;
import com.lumen.ebonding.service.CustomerService;

/**
 * Service implementation class for customer operations
 * 
 * @author Devesh Joshi
 * @since Oct 13, 2020
 */
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer, CustomerDTO> implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public Customer toEntity(CustomerDTO dto) {
		Customer customerEntity = new Customer();
		mapper.map(dto, customerEntity);

		return customerEntity;
	}

	@Override
	public CustomerDTO toDTO(Customer entity) {
		CustomerDTO customerDTO = new CustomerDTO();
		mapper.map(entity, customerDTO);

		return customerDTO;
	}

	@Override
	public JpaRepository getRepository() {
		return customerRepository;
	}

}
