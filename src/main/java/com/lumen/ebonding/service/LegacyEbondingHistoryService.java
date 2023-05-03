package com.lumen.ebonding.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lumen.ebonding.dto.LegacyCompaniesDTO;
import com.lumen.ebonding.dto.LegacyEbondingHistoryDTO;
import com.lumen.ebonding.model.LegacyCompanies;
import com.lumen.ebonding.model.LegacyEbondingHistory;

public interface LegacyEbondingHistoryService extends GenericService<LegacyEbondingHistory,Integer,LegacyEbondingHistoryDTO> {

	public Map<String,Object> getHistoryDataWithSindex(int startIndex);
	public Map<String,Object> getHistoryParameteredData(String ticketId,String fromDate,String toDate,String customerName, int sIndex) throws ParseException;
	public int getHistoryParameteredDataCount(String ticketId,String fromDate,String toDate,String customerName) throws ParseException ;
	public Map<String,Object> getPayload(int masterId);
	public Map<String, Object> getHistoryOfTicket(String ticketId);
	public List<LegacyCompaniesDTO> getCompanies();
	public LegacyCompaniesDTO toDTO(LegacyCompanies entity) ;
	public int getHistoryDataCount();
}
