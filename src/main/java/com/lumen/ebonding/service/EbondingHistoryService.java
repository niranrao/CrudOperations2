package com.lumen.ebonding.service;

import java.text.ParseException;
import java.util.Map;

import com.lumen.ebonding.dto.EbondingHistoryDTO;
import com.lumen.ebonding.model.EbondingHistory;

public interface EbondingHistoryService extends GenericService<EbondingHistory, Integer, EbondingHistoryDTO>{

	public Map<String,Object> getHistoryDataWithSindex(int startIndex);
	public int getHistoryDataCount();
	public Map<String,Object> getHistoryParameteredData(String ticketId,String fromDate,String toDate,String businessProcess,String customerName, int sIndex) throws ParseException;
	public Map<String,Object> getPayload(int masterId);
	public Map<String, Object> getHistoryOfTicket(String ticketId);
	
	public int getHistoryParameteredDataCount(String ticketId,String fromDate,String toDate, String businessProcess,String customerName) throws ParseException;
}
