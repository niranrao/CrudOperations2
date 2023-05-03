package com.lumen.ebonding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.MappingDetailDTO;
import com.lumen.ebonding.model.CustomerConfiguration;
import com.lumen.ebonding.model.MappingDetail;
import com.lumen.ebonding.repository.CustomerConfigRepository;
import com.lumen.ebonding.repository.MappingDetailsRepository;
import com.lumen.ebonding.service.MappingDetailService;

/**
 * Service implementation for {@link MappingDetailService}
 * 
 * @author Devesh Joshi
 * @since Oct 16, 2020
 */

@Service
public class MappingDetailServiceImpl extends GenericServiceImpl<MappingDetail, Integer, MappingDetailDTO>
		implements MappingDetailService {

	@Autowired
	private MappingDetailsRepository mappingDetailsRepository;
	
	@Autowired
	private CustomerConfigRepository customerConfigRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(MappingDetailServiceImpl.class);

	@Override
	public MappingDetail toEntity(MappingDetailDTO dto) {
		MappingDetail entity = new MappingDetail();
		mapper.map(dto, entity);

		return entity;
	}

	@Override
	public MappingDetailDTO toDTO(MappingDetail entity) {
		MappingDetailDTO dto = new MappingDetailDTO();
		mapper.map(entity, dto);

		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return mappingDetailsRepository;
	}

	@Override
	public List<MappingDetailDTO> getMappingsByConfigId(Integer configId) {
		log.trace("Fetching all mappings for config id: " + configId);

		CustomerConfiguration config = new CustomerConfiguration();
		config = customerConfigRepository.getOne(configId);

		List<MappingDetail> mappingDetials = mappingDetailsRepository.findAllByConfig(config);

		return toDTOList(mappingDetials);
	}

	@Override
	public List<MappingDetailDTO> getActiveMappingsByConfigId(Integer configId) {
		log.trace("Fetching all active mappings for config id: " + configId);

		CustomerConfiguration config = new CustomerConfiguration();
		config = customerConfigRepository.getOne(configId);

		List<MappingDetail> mappingDetials = mappingDetailsRepository.findAllByConfigAndActive(config, true);

		return toDTOList(mappingDetials);
	}
	

	
	@Override
	public List<MappingDetailDTO> saveOrUpdate(List<MappingDetailDTO> dtoList) {
		if (dtoList != null && dtoList.get(0) != null && dtoList.get(0).getConfig() != null) {
			updateRemoved(dtoList.get(0).getConfig().getId(), dtoList);	
		}
		return super.saveOrUpdate(dtoList);
	}

	void updateRemoved(Integer configId, List<MappingDetailDTO> mappings) {
		log.trace("Removing the mapping which were removed by the user");

		List<Integer> configMappings = new ArrayList<Integer>();
		CustomerConfiguration config = customerConfigRepository.getOne(configId);
		
		List<MappingDetail> mappingDetails = mappingDetailsRepository.findAllByConfig(config);
		
		if(mappings != null) {
			for (MappingDetailDTO mapping: mappings) {
				if (mapping != null && mapping.getId() != null)
					configMappings.add(mapping.getId());
			}
		}
		
		if(mappingDetails != null) {
			for (MappingDetail mapping : mappingDetails) {
				if(!configMappings.contains(mapping.getId())) {
					mappingDetailsRepository.delete(mapping.getId());
				}
			}
		}		
	}

}
