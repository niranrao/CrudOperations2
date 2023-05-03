package com.lumen.ebonding.service;

import java.util.List;

import com.lumen.ebonding.dto.NavigationDTO;
import com.lumen.ebonding.model.NavigationMaster;

/**
 * Service interface for {@link NavigationMaster}
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
public interface NavigationService extends GenericService<NavigationMaster, Integer, NavigationDTO> {

	/**
	 * Method to fetch the list of {@link NavigationMaster} based on active flag
	 * 
	 * @param active
	 * @return List of {@link NavigationDTO}
	 */
	List<NavigationDTO> getAllByActive(boolean active);
}
