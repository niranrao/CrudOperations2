package com.lumen.ebonding.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.NavigationDTO;
import com.lumen.ebonding.model.NavigationMaster;
import com.lumen.ebonding.repository.NavigationRepository;
import com.lumen.ebonding.service.NavigationService;

@Service
public class NavigationServiceImpl extends GenericServiceImpl<NavigationMaster, Integer, NavigationDTO>
		implements NavigationService {

	@Autowired
	private NavigationRepository navigationRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(NavigationServiceImpl.class);

	@Override
	public NavigationMaster toEntity(NavigationDTO dto) {
		NavigationMaster entity = new NavigationMaster();
		mapper.map(dto, entity);

		return entity;
	}

	@Override
	public NavigationDTO toDTO(NavigationMaster entity) {
		NavigationDTO dto = new NavigationDTO();
		mapper.map(entity, dto);

		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return navigationRepository;
	}

	@Override
	public List<NavigationDTO> getAllByActive(boolean active) {
		log.trace("Fetching all the active navigations");
		
		return toDTOList(navigationRepository.findAllByActive(active));
	}

	

}
