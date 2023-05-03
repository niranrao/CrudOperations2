package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.Customer;
import com.lumen.ebonding.model.StaticData;

/**
 * Repository class to manage Customer
 * @author Devesh Joshi
 * @since Oct 12, 2020
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByCustomerName(String name);
}
