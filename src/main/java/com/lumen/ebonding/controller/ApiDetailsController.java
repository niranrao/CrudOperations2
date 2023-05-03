package com.lumen.ebonding.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.ebonding.dto.ApiDetailDTO;
import com.lumen.ebonding.model.ApiDetail;
import com.lumen.ebonding.service.ApiDetailsService;

@RestController
@RequestMapping(path = "/apiDetail")
public class ApiDetailsController {

	private Logger log = LoggerFactory.getLogger(ApiDetailsController.class);

	@Autowired
	private ApiDetailsService apiDetailsService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<ApiDetailDTO> get(@PathVariable Integer id) {
		log.trace("Starting processing get request for id :" + id);

		ApiDetailDTO apiDetail = apiDetailsService.get(id);
		if (apiDetail != null) {
			return new ResponseEntity<ApiDetailDTO>(apiDetail, HttpStatus.OK);
		}

		log.info("Entity having id not found, id : " + id);
		return new ResponseEntity<ApiDetailDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<ApiDetailDTO>> getAll() {
		log.trace("Starting processing getAll request!");

		List<ApiDetailDTO> apiDetailList = apiDetailsService.getAll();
		if (apiDetailList != null && !apiDetailList.isEmpty()) {
			return new ResponseEntity<List<ApiDetailDTO>>(apiDetailList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAll");
		return new ResponseEntity<List<ApiDetailDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<ApiDetailDTO> save(@Valid @RequestBody ApiDetailDTO apiDetail) {
		log.trace("Starting processing Post request!");

		try {
			apiDetail = apiDetailsService.saveOrUpdate(apiDetail);
		} catch (Exception err) {
			log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
			return new ResponseEntity<ApiDetailDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiDetailDTO>(apiDetail, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<ApiDetailDTO> update(@PathVariable Integer id, @Valid @RequestBody ApiDetailDTO apiDetail) {

		log.trace("Starting processing put for id :" + id);

		if (apiDetail != null && apiDetail.getId() == null) {
			log.trace("updating id in model for put request for id :" + id);
			apiDetail.setId(id);
		}

		if (apiDetail != null && apiDetail.getId().equals(id)) {
			log.trace("processing put request for id :" + id);

			try {
				apiDetail = apiDetailsService.saveOrUpdate(apiDetail);
			} catch (Exception err) {
				log.error("Error Occured while saving, Message : " + err.getMessage() + "; Cause :" + err.getCause());
				err.printStackTrace();
				return new ResponseEntity<ApiDetailDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<ApiDetailDTO>(apiDetail, HttpStatus.OK);
		}

		log.info("Id mismatch for model (" + apiDetail.getId() + ") and request param :" + id);
		return new ResponseEntity<ApiDetailDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		log.trace("Starting  processing of delete request for id :" + id);

		if (apiDetailsService.get(id) == null) {
			log.info("Entity not found while processing the delete request for id :" + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		apiDetailsService.delete(id);
		log.info("Entity deleted having id :" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/config/{configId}")
	public ResponseEntity<List<ApiDetailDTO>> getAllDataByConfigId(@PathVariable Integer configId) {
		log.trace("Starting processing getAllByType request!");

		List<ApiDetailDTO> apiDetailList = apiDetailsService.getAllByConfig(configId);
		if (apiDetailList != null && !apiDetailList.isEmpty()) {
			return new ResponseEntity<List<ApiDetailDTO>>(apiDetailList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByType");
		return new ResponseEntity<List<ApiDetailDTO>>(HttpStatus.NO_CONTENT);

	}

	@GetMapping(path = "/config/{configId}/active")
	public ResponseEntity<List<ApiDetailDTO>> getAllByConfigIdAndStatus(@PathVariable Integer configId) {
		log.trace("Starting processing getAllByconfigIdAndStatus request!");

		List<ApiDetailDTO> apiDetailList = apiDetailsService.getAllActiveApiByConfig(configId, true);
		if (apiDetailList != null && !apiDetailList.isEmpty()) {
			return new ResponseEntity<List<ApiDetailDTO>>(apiDetailList, HttpStatus.OK);
		}

		log.info("No element found while hitting getAllByTypeAndStatus");
		return new ResponseEntity<List<ApiDetailDTO>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "{id}/removeFile/{type}")
	public ResponseEntity<Boolean> removeFileByConfig(@PathVariable Integer id, @PathVariable String type) {
		log.trace("Removing uploaded file for id: " + id + "and type: " + type);

		boolean deleted = false;
		if (id != null && type != null) {
			deleted = apiDetailsService.deleteUploadedContentById(id, type);
		}

		return deleted ? ResponseEntity.ok(true) : new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/config/{id}/from/{from}")
	public ResponseEntity<List<String>> getFieldsConfigId(@PathVariable Integer id, @PathVariable String from) {
		log.trace("get field list for configid: " + id + "from : " + from);

		return ResponseEntity.ok(apiDetailsService.getFieldsListByConfigId(id, from));
	}
	@RequestMapping(value = "/download-config-api-file", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("config-api-id") int custConfigApiId,
			@RequestParam("resourceType") String resourceType) {

		ApiDetail downloadXmlResponse = apiDetailsService.getFileDownload(custConfigApiId);

		if (downloadXmlResponse == null) {
			log.info("file could not be downloaded for configapidi  " + custConfigApiId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			if ("SOURCE".equalsIgnoreCase(resourceType)) {
				ByteArrayResource resource = new ByteArrayResource(downloadXmlResponse.getSourcePayload());
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + downloadXmlResponse.getSourceFileName() + "\"").body(resource);
			} else if ("TARGET".equalsIgnoreCase(resourceType)) {
				ByteArrayResource resource = new ByteArrayResource(downloadXmlResponse.getTargetPayload());
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + downloadXmlResponse.getTargetFileName() + "\"").body(resource);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

		}
	}
}
