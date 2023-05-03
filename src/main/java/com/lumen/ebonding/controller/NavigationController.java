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

import com.lumen.ebonding.dto.NavigationDTO;
import com.lumen.ebonding.service.NavigationService;

@RestController
@RequestMapping(path = "/navigation")
public class NavigationController {

	private Logger log = LoggerFactory.getLogger(NavigationController.class);

	@Autowired
	private NavigationService navigationService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<NavigationDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		NavigationDTO navigation = navigationService.get(id);
		if (navigation != null) {
			return new ResponseEntity<NavigationDTO>(navigation, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<NavigationDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<NavigationDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<NavigationDTO> navigationList = navigationService.getAll();
		if (navigationList != null && !navigationList.isEmpty()) {
			return new ResponseEntity<List<NavigationDTO>>(navigationList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<NavigationDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<NavigationDTO> save(@Valid @RequestBody NavigationDTO navigation) {
		log.trace("Starting processing Post request!");

		try {
			navigation = navigationService.saveOrUpdate(navigation);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<NavigationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<NavigationDTO>(navigation, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<NavigationDTO> update(@PathVariable Integer id,
			@Valid @RequestBody NavigationDTO navigation) {

		log.trace("Starting processing put for id :" + id);

		if (navigation != null && navigation.getId() == null) {
			log.trace("updating id in model for put request for id :" + id);
			navigation.setId(id);
		}

		if (navigation != null && navigation.getId().equals(id)) {
			log.trace("processing put request for id :" + id);

			try {
				navigation = navigationService.saveOrUpdate(navigation);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				return new ResponseEntity<NavigationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<NavigationDTO>(navigation, HttpStatus.OK);
		}

		log.info("Id mismatch for model (" + navigation.getId() + ") and request param :" + id);
		return new ResponseEntity<NavigationDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting  processing of delete rrequest for id :" + id);

		if (navigationService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		navigationService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/active")
	public ResponseEntity<List<NavigationDTO>> getAllDataByTypeAndStatus() {
		log.trace("Starting processing getAllByTypeAndStatus request!");

		List<NavigationDTO> navigationList = navigationService.getAllByActive(true);
		if (navigationList != null && !navigationList.isEmpty()) {
			return new ResponseEntity<List<NavigationDTO>>(navigationList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByTypeAndStatus");
		return new ResponseEntity<List<NavigationDTO>>(HttpStatus.NO_CONTENT);
	}
}
