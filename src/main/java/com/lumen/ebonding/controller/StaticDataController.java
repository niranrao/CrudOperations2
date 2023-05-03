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
import org.springframework.web.bind.annotation.RestController;

import com.lumen.ebonding.dto.StaticDataDTO;
import com.lumen.ebonding.service.StaticDataService;

@RestController
@RequestMapping(path = "/staticData")
public class StaticDataController {

	private Logger log = LoggerFactory.getLogger(StaticDataController.class);

	@Autowired
	private StaticDataService staticDataService;

	@GetMapping(path = "**/{id}")
	public ResponseEntity<StaticDataDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		StaticDataDTO staticData = staticDataService.get(id);
		if (staticData != null) {
			return new ResponseEntity<StaticDataDTO>(staticData, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<StaticDataDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<StaticDataDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<StaticDataDTO> staticDataList = staticDataService.getAll();
		if (staticDataList != null && !staticDataList.isEmpty()) {
			return new ResponseEntity<List<StaticDataDTO>>(staticDataList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<List<StaticDataDTO>> save(@Valid @RequestBody List<StaticDataDTO> staticDataList) {
		log.trace("Starting processing Post request!");

		try {
			staticDataList = staticDataService.saveOrUpdate(staticDataList);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<StaticDataDTO>>(staticDataList, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{type}")
	public ResponseEntity<List<StaticDataDTO>> update(@PathVariable String type, @Valid @RequestBody List<StaticDataDTO> staticDataList) {

		log.trace("Starting processing put for type :" + type);

		if (staticDataList != null && staticDataList.size() > 0) {
			try {
				staticDataList = staticDataService.saveOrUpdate(staticDataList);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<StaticDataDTO>>(staticDataList, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting  processing of delete rrequest for id :" + id);

		if (staticDataService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		staticDataService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/type/{type}")
	public ResponseEntity<List<StaticDataDTO>> getAllDataByType(@PathVariable String type){
		log.trace("Starting processing getAllByType request!");

		List<StaticDataDTO> staticDataList = staticDataService.getAllDataByType(type);
		if (staticDataList != null && !staticDataList.isEmpty()) {
			return new ResponseEntity<List<StaticDataDTO>>(staticDataList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByType");
		return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.NO_CONTENT);
		
	}

	@GetMapping(path = "/type/{type}/active")
	public ResponseEntity<List<StaticDataDTO>> getAllDataByTypeAndStatus(@PathVariable String type){
		log.trace("Starting processing getAllByTypeAndStatus request!");

		List<StaticDataDTO> staticDataList = staticDataService.getAllByTypeAndActive(type, true);
		if (staticDataList != null && !staticDataList.isEmpty()) {
			return new ResponseEntity<List<StaticDataDTO>>(staticDataList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByTypeAndStatus");
		return new ResponseEntity<List<StaticDataDTO>>(HttpStatus.NO_CONTENT);
	}
}
