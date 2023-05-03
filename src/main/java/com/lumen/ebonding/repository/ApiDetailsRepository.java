package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.ApiDetail;
import com.lumen.ebonding.model.CustomerConfiguration;

@Repository
public interface ApiDetailsRepository extends JpaRepository<ApiDetail, Integer>{
	
	List<ApiDetail> findAllByConfig(CustomerConfiguration config);
	
	List<ApiDetail> findAllByConfigAndActive(CustomerConfiguration config, boolean active);

}
