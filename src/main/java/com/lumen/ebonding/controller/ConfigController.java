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

import com.lumen.ebonding.dto.CustomerConfigurationDTO;
import com.lumen.ebonding.service.CustomerConfigService;

@RestController
@RequestMapping(path = "/customerConfig")
public class ConfigController {

	private Logger log = LoggerFactory.getLogger(ConfigController.class);

	@Autowired
	private CustomerConfigService configService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<CustomerConfigurationDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		CustomerConfigurationDTO config = configService.get(id);
		if (config != null) {
			return new ResponseEntity<CustomerConfigurationDTO>(config, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<CustomerConfigurationDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<CustomerConfigurationDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<CustomerConfigurationDTO> configs = configService.getAll();
		if (configs != null && !configs.isEmpty()) {
			return new ResponseEntity<List<CustomerConfigurationDTO>>(configs, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<CustomerConfigurationDTO>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/customer/{custId}")
	public ResponseEntity<List<CustomerConfigurationDTO>> getConfigsByCustomerId(@PathVariable Integer custId) {
		log.trace("Fetching cusotmer configurations for customer id : " + custId);

		return new ResponseEntity<List<CustomerConfigurationDTO>>(configService.getConfigByCustomerId(custId),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerConfigurationDTO> save(@Valid @RequestBody CustomerConfigurationDTO config) {
		log.trace("Starting processing Post request!");

		try {
			config = configService.saveOrUpdate(config);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<CustomerConfigurationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CustomerConfigurationDTO>(config, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<CustomerConfigurationDTO> update(@PathVariable Integer id,
			@Valid @RequestBody CustomerConfigurationDTO config) {

		log.trace("Starting processing put for id :" + id);

		if (config != null && config.getId() == null) {
			log.trace("updating id in model for put request for id :" + id);
			config.setId(id);
		}

		if (config != null && config.getId().equals(id)) {
			log.trace("processing put request for id :" + id);

			try {
				config = configService.saveOrUpdate(config);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				return new ResponseEntity<CustomerConfigurationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CustomerConfigurationDTO>(config, HttpStatus.OK);
		}

		log.info("Id mismatch for model (" + config.getId() + ") and request param :" + id);
		return new ResponseEntity<CustomerConfigurationDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting processing of delete rrequest for id :" + id);

		if (configService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		configService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/customer/{custId}/{businessProcess}")
	public ResponseEntity<List<CustomerConfigurationDTO>> getCustomerConfigByCustomerIdAndBusinessProcess(
			@PathVariable Integer custId, @PathVariable String businessProcess) {
		log.trace("Fetching customer configurations for customer id : " + custId + "and business process : "
				+ businessProcess);

		return new ResponseEntity<List<CustomerConfigurationDTO>>(
				configService.getCustomerConfigByCustomerIdAndBusinessProcess(custId, businessProcess), HttpStatus.OK);
	}

	@GetMapping(path = "/cust/{customerName}")
	public ResponseEntity<List<CustomerConfigurationDTO>> getCustomerConfigByCustomerName(
			@PathVariable String customerName) {
		log.trace("Fetching customer configurations for customer name : " + customerName);

		return new ResponseEntity<List<CustomerConfigurationDTO>>(
				configService.getCustomerConfigByCustomerName(customerName), HttpStatus.OK);
	}

	@GetMapping(path = "/getCustomer/{custName}/{businessProcess}/{source}/{target}")
	public ResponseEntity<List<CustomerConfigurationDTO>> getCustConfByCustNameAndBPAndSourceAndTarget(
			@PathVariable String custName, @PathVariable String businessProcess, @PathVariable String source,
			@PathVariable String target) {
		log.trace("Fetching customer configurations for customer : " + custName + "and business process : "
				+ businessProcess + "and source :" + source + "and target :" + target);

		return new ResponseEntity<List<CustomerConfigurationDTO>>(
				configService.getCustomerConfigByCustNameBPSourceAndTarget(custName, businessProcess, source, target),
				HttpStatus.OK);
	}
}
