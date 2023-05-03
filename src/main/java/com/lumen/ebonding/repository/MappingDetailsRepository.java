package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.CustomerConfiguration;
import com.lumen.ebonding.model.MappingDetail;

/**
 * Repository for managing {@link MappingDetail}
 * 
 * @author Devesh Joshi
 * @since Oct 16, 2020
 *
 */

@Repository
public interface MappingDetailsRepository extends JpaRepository<MappingDetail, Integer>{

	List<MappingDetail> findAllByConfig(CustomerConfiguration config);
	
	List<MappingDetail> findAllByConfigAndActive(CustomerConfiguration config, boolean active);
	
}
