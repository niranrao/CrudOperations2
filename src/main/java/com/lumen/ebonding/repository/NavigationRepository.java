package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.NavigationMaster;

/**
 * Integer for Navigation Repository
 * 
 * @author Devesh Joshi
 * @since Oct 19, 2020
 *
 */
@Repository
public interface NavigationRepository extends JpaRepository<NavigationMaster, Integer> {
	
	List<NavigationMaster> findAllByActive(Boolean active); 
}
