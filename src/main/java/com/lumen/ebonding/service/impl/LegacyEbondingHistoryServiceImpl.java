package com.lumen.ebonding.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.LegacyCompaniesDTO;
import com.lumen.ebonding.dto.LegacyEbondingHistoryDTO;
import com.lumen.ebonding.model.LegacyCompanies;
import com.lumen.ebonding.model.LegacyEbondingHistory;
import com.lumen.ebonding.model.Metadata;
import com.lumen.ebonding.repository.LegacyCompaniesRepository;
import com.lumen.ebonding.repository.LegacyEbondingHistoryRepository;
import com.lumen.ebonding.service.LegacyEbondingHistoryService;

@Service
public class LegacyEbondingHistoryServiceImpl
		extends GenericServiceImpl<LegacyEbondingHistory, Integer, LegacyEbondingHistoryDTO>
		implements LegacyEbondingHistoryService {

	@Autowired
	private LegacyEbondingHistoryRepository legacyEbondingHistoryRepository;

	@Autowired
	private LegacyCompaniesRepository legacyCompaniesRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(LegacyEbondingHistoryServiceImpl.class);

	@Override
	public LegacyEbondingHistory toEntity(LegacyEbondingHistoryDTO dto) {
		LegacyEbondingHistory entity = new LegacyEbondingHistory();
		mapper.map(dto, entity);
		return entity;
	}

	@Override
	public LegacyEbondingHistoryDTO toDTO(LegacyEbondingHistory entity) {
		LegacyEbondingHistoryDTO dto = new LegacyEbondingHistoryDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public LegacyCompaniesDTO toDTO(LegacyCompanies entity) {
		LegacyCompaniesDTO dto = new LegacyCompaniesDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return legacyEbondingHistoryRepository;
	}

	@Override
	public int getHistoryDataCount() {
		log.info("Fetching the data count for 24 hrs");
		int count = legacyEbondingHistoryRepository.get24HrsDataCountOnLoad();
		return count;
	}

	// method to get data of Last 24 hours with Start Index for incidents and
	// changes
	@Override
	public Map<String, Object> getHistoryDataWithSindex(int startIndex) {
		log.info("Fetching the Last 24 hours data");
			List<LegacyEbondingHistory> historyModified =new ArrayList<>();
			// historyModified= legacyEbondingHistoryRepository.get24HrsDataWithSindexOnLoad(startIndex);
		historyModified = ((LegacyEbondingHistoryRepository) getRepository()).get24HrsDataWithSindexOnLoad(startIndex);
		//List<LegacyEbondingHistory> historyModified = new ArrayList<>();

		/*for (Object data : history) {
			LegacyEbondingHistory historyObj = new LegacyEbondingHistory();
			Object[] objects = (Object[]) data;
			historyObj.setId(objects[0] != null ? (int) objects[0] : 0);
			historyObj.setRemedyTicketId(objects[1] != null ? (String) objects[1] : "");
			historyObj.setCustomerTicketId(objects[2] != null ? (String) objects[2] : "");
			historyObj.setRequestFrom(objects[3] != null ? (String) objects[3] : "");
			historyObj.setRequestTo(objects[4] != null ? (String) objects[4] : "");
			historyObj.setRemedyTicketStatus(objects[5] != null ? (String) objects[5] : "");
			historyObj.setCustomerTicketStatus(objects[6] != null ? (String) objects[6] : "");
			historyObj.setEbondingResult(objects[7] != null ? (String) objects[7] : "");
			historyObj.setTransactionResult(objects[8] != null ? (String) objects[8] : "");
			historyObj.setCreatedTime(objects[9] != null ? (Timestamp) objects[9] : null);

			historyModified.add(historyObj);
		}*/
		Map<String, Object> historyData = new HashMap<>();
		Metadata metaData = new Metadata();
		if (historyModified.size() > 0) {
			log.info("Data is fetched");
			metaData.setCountOfRecords(historyModified.size());
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No Data available to fetch");
			metaData.setMessage("NO_CONTENT");
		}
		historyData.put("data", historyModified);
		historyData.put("metadata", metaData);
		log.info("Operation Completed....");
		return historyData;
	} 

	// method to get data of Last 24 hours for incidents and changes
	public Map<String, Object> processHistoryData(List<LegacyEbondingHistory> history) {
		log.info("Fetching the Last 24 hours data");
		Set<String> ticketSet = new HashSet<>();
		List<LegacyEbondingHistory> historyModified = new ArrayList<>();
		Set<String> customerTicketSet = new HashSet<>();
		// To get latest transaction of the ticket
		for (LegacyEbondingHistory data : history) {
			if (data.getCustomerTicketId() != null && data.getCustomerTicketId().length() == 10
					&& customerTicketSet.contains(data.getCustomerTicketId())) {
				continue;
			}
			if (data.getRemedyTicketId() != null && data.getRemedyTicketId().length() == 15
					&& !ticketSet.contains(data.getRemedyTicketId())) {
				historyModified.add(data);
				ticketSet.add(data.getRemedyTicketId());
				customerTicketSet.add(data.getCustomerTicketId());
			} else if ((data.getRemedyTicketId() == null || data.getRemedyTicketId().length() != 15)
					&& data.getCustomerTicketId().length() == 10
					&& !customerTicketSet.contains(data.getCustomerTicketId())) {
				historyModified.add(data);
				customerTicketSet.add(data.getCustomerTicketId());
			}
		}
		List<LegacyEbondingHistoryDTO> historyDTO = toDTOList(historyModified);
		Map<String, Object> historyData = new HashMap<>();
		Metadata metaData = new Metadata();
		if (historyDTO.size() > 0) {
			log.info("Data is fetched");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No Data available to fetch");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("NO_CONTENT");
		}
		historyData.put("data", historyDTO);
		historyData.put("metadata", metaData);
		log.info("Operation Completed....");
		return historyData;
	}

	// Method to retieve History data for Incident or Change requests using ticket
	// id and particular time period. Time period is optional
	@Override
	public Map<String, Object> getHistoryParameteredData(String ticketId, String fromDate, String toDate,
			String customerName, int sIndex) throws ParseException {
		log.info("TicketId: " +ticketId+ " From Date: " +fromDate+ " To Date: " +toDate+ " Source Customer Name :"+customerName);
		List<LegacyEbondingHistory> history = new ArrayList<>();
		HashMap<String, String> customersMap = new HashMap<String, String>();
		String fromTime = null, toTime = null;
		if (fromDate != "" && toDate != "") {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fdate = formatter.parse(fromDate);
			Date tdate = formatter.parse(toDate);
			tdate.setHours(23);
			tdate.setMinutes(59);
			tdate.setSeconds(59);
			SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fromTime = formatterTime.format(new Timestamp(fdate.getTime()));
			toTime = formatterTime.format(new Timestamp(tdate.getTime()));
		}
		if (customerName != "" && !customerName.isEmpty()) {
			log.info("Getting targetcompany");
			String targetCust = legacyCompaniesRepository.getTargetCustomers(customerName);
			customersMap.put(customerName, targetCust);
			log.info("Target Company : " + customersMap.get(customerName));
		}
		if (ticketId.length() == 15) {
			if (fromDate != "" && toDate != "") {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get data using ticket Id for a particular time period
				history = ((LegacyEbondingHistoryRepository) getRepository()).getRemedyHistoryDataWithDateRangeAndTicketId(ticketId, fromTime, toTime, sIndex);
			} else {
				// This method will get the data only with ticket Id
				log.info("Fetching for Remedy Ticket Id :" + ticketId);
				history = ((LegacyEbondingHistoryRepository) getRepository()).getRemedyHistoryDataWithTicketId(ticketId, sIndex);
			}
		} else if (ticketId.length() == 10) {
			if (fromDate != "" && toDate != "") {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get data using ticket Id for a particular time period
				history = ((LegacyEbondingHistoryRepository) getRepository()).getCustomerHistoryWithDateRangeAndTicketId(ticketId, fromTime, toTime, sIndex);
			} else {
				// This method will get the data only with ticket Id
				log.info("Fetching for Customer Ticket Id :" + ticketId);
				history = ((LegacyEbondingHistoryRepository) getRepository()).getCustomerHistoryDataWithTicketId(ticketId, sIndex);
			}
		} else if (ticketId.length() == 0) {
			if (fromDate != "" && toDate != "") {
				Set<String> ticketSet = new HashSet<>();
				Set<String> customerTicketSet = new HashSet<>();
				List<LegacyEbondingHistory> historyDataWithInPeriod = null;

				if (!customerName.isEmpty()) {
					log.info("Fetching data with customer name and from data and to data");
					history = ((LegacyEbondingHistoryRepository) getRepository())
							.getRemedyHistoryDataWithDateRangeSrcTarget(fromTime, toTime, customerName, customersMap.get(customerName), sIndex);
				} else {
					log.info("Fetching data with from data and to data");
					history = ((LegacyEbondingHistoryRepository) getRepository())
							.getRemedyHistoryDataWithDateRange(fromTime, toTime, sIndex);
				}

				// To get latest transaction of the ticket
				/*
				for (LegacyEbondingHistory data : historyDataWithInPeriod) {
					if (data.getCustomerTicketId() != null && data.getCustomerTicketId().length() == 11
							&& customerTicketSet.contains(data.getCustomerTicketId())) {
						continue;
					}
					if (data.getRemedyTicketId() != null && data.getRemedyTicketId().length() == 15
							&& !ticketSet.contains(data.getRemedyTicketId())) {
						history.add(data);
						ticketSet.add(data.getRemedyTicketId());
						customerTicketSet.add(data.getCustomerTicketId());
					} else if ((data.getRemedyTicketId() == null || data.getRemedyTicketId().length() != 15)
							&& data.getCustomerTicketId().length() == 10
							&& !customerTicketSet.contains(data.getCustomerTicketId())) {
						history.add(data);
						customerTicketSet.add(data.getCustomerTicketId());
					}
				}*/
			}
		}

		List<LegacyEbondingHistoryDTO> historyDTO = toDTOList(history);
		Map<String, Object> historyData = new HashMap<>();
		Metadata metaData = new Metadata();
		if (historyDTO.size() > 0) {
			log.info("Data is fetched");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No data available to fetch");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("NO_CONTENT");
		}
		historyData.put("data", historyDTO);
		historyData.put("metadata", metaData);
		log.info("Operation completed...");
		return historyData;
	}

	// method to retieve Payload
	@Override
	public Map<String, Object> getPayload(int mappingId) {
		log.info("Fetching the data of Payload for id : " + mappingId);
		String rcvdPayload = ((LegacyEbondingHistoryRepository) getRepository()).getReceivedPayload(mappingId);
		String transPayload = ((LegacyEbondingHistoryRepository) getRepository()).getTransformedPayload(mappingId);
		String rspInfo = ((LegacyEbondingHistoryRepository) getRepository()).getResponseInfo(mappingId);
		Metadata metaData = new Metadata();
		if (rcvdPayload != null || transPayload != null || rspInfo != null) {
			log.info("Fetched the Payload");
			metaData.setCountOfRecords(1);
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No data for payload is available to fetch");
			metaData.setCountOfRecords(0);
			metaData.setMessage("NO_CONTENT");
		}
		Map<String, String> payloadData = new HashMap<>();
		payloadData.put("ReceivedPayload", rcvdPayload);
		payloadData.put("TransformedPayload", transPayload);
		payloadData.put("ResponseInfo", rspInfo);
		Map<String, Object> historyData = new HashMap<>();
		historyData.put("data", payloadData);
		historyData.put("metadata", metaData);
		log.info("Operation completed ... ");
		return historyData;
	}

	// Method to retieve History data for Incident or Change requests using ticket
	// id
	@Override
	public Map<String, Object> getHistoryOfTicket(String ticketId) {
		log.trace("Fetching the history data for ticket id : " + ticketId);
		List<LegacyEbondingHistory> history = new ArrayList<>();
		System.out.println(ticketId);
		if (ticketId.length() == 15) {
			history = ((LegacyEbondingHistoryRepository) getRepository()).getRemedyHistoryDataOfTicket(ticketId);
		} else if (ticketId.length() == 10) {
			history = ((LegacyEbondingHistoryRepository) getRepository()).getCustomerHistoryDataOfTicket(ticketId);
		}
		List<LegacyEbondingHistoryDTO> historyDTO = toDTOList(history);
		Map<String, Object> historyData = new HashMap<>();
		Metadata metaData = new Metadata();
		if (historyDTO.size() > 0) {
			log.info("Data is fetched");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No data available to fetch");
			metaData.setCountOfRecords(historyDTO.size());
			metaData.setMessage("NO_CONTENT");
		}
		historyData.put("data", historyDTO);
		historyData.put("metadata", metaData);
		log.info("Operation completed...");
		return historyData;
	}

	@Override
	public List<LegacyCompaniesDTO> getCompanies() {
		log.info("getCompanies start");
		List<LegacyCompanies> companyNames = new ArrayList<>();
		companyNames = legacyCompaniesRepository.getCustomerNames();
		log.info("companyname list" + companyNames);
		// List<LegacyCompaniesDTO> companyList = toDTOList(companyNames);

		List<LegacyCompaniesDTO> dtoList = new ArrayList<LegacyCompaniesDTO>(companyNames.size());

		for (LegacyCompanies entity : companyNames) {
			dtoList.add(toDTO(entity));
		}

		Map<String, Object> companies = new HashMap<>();
		Metadata metaData = new Metadata();
		if (dtoList.size() > 0) {
			log.info("Data is fetched");
			metaData.setCountOfRecords(dtoList.size());
			metaData.setMessage("SUCCESS");
		} else {
			log.info("No data available to fetch");
			metaData.setCountOfRecords(dtoList.size());
			metaData.setMessage("NO_CONTENT");
		}

		log.info("Operation completed...");
		return dtoList;
	}

	@Override
	public int getHistoryParameteredDataCount(String ticketId, String fromDate, String toDate, String customerName) throws ParseException  {
		log.info("TicketId : " + ticketId + "From Date : " + fromDate + "To Date : " +toDate+ "Source Customer Name :" + customerName);		
		log.info("Ticket Length" + ticketId.length());
		int historyCount = 0;
		HashMap<String, String> customersMap = new HashMap<String, String>();
		String fromTime = null, toTime = null;
		if (fromDate != null && toDate != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fdate = formatter.parse(fromDate);
			Date tdate = formatter.parse(toDate);
			tdate.setHours(23);
			tdate.setMinutes(59);
			tdate.setSeconds(59);
			SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fromTime = formatterTime.format(new Timestamp(fdate.getTime()));
			toTime = formatterTime.format(new Timestamp(tdate.getTime()));
			log.info("**********"+fromTime +"******" + toTime);
		}
		if (customerName != "" && !customerName.isEmpty()) {
			log.info("Getting targetcompany");
			String targetCust = legacyCompaniesRepository.getTargetCustomers(customerName);
			customersMap.put(customerName, targetCust);
			log.info("Target Company : " + customersMap.get(customerName));
		}
		if (ticketId.length() == 15) {
			if (fromDate != null && toDate != null) {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get count using ticket Id for a particular time period
				historyCount = legacyEbondingHistoryRepository.getRemedyHistoryDataCountWithDateRangeAndTicketId(ticketId, fromTime, toTime);
			} else {
				// This method will get the count only with ticket Id
				historyCount = legacyEbondingHistoryRepository.getRemedyHistoryDataCountWithTicketId(ticketId);
			}
		} else if (ticketId.length() == 10) {
			if (fromDate != null && toDate != null) {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get count using ticket Id for a particular time period
				historyCount = legacyEbondingHistoryRepository.getCustomerHistoryDataCountWithDateRangeAndTicketId(ticketId, fromTime, toTime);
			} else {
				// This method will get the count only with ticket Id
				historyCount = legacyEbondingHistoryRepository.getCustomerHistoryDataCountWithTicketId(ticketId);
			}
		} else if (ticketId.length() == 0) {
			if (fromDate != null && toDate != null) {
				if (!customerName.isEmpty()) {
					log.info("Fetching count with customer name and from data and to data");
					historyCount = legacyEbondingHistoryRepository.getRemedyHistoryDataCountWithDateRangeSrcTarget(fromTime, toTime, customerName, customersMap.get(customerName));
				} else {
					log.info("Fetching data with from data and to data");
					historyCount = legacyEbondingHistoryRepository.getRemedyHistoryDataCountWithDateRange(fromTime, toTime);
				}
			}
		}

		log.info("Operation completed...");
		return historyCount;
	}
}