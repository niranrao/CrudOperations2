package com.lumen.ebonding.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.ebonding.service.EbondingHistoryService;

@RestController
@RequestMapping(path = "/history")
public class EbondingHistoryController {

	private Logger log = LoggerFactory.getLogger(EbondingHistoryController.class);

	@Autowired
	private EbondingHistoryService ebondingHistoryService;
	
	// Method to get total no of records.
	@GetMapping(path = "/count")
	public ResponseEntity<Integer> getTicketCount() {
		log.info("Started to get count for 24 hrs history data");
		try {
			Integer count = ebondingHistoryService.getHistoryDataCount();
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception ex) {
			log.info("History data count retrival failed: " + ex);
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	// Method to get last 24 hours history data with startIndex for both incidents
	// and changes
	@GetMapping(path = "/getAllData")
	public ResponseEntity<Map<String, Object>> getAllData(@RequestParam("startIndex") int startIndex) {
		log.info("Started processing for 24 hours history data");
		try {
			Map<String, Object> history = ebondingHistoryService.getHistoryDataWithSindex(startIndex);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Map<String, Object>>(history, HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed: " + e);
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}
	
	//Method to get count using ticket Id and particular time period(optional) of both incidents and changes
	@GetMapping(path = "/searchCount")
	public ResponseEntity<Integer> getHistoryDataCount(@RequestParam("ticketId") String ticketId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("businessProcess") String businessProcess, @RequestParam("customerName") String customerName){
		log.info("Getting Count for the search parameter : ");
		try {
			Integer history = ebondingHistoryService.getHistoryParameteredDataCount(ticketId, fromDate, toDate, businessProcess, customerName);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Integer>(history,HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}		
	}

	// Method to get data using ticket Id and particular time period(optional) of
	// both incidents and changes
	@GetMapping(path = "/search")
	public ResponseEntity<Map<String, Object>> getHistoryData(@RequestParam("ticketId") String ticketId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("businessProcess") String businessProcess,
			@RequestParam("customerName") String customerName,
			@RequestParam("sIndex") int sIndex) {
		log.info("Starting processing for history data with ticketId : " + ticketId);
		Map<String, Object> history = null;
		try {
			history = ebondingHistoryService.getHistoryParameteredData(ticketId, fromDate, toDate, businessProcess, customerName, sIndex);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		log.info("Data retrieved successfully");
		return new ResponseEntity<Map<String, Object>>(history, HttpStatus.OK);
	}

	// Method to get all transactions of particular ticket id
	@GetMapping(path = "/{ticketId}")
	public ResponseEntity<Map<String, Object>> getHistoryDataWithTicket(@PathVariable("ticketId") String ticketId) {
		log.info("Starting processing for history data with ticketId : " + ticketId);
		Map<String, Object> history = null;
		try {
			history = ebondingHistoryService.getHistoryOfTicket(ticketId);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		log.info("Data retrieved successfully");
		return new ResponseEntity<Map<String, Object>>(history, HttpStatus.OK);
	}

	// To retrieve Received Payload of incident or Change tickets using mapping Id
	@GetMapping(path = "/payload/{ticketId}/{mappingId}")
	public ResponseEntity<Map<String, Object>> getPayload(@PathVariable("ticketId") String ticketId,
			@PathVariable("mappingId") int mappingId) {
		log.info("Starting processing for Payload data with Mapping Id : " + mappingId + "and Ticket Id : " + ticketId);
		Map<String, Object> payload = null;
		try {
			payload = ebondingHistoryService.getPayload(mappingId);
			log.info("Payload is retrived successfully");
			return new ResponseEntity<Map<String, Object>>(payload, HttpStatus.OK);
		} catch (Exception e) {
			log.info("Payload Data retrival failed");
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}
}