package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lumen.ebonding.model.StaticData;

/**
 * Repository for StaticData 
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
@Repository
public interface StaticDataRepository extends JpaRepository<StaticData, Integer> {

	List<StaticData> findAllByName(String name);
	
	List<StaticData> findAllByNameAndActive(String name, boolean active);
}
