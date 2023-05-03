package com.lumen.ebonding.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.ebonding.dto.MappingDetailDTO;
import com.lumen.ebonding.service.MappingDetailService;

@RestController
@RequestMapping(path = "/mappingDetail")
public class MappingDetailController {

	private Logger log = LoggerFactory.getLogger(MappingDetailController.class);

	@Autowired
	private MappingDetailService mappingDetailService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<MappingDetailDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		MappingDetailDTO mapping = mappingDetailService.get(id);
		if (mapping != null) {
			return new ResponseEntity<MappingDetailDTO>(mapping, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<MappingDetailDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<MappingDetailDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<MappingDetailDTO> mappingList = mappingDetailService.getAll();
		if (mappingList != null && !mappingList.isEmpty()) {
			return new ResponseEntity<List<MappingDetailDTO>>(mappingList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<MappingDetailDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<List<MappingDetailDTO>> save(@Valid @RequestBody List<MappingDetailDTO> mappingDetails) {
		log.trace("Starting processing Post request!");

		try {
			mappingDetails = mappingDetailService.saveOrUpdate(mappingDetails);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<List<MappingDetailDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<MappingDetailDTO>>(mappingDetails, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{configId}")
	public ResponseEntity<List<MappingDetailDTO>> update(@PathVariable Integer configId,
			@Valid @RequestBody List<MappingDetailDTO> mappigDetails) {

		log.trace("Starting processing put for id :" + configId);

		if (mappigDetails != null) {
			log.trace("processing put request for config id :" + configId);

			try {
				mappigDetails = mappingDetailService.saveOrUpdate(mappigDetails);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				return new ResponseEntity<List<MappingDetailDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<MappingDetailDTO>>(mappigDetails, HttpStatus.OK);
		}

		log.info("Id mismatch for models and request param :" + configId);
		return new ResponseEntity<List<MappingDetailDTO>>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting  processing of delete rrequest for id :" + id);

		if (mappingDetailService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		mappingDetailService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/config/{id}")
	public ResponseEntity<List<MappingDetailDTO>> getAllDataByType(@PathVariable Integer id,
			@RequestParam(required = false) Boolean active) {
		log.trace("Starting processing getAllByConfigId request!");

		List<MappingDetailDTO> mappingList = active != null && active ? mappingDetailService.getActiveMappingsByConfigId(id)
				: mappingDetailService.getMappingsByConfigId(id);
		
		if (mappingList != null && !mappingList.isEmpty()) {
			return new ResponseEntity<List<MappingDetailDTO>>(mappingList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByConfig");
		return new ResponseEntity<List<MappingDetailDTO>>(HttpStatus.NO_CONTENT);

	}

}
