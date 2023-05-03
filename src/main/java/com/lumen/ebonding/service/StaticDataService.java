package com.lumen.ebonding.service;

import java.util.List;

import com.lumen.ebonding.dto.StaticDataDTO;
import com.lumen.ebonding.model.StaticData;

/**
 * Service interface for {@link StaticData}
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
public interface StaticDataService extends GenericService<StaticData, Integer, StaticDataDTO> {

	/**
	 * Method to fetch the list of {@link StaticData} based on type
	 * @param type
	 * @return List of {@link StaticDataDTO}
	 */
	List<StaticDataDTO> getAllDataByType(String type);

	/**
	 * Method to fetch the list of {@link StaticData} based on type and status
	 * @param type, active
	 * @return List of {@link StaticDataDTO}
	 */
	List<StaticDataDTO> getAllByTypeAndActive(String type, boolean active);
}
