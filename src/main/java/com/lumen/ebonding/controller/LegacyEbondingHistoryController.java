package com.lumen.ebonding.controller;

import java.util.List;
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

import com.lumen.ebonding.dto.LegacyCompaniesDTO;
import com.lumen.ebonding.service.LegacyEbondingHistoryService;

@RestController
@RequestMapping(path = "/legacyhistory")
public class LegacyEbondingHistoryController {

	private Logger log = LoggerFactory.getLogger(LegacyEbondingHistoryController.class);

	@Autowired
	private LegacyEbondingHistoryService legacyEbondingHistoryService;
	
	// Method to get last 24 hours history data with startIndex for both incidents and changes
	@GetMapping(path="/getAllData")
	public ResponseEntity<Map<String, Object>> getAllData(@RequestParam("startIndex") int startIndex) {
		log.info("Started processing for 24 hours history data");
		try {
			Map<String, Object> history = legacyEbondingHistoryService.getHistoryDataWithSindex(startIndex);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Map<String, Object>>(history, HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}
	
	// Method to get total no of records.
	@GetMapping(path="/count")
	public ResponseEntity<Integer> getTicketCount() {
		log.info("Started to get count for 24 hrs history data");
		try {
			Integer count = legacyEbondingHistoryService.getHistoryDataCount();
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		}catch(Exception ex) {
			log.info("History data count retrival failed");
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}		
	}
	
	//Method to get data using ticket Id and particular time period(optional) of both incidents and changes
	@GetMapping(path = "/search")
	public ResponseEntity<Map<String,Object>> getHistoryData(@RequestParam("ticketId") String ticketId,@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("customerName") String customerName, @RequestParam("sIndex") int sIndex){
		log.info("Starting processing for history data with ticketId : "+ ticketId);
		Map<String, Object> history = null;
		try {
			history = legacyEbondingHistoryService.getHistoryParameteredData(ticketId, fromDate, toDate, customerName, sIndex);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Map<String,Object>>(history,HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	//Method to get count using ticket Id and particular time period(optional) of both incidents and changes
	@GetMapping(path = "/searchCount")
	public ResponseEntity<Integer> getHistoryDataCount(@RequestParam("ticketId") String ticketId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("customerName") String customerName){
		log.info("Getting Count for the search parameter : ");

		try {
			Integer history = legacyEbondingHistoryService.getHistoryParameteredDataCount(ticketId, fromDate, toDate, customerName);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Integer>(history,HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	//Method to get all transactions of particular ticket id
	@GetMapping(path = "/{ticketId}")
	public ResponseEntity<Map<String,Object>> getHistoryDataWithTicket(@PathVariable("ticketId") String ticketId){
		log.info("Starting processing for history data with ticketId : "+ ticketId);
		Map<String, Object> history = null;
		try {
			history = legacyEbondingHistoryService.getHistoryOfTicket(ticketId);
			log.info("Data retrieved successfully");
			return new ResponseEntity<Map<String,Object>>(history,HttpStatus.OK);
		} catch (Exception e) {
			log.info("History data retrival failed");
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}		
	}
	
	// To retrieve Received Payload of incident or Change tickets using mapping Id
	@GetMapping(path="/payload/{ticketId}/{mappingId}")
	public ResponseEntity<Map<String,Object>> getPayload(@PathVariable("ticketId") String ticketId,@PathVariable("mappingId") int mappingId){
		log.info("Starting processing for Payload data with Mapping Id : "+ mappingId + "and Ticket Id : "+ ticketId);
		Map<String,Object> payload = null;
		try {
			payload = legacyEbondingHistoryService.getPayload(mappingId);
			log.info("Payload is retrived successfully");
			return new ResponseEntity<Map<String,Object>>(payload,HttpStatus.OK);
		}
		catch(Exception e) {
			log.info("Payload Data retrival failed");
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path="/companies")
	public ResponseEntity<List<LegacyCompaniesDTO>> getCompany(){
		log.info("start getCompanies");
		List<LegacyCompaniesDTO> companyNames=null;
		try {
			companyNames=legacyEbondingHistoryService.getCompanies();
			log.info("company-->"+companyNames.toString());
			return new ResponseEntity<List<LegacyCompaniesDTO>>(companyNames,HttpStatus.OK);
		}
		catch(Exception e) {
			log.info("getCompanies failed");
			return  new ResponseEntity<List<LegacyCompaniesDTO>>(HttpStatus.NO_CONTENT);
		}
	}	
}