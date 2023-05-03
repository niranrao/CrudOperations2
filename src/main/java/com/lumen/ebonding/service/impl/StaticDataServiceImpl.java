package com.lumen.ebonding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.StaticDataDTO;
import com.lumen.ebonding.model.StaticData;
import com.lumen.ebonding.repository.StaticDataRepository;
import com.lumen.ebonding.service.StaticDataService;

@Service
public class StaticDataServiceImpl extends GenericServiceImpl<StaticData, Integer, StaticDataDTO>
		implements StaticDataService {

	@Autowired
	private StaticDataRepository staticDataRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(StaticDataServiceImpl.class);

	@Override
	public StaticData toEntity(StaticDataDTO dto) {
		StaticData dataEntity = new StaticData();
		mapper.map(dto, dataEntity);

		return dataEntity;
	}

	@Override
	public StaticDataDTO toDTO(StaticData entity) {
		StaticDataDTO dataDTO = new StaticDataDTO();
		mapper.map(entity, dataDTO);

		return dataDTO;
	}

	@Override
	public JpaRepository getRepository() {
		return staticDataRepository;
	}

	@Override
	public List<StaticDataDTO> getAllDataByType(String type) {
		log.trace("Fetching the static data for type:" + type);

		List<StaticData> staticDataList = staticDataRepository.findAllByName(type);
		return toDTOList(staticDataList);
	}

	@Override
	public List<StaticDataDTO> getAllByTypeAndActive(String type, boolean active) {
		log.trace("Fetching the ACTIVE static data for type:" + type);

		List<StaticData> staticDataList = staticDataRepository.findAllByNameAndActive(type, active);
		return toDTOList(staticDataList);
	}

	@Override
	public List<StaticDataDTO> saveOrUpdate(List<StaticDataDTO> dtoList) {
		if (dtoList != null && dtoList.get(0) != null && dtoList.get(0).getName() != null) {
			updateRemoved(dtoList.get(0).getName(), dtoList);	
		}
		return super.saveOrUpdate(dtoList);
	}

	void updateRemoved(String type, List<StaticDataDTO> dataList) {
		log.trace("Removing the static data which were removed by the user in ui");

		List<Integer> existingData = new ArrayList<Integer>();
		
		List<StaticData> existingDetails = staticDataRepository.findAllByName(type);
		
		if(dataList != null) {
			for (StaticDataDTO data: dataList) {
				if (data != null && data.getId() != null)
					existingData.add(data.getId());
			}
		}
		
		if(existingDetails != null) {
			for (StaticData data : existingDetails) {
				if(!existingData.contains(data.getId())) {
					staticDataRepository.delete(data.getId());
				}
			}
		}
	}
}
