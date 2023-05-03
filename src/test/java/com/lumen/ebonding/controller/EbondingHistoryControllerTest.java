package com.lumen.ebonding.controller;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lumen.ebonding.model.Metadata;

public class EbondingHistoryControllerTest {


	RestTemplate restTemplate = new RestTemplate();
	EbondingHistoryController controller = new EbondingHistoryController();
	
	@Test
	public void testLast24hrsData() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history", Map.class);
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void testSearchHistoryDataWithIncidentTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=INC000001923248&fromDate=&toDate=",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithChangeTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=CRQ000004266088&fromDate=&toDate=",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithUnExistedTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=CRQ000009266088&fromDate=&toDate=",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertEquals(0,metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithDates() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=&fromDate=12/12/2021&toDate=01/01/2022",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithDatesHavingNoData() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=&fromDate=12/12/2021&toDate=13/12/2021",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithDatesWithTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=INC000001923248&fromDate=12/12/2021&toDate=18/12/2021",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithDatesUnexistedWithTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=INC000001923248&fromDate=12/12/2021&toDate=15/12/2021",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithDatesWithTicketIdUnexisted() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/search?ticketId=INC000000923248&fromDate=12/12/2021&toDate=18/12/2021",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithCustomerIncidentTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/INC10942803",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithRemedyIncidentTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/INC000001923001",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithCustomerChangeTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/CHG10213494",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithRemedyChangeTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/CRQ000004266980",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithCustomerIncidentUnExistedTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/INC10942813",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithRemedyIncidentUnExistedTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/INC000001123001",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithCustomerChangeUnExistedTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/CHG10211494",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataWithRemedyChangeUnExistedTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/CRQ000001266980",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0==(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataPayloadWithIncidentTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/payload/INC10942202/7",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}
	
	@Test
	public void testSearchHistoryDataPayloadWithChangeTicketId() {
		ResponseEntity<Map> result = restTemplate.getForEntity("http://localhost:8082/history/payload/CRQ000004266980/3",Map.class);
		Map metadata = (Map) result.getBody().get("metadata");
		assertTrue(0<(int) metadata.get("countOfRecords"));
	}

}
