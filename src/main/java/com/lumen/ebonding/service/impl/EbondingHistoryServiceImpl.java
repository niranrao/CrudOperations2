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

import com.lumen.ebonding.dto.EbondingHistoryDTO;
import com.lumen.ebonding.model.EbondingHistory;
import com.lumen.ebonding.model.Metadata;
import com.lumen.ebonding.repository.EbondingHistoryRepository;
import com.lumen.ebonding.service.EbondingHistoryService;

@Service
public class EbondingHistoryServiceImpl extends GenericServiceImpl<EbondingHistory, Integer, EbondingHistoryDTO>
		implements EbondingHistoryService {

	@Autowired
	private EbondingHistoryRepository ebondingHistoryRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(EbondingHistoryServiceImpl.class);

	@Override
	public EbondingHistory toEntity(EbondingHistoryDTO dto) {
		EbondingHistory entity = new EbondingHistory();
		mapper.map(dto, entity);
		return entity;
	}

	@Override
	public EbondingHistoryDTO toDTO(EbondingHistory entity) {
		EbondingHistoryDTO dto = new EbondingHistoryDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return ebondingHistoryRepository;
	}

	@Override
	public int getHistoryDataCount() {
		log.info("Fetching the count data for 24 hrs");
		int count = ebondingHistoryRepository.get24HrsDataCountOnLoad();
		return count;
	}

	// method to get data of Last 24 hours with Start Index for incidents and
	// changes
	@Override
	public Map<String, Object> getHistoryDataWithSindex(int startIndex) {
		log.info("Fetching the Last 24 hours data");	
		List<EbondingHistory> historyModified = new ArrayList<>();
		historyModified= ((EbondingHistoryRepository) getRepository()).get24HrsDataWithSindexOnLoad(startIndex);
	

		/*for (Object data : history) {
			EbondingHistory historyObj = new EbondingHistory();
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

	// Method to retrieve History data for Incident or Change requests using ticket
	// id and particular time period. Time period is optional
	@Override
	public Map<String, Object> getHistoryParameteredData(String ticketId, String fromDate, String toDate,
			String businessProcess, String customerName, int sIndex) throws ParseException {
		log.info("TicketId : " + ticketId + "From Date : " + fromDate + "To Date : " +toDate+ "BusinessProcess : "
				+ businessProcess + "Source Customer Name :" + customerName);
		List<EbondingHistory> history = new ArrayList<>();
		String fromTime = null, toTime = null;
		HashMap<String, String> customersMap = new HashMap<String, String>();
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
			String targetCust = ebondingHistoryRepository.getTargetCustomers(customerName);
			customersMap.put(customerName, targetCust);
			log.info("Target Company : " + customersMap.get(customerName));
		}
		if (ticketId.length() == 15) {
			if (fromDate != "" && toDate != "") {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get data using ticket Id for a particular time period
				history = ((EbondingHistoryRepository) getRepository()).getRemedyHistoryDataWithDateRangeAndTicketId(ticketId, fromTime, toTime, sIndex);
			} else {
				// This method will get the data only with ticket Id
				history = ((EbondingHistoryRepository) getRepository()).getRemedyHistoryDataWithTicketId(ticketId, sIndex);
			}
		} else if (ticketId.length() == 11) {
			if (fromDate != "" && toDate != "") {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get data using ticket Id for a particular time period
				history = ((EbondingHistoryRepository) getRepository()).getCustomerHistoryWithDateRangeAndTicketId(ticketId, fromTime, toTime, sIndex);
			} else {
				// This method will get the data only with ticket Id
				history = ((EbondingHistoryRepository) getRepository()).getCustomerHistoryDataWithTicketId(ticketId, sIndex);
			}
		} else if (ticketId.length() == 0) {
			if (fromDate != "" && toDate != "") {
				Set<String> ticketSet = new HashSet<>();
				Set<String> customerTicketSet = new HashSet<>();
				List<EbondingHistory> historyDataWithInPeriod = null;
				if (!customerName.isEmpty() && !businessProcess.isEmpty()) {
					if (businessProcess.equalsIgnoreCase("Change")) {
						log.info("fetching change tickets with customerName and businessProcess");
						history = ((EbondingHistoryRepository) getRepository())
								.getHistoryDataWithInPeriodAndBPChangeCustName(fromTime, toTime, customerName, customersMap.get(customerName), sIndex);
						log.info("list size" + history.size());
					} else if (businessProcess.equalsIgnoreCase("Incident")) {
						log.info("fetching incident tickets customerName and businessProcess");
						history = ((EbondingHistoryRepository) getRepository())
								.getHistoryDataWithInPeriodAndBPIncidentCustName(fromTime, toTime, customerName, customersMap.get(customerName), sIndex);
						log.info("list size" + history.size());
					}
				} else if (customerName.isEmpty() && !businessProcess.isEmpty()) {
					if (businessProcess.equalsIgnoreCase("Change")) {
						log.info("fetching change tickets with businessProcess");
						history = ((EbondingHistoryRepository) getRepository())
								.getHistoryDataWithInPeriodAndBPChange(fromTime, toTime, sIndex);
						log.info("list size" + history.size());
					} else if (businessProcess.equalsIgnoreCase("Incident")) {
						log.info("fetching incident tickets with businessProcess");
						history = ((EbondingHistoryRepository) getRepository())
								.getHistoryDataWithInPeriodAndBPIncident(fromTime, toTime, sIndex);
						log.info("list size" + history.size());
					}
				} else if (!customerName.isEmpty() && businessProcess.isEmpty()) {
					log.info("fetching tickets with customerName");
					history = ((EbondingHistoryRepository) getRepository())
							.getHistoryDataWithInPeriodAndCustName(fromTime, toTime, customerName, customersMap.get(customerName), sIndex);
				} else {
					log.info("***Fetching for the time period from " + fromTime + " to " + toTime);
					history = ((EbondingHistoryRepository) getRepository())
							.getHistoryDataWithInPeriod(fromTime, toTime, sIndex);
					log.info("***********"+ history.size());
				}
				// To get latest transaction of the ticket
				/*
				for (EbondingHistory data : historyDataWithInPeriod) {
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
							&& data.getCustomerTicketId().length() == 11
							&& !customerTicketSet.contains(data.getCustomerTicketId())) {
						history.add(data);
						customerTicketSet.add(data.getCustomerTicketId());
					}
				}*/
			}
		}
		List<EbondingHistoryDTO> historyDTO = toDTOList(history);
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
		String rcvdPayload = ((EbondingHistoryRepository) getRepository()).getReceivedPayload(mappingId);
		String transPayload = ((EbondingHistoryRepository) getRepository()).getTransformedPayload(mappingId);
		String rspInfo = ((EbondingHistoryRepository) getRepository()).getResponseInfo(mappingId);
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
		List<EbondingHistory> history = new ArrayList<>();
		System.out.println(ticketId);
		if (ticketId.length() == 15) {
			history = ((EbondingHistoryRepository) getRepository()).getRemedyHistoryDataOfTicket(ticketId);
		} else if (ticketId.length() == 11) {
			history = ((EbondingHistoryRepository) getRepository()).getCustomerHistoryDataOfTicket(ticketId);
		}
		List<EbondingHistoryDTO> historyDTO = toDTOList(history);
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
	public int getHistoryParameteredDataCount(String ticketId, String fromDate, String toDate, String businessProcess, String customerName) throws ParseException {
		log.info("TicketId : " + ticketId + "From Date : " + fromDate + "To Date : " +toDate+ "Source Customer Name :" + customerName);		
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
		}
		if (customerName != "" && !customerName.isEmpty()) {
			log.info("Getting targetcompany");
			String targetCust = ebondingHistoryRepository.getTargetCustomers(customerName);
			customersMap.put(customerName, targetCust);
			log.info("Target Company : " + customersMap.get(customerName));
		}
		if (ticketId.length() == 15) {
			if (fromDate != null && toDate != null) {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get count using ticket Id for a particular time period
				historyCount = ebondingHistoryRepository.getRemedyHistoryDataCountWithDateRangeAndTicketId(ticketId, fromTime, toTime);
			} else {
				// This method will get the count only with ticket Id
				historyCount = ebondingHistoryRepository.getRemedyHistoryDataCountWithTicketId(ticketId);
			}
		} else if (ticketId.length() == 11) {
			if (fromDate != null && toDate != null) {
				log.info("Fetching for the time period from " + fromTime + " to " + toTime);
				// This method will get count using ticket Id for a particular time period
				historyCount = ebondingHistoryRepository.getCustomerHistoryDataCountWithDateRangeAndTicketId(ticketId, fromTime, toTime);
			} else {
				// This method will get the count only with ticket Id
				historyCount = ebondingHistoryRepository.getCustomerHistoryDataCountWithTicketId(ticketId);
			}
		} else if (ticketId.length() == 0) {
			if (fromDate != "" && toDate != "") {
				if (!customerName.isEmpty() && !businessProcess.isEmpty()) {
					if (businessProcess.equalsIgnoreCase("Change")) {
						log.info("fetching change tickets count with customerName and businessProcess");
						historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodAndBPChangeCustNameCount(fromTime, toTime, customerName, customersMap.get(customerName));
					} else if (businessProcess.equalsIgnoreCase("Incident")) {
						log.info("fetching incident tickets customerName and businessProcess");
						historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodAndBPIncidentCustNameCount(fromTime, toTime, customerName, customersMap.get(customerName));
					}
				} else if (customerName.isEmpty() && !businessProcess.isEmpty()) {
					if (businessProcess.equalsIgnoreCase("Change")) {
						log.info("fetching change tickets with businessProcess");
						historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodAndBPChangeCount(fromTime, toTime);
					} else if (businessProcess.equalsIgnoreCase("Incident")) {
						log.info("fetching incident tickets with businessProcess");
						historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodAndBPIncidentCount(fromTime, toTime);
					}
				} else if (!customerName.isEmpty() && businessProcess.isEmpty()) {
					log.info("fetching tickets with customerName");
					historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodAndCustNameCount(fromTime, toTime, customerName, customersMap.get(customerName));
				} else {
					log.info("Fetching for the time period from " + fromTime + " to " + toTime);
					historyCount = ebondingHistoryRepository.getHistoryDataWithInPeriodCount(fromTime, toTime);
				}
			}
		}
		log.info("Operation completed...");
		return historyCount;
	}
}
