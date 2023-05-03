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

import com.lumen.ebonding.dto.CustomerDTO;
import com.lumen.ebonding.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	private Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		CustomerDTO Customer = customerService.get(id);
		if (Customer != null) {
			return new ResponseEntity<CustomerDTO>(Customer, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<CustomerDTO> Customeres = customerService.getAll();
		if (Customeres != null && !Customeres.isEmpty()) {
			return new ResponseEntity<List<CustomerDTO>>(Customeres, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO customer) {
		log.trace("Starting processing Post request!");

		try {
			customer = customerService.saveOrUpdate(customer);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<CustomerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO customer) {

		log.trace("Starting processing put for id :" + id);

		if (customer != null && customer.getId() == null) {
			log.trace("updating id in model for put request for id :" + id);
			customer.setId(id);
		}

		if (customer != null && customer.getId().equals(id)) {
			log.trace("processing put request for id :" + id);

			try {
				customer = customerService.saveOrUpdate(customer);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				return new ResponseEntity<CustomerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
		}

		log.info("Id mismatch for model (" + customer.getId() + ") and request param :" + id);
		return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting  processing of delete rrequest for id :" + id);

		if (customerService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		customerService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
