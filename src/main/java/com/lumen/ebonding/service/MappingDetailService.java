package com.lumen.ebonding.service;

import java.util.List;

import com.lumen.ebonding.dto.MappingDetailDTO;
import com.lumen.ebonding.model.MappingDetail;

/**
 * Service interface for {@link MappingDetail}
 * 
 * @author Devesh Joshi
 * @since Oct 16, 2020
 *
 */

public interface MappingDetailService extends GenericService<MappingDetail, Integer, MappingDetailDTO>{

	/**
	 * Method to fetch all the mapping details by configid 
	 * @param configId
	 * @return List of {@link MappingDetailDTO}
	 */
	
	List<MappingDetailDTO> getMappingsByConfigId(Integer configId);
	
	/**
	 * Method to fetch all the active mapping detials for configuration 
	 * @param configId
	 * @return List of {@link MappingDetailDTO}
	 */
	List<MappingDetailDTO> getActiveMappingsByConfigId(Integer configId);
}
