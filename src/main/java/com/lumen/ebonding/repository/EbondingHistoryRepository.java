package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lumen.ebonding.model.EbondingHistory;
import com.lumen.ebonding.model.LegacyEbondingHistory;

/**
 * Repository class to manage History for both Incident and Change Requests
 * 
 * @author AC96728
 *
 */
public interface EbondingHistoryRepository extends JpaRepository<EbondingHistory, Integer> {
	/*Below query is retrieve duplicate rows from database.We have changed all search operation query with from date,to date,customer name,business process
	 * to remove duplicate rows.
	@Query(nativeQuery = true, value="SELECT COUNT(*) FROM ((SELECT e1.remedy_ticket_id, e1.customer_ticket_id, MAX(e1.mapping_id), e1.master_id, e1.request_from, e1.request_to, " + 
			"e1.remedy_ticket_status, e1.customer_ticket_status, e1.ebonding_result, e1.transcation_result, " + 
			"MAX(e1.created_time) as createId FROM legacy_ebonding.ebonding_master_audit AS e1 WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) " + 
			"AND e1.remedy_ticket_id is NOT NULL Group By e1.remedy_ticket_id) UNION (SELECT e2.remedy_ticket_id, e2.customer_ticket_id, MAX(e2.mapping_id), e2.master_id, e2.request_from, e2.request_to, " + 
			"e2.remedy_ticket_status, e2.customer_ticket_status, e2.ebonding_result, e2.transcation_result, " + 
			"MAX(e2.created_time) as createId FROM legacy_ebonding.ebonding_master_audit AS e2 WHERE e2.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) " + 
			"AND e2.customer_ticket_id is NOT NULL Group By e2.customer_ticket_id)) M1")
	public int get24HrsDataCountOnLoad();*/

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int get24HrsDataCountOnLoad();

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> get24HrsDataWithSindexOnLoad(@Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM (SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), master_id, request_from, request_to,"
			+ "remedy_ticket_status, customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) "
			+ "FROM legacy_ebonding.ebonding_master_audit " + "WHERE remedy_ticket_id = :ticketId "
			+ "AND (created_time BETWEEN :fdate AND :tdate)) M1")
	public int getRemedyHistoryDataCountWithDateRangeAndTicketId(@Param("ticketId") String ticketId,
			@Param("fdate") String fdate, @Param("tdate") String tdate);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM (SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), master_id, request_from, request_to, "
			+ "remedy_ticket_status, customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) "
			+ "FROM legacy_ebonding.ebonding_master_audit " + "WHERE remedy_ticket_id = :ticketId ) M1")
	public int getRemedyHistoryDataCountWithTicketId(@Param("ticketId") String ticketId);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM (SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), master_id, request_from, request_to, "
			+ "remedy_ticket_status, customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) "
			+ "FROM legacy_ebonding.ebonding_master_audit "
			+ "WHERE customer_ticket_id = :ticketId AND (created_time BETWEEN :fdate AND :tdate)) M1")
	public int getCustomerHistoryDataCountWithDateRangeAndTicketId(@Param("ticketId") String ticketId,
			@Param("fdate") String fdate, @Param("tdate") String tdate);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM (SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), master_id, request_from, request_to, "
			+ "remedy_ticket_status, customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) "
			+ "FROM legacy_ebonding.ebonding_master_audit " + "WHERE customer_ticket_id = :ticketId) M1")
	public int getCustomerHistoryDataCountWithTicketId(@Param("ticketId") String ticketId);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodAndBPChangeCustNameCount(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sourceComp") String sourceComp,
			@Param("targetComp") String targetComp);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodAndBPIncidentCustNameCount(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sourceComp") String sourceComp,
			@Param("targetComp") String targetComp);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodAndBPChangeCount(@Param("fdate") String fdate, @Param("tdate") String tdate);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodAndBPIncidentCount(@Param("fdate") String fdate, @Param("tdate") String tdate);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodAndCustNameCount(@Param("fdate") String fdate, @Param("tdate") String tdate,
			@Param("sourceComp") String sourceComp, @Param("targetComp") String targetComp);

	@Query(nativeQuery = true, value = "select count(*) from ((SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate   AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate   AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id)) m1;")
	public int getHistoryDataWithInPeriodCount(@Param("fdate") String fdate, @Param("tdate") String tdate);

	@Query(nativeQuery = true, value = "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id) as mapping_id , master_id, request_from, request_to, remedy_ticket_status, "
			+ "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) as created_time FROM legacy_ebonding.ebonding_master_audit "
			+ "WHERE (remedy_ticket_id = :ticketId AND (created_time between :fdate AND :tdate)) "
			+ "ORDER BY created_time DESC limit :sIndex, 25 ")
	public List<EbondingHistory> getRemedyHistoryDataWithDateRangeAndTicketId(@Param("ticketId") String ticketId,
			@Param("fdate") String fdate, @Param("tdate") String tdate, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id) as mapping_id, master_id, request_from, request_to, remedy_ticket_status,"
			+ "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) as created_time FROM legacy_ebonding.ebonding_master_audit "
			+ "WHERE remedy_ticket_id = :ticketId ORDER BY created_time DESC limit :sIndex, 25")
	public List<EbondingHistory> getRemedyHistoryDataWithTicketId(@Param("ticketId") String ticketId,
			@Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id) as mapping_id, master_id, request_from, request_to, remedy_ticket_status,"
			+ "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) as created_time FROM legacy_ebonding.ebonding_master_audit "
			+ "WHERE (customer_ticket_id = :ticketId AND (created_time BETWEEN :fdate AND :tdate)) ORDER BY created_time DESC limit :sIndex, 25")
	public List<EbondingHistory> getCustomerHistoryWithDateRangeAndTicketId(@Param("ticketId") String ticketId,
			@Param("fdate") String fdate, @Param("tdate") String tdate, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id) as mapping_id, master_id, request_from, request_to, remedy_ticket_status,"
			+ "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) as created_time  FROM legacy_ebonding.ebonding_master_audit "
			+ "WHERE customer_ticket_id= :ticketId ORDER BY created_time DESC limit :sIndex, 25")
	public List<EbondingHistory> getCustomerHistoryDataWithTicketId(@Param("ticketId") String ticketId,
			@Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriodAndBPChangeCustName(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sourceComp") String sourceComp,
			@Param("targetComp") String targetComp, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriodAndBPIncidentCustName(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sourceComp") String sourceComp,
			@Param("targetComp") String targetComp, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'CHG%' OR remedy_ticket_id LIKE 'CRQ%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriodAndBPChange(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate AND (customer_ticket_id LIKE 'INC%' OR remedy_ticket_id LIKE 'INC%') AND  NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriodAndBPIncident(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate  AND (organization_name = :sourceComp OR organization_name = :targetComp) AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriodAndCustName(@Param("fdate") String fdate,
			@Param("tdate") String tdate, @Param("sourceComp") String sourceComp,
			@Param("targetComp") String targetComp, @Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "(SELECT e1.remedy_ticket_id,e1.customer_ticket_id, e1.mapping_id,e1.master_id,e1.request_from,e1.request_to,e1.remedy_ticket_status,e1.customer_ticket_status,e1.ebonding_result,e1.transcation_result,e1.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL \r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id,e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from,e2.request_to,e2.remedy_ticket_status,e2.customer_ticket_status,e2.ebonding_result,e2.transcation_result,e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate   AND NULLIF(e2.remedy_ticket_id, '') IS NULL and e2.customer_ticket_id not in (SELECT e1.customer_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.customer_ticket_id)\r\n" + 
			"UNION\r\n" + 
			"(SELECT e2.remedy_ticket_id, e2.customer_ticket_id, e2.mapping_id, e2.master_id,e2.request_from, e2.request_to, e2.remedy_ticket_status, e2.customer_ticket_status,e2.ebonding_result, e2.transcation_result, e2.created_time\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e2\r\n" + 
			"WHERE e2.created_time between :fdate and :tdate   AND NULLIF(e2.customer_ticket_id, '') IS NULL and e2.remedy_ticket_id not in (SELECT e1.remedy_ticket_id\r\n" + 
			"FROM legacy_ebonding.ebonding_master_audit AS e1\r\n" + 
			"WHERE e1.created_time between :fdate and :tdate   AND NULLIF(e1.remedy_ticket_id , '') IS NOT NULL and NULLIF(e1.customer_ticket_id , '') IS NOT NULL\r\n" + 
			"GROUP BY e1.remedy_ticket_id, e1.customer_ticket_id)\r\n" + 
			"GROUP BY e2.remedy_ticket_id) limit :sIndex,25;")
	public List<EbondingHistory> getHistoryDataWithInPeriod(@Param("fdate") String fdate, @Param("tdate") String tdate,
			@Param("sIndex") int sIndex);

	@Query(nativeQuery = true, value = "SELECT received_payload FROM legacy_ebonding.ebonding_master_audit WHERE mapping_id = :mappingId")
	public String getReceivedPayload(@Param("mappingId") int mappingId);

	@Query(nativeQuery = true, value = "SELECT transform_payload FROM legacy_ebonding.ebonding_master_audit WHERE mapping_id = :mappingId")
	public String getTransformedPayload(@Param("mappingId") int mappingId);

	@Query(nativeQuery = true, value = "SELECT response_info FROM legacy_ebonding.ebonding_master_audit WHERE mapping_id = :mappingId")
	public String getResponseInfo(@Param("mappingId") int mappingId);

	/*
	 * @Query(nativeQuery = true,value =
	 * "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), request_from, request_to, remedy_ticket_status,"
	 * +
	 * "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) FROM legacy_ebonding.ebonding_master_audit "
	 * +
	 * "WHERE ( remedy_ticket_id = :ticketId OR ((remedy_ticket_id IS null OR length(remedy_ticket_id)!=15) AND customer_ticket_id "
	 * +
	 * "IN (SELECT customer_ticket_id FROM legacy_ebonding.ebonding_master_audit WHERE remedy_ticket_id = :ticketId AND customer_ticket_id IS NOT null "
	 * + "AND length(customer_ticket_id)=11 ))) ORDER BY created_time DESC") public
	 * List<EbondingHistory> getRemedyHistoryDataOfTicket(@Param("ticketId") String
	 * ticketId);
	 * 
	 * @Query(nativeQuery = true,value =
	 * "SELECT distinct remedy_ticket_id, customer_ticket_id, MAX(mapping_id), request_from, request_to, remedy_ticket_status, "
	 * +
	 * "customer_ticket_status, ebonding_result, transcation_result, MAX(created_time) FROM legacy_ebonding.ebonding_master_audit "
	 * +
	 * "WHERE customer_ticket_id= :ticketId OR ((customer_ticket_id IS null OR length(customer_ticket_id)!=11) "
	 * +
	 * "AND remedy_ticket_id = (SELECT remedy_ticket_id FROM legacy_ebonding.ebonding_master_audit WHERE customer_ticket_id= :ticketId "
	 * +
	 * "AND remedy_ticket_id IS NOT null AND length(remedy_ticket_id) =15 limit 1)) ORDER BY created_time DESC"
	 * ) public List<EbondingHistory>
	 * getCustomerHistoryDataOfTicket(@Param("ticketId") String ticketId);
	 */

	@Query(nativeQuery = true, value = "select mapping_id,master_id, remedy_ticket_id,customer_ticket_id,request_from,request_to,remedy_ticket_status,customer_ticket_status,ebonding_result,transcation_result,created_time from legacy_ebonding.ebonding_master_audit WHERE ( remedy_ticket_id = :ticketId or ( (remedy_ticket_id is null or length(remedy_ticket_id)!=15) and customer_ticket_id in (select customer_ticket_id from legacy_ebonding.ebonding_master_audit where remedy_ticket_id = :ticketId and customer_ticket_id is not null and length(customer_ticket_id)=11 ))) order by created_time desc")
	public List<EbondingHistory> getRemedyHistoryDataOfTicket(@Param("ticketId") String ticketId);

	@Query(nativeQuery = true, value = "select mapping_id,master_id, remedy_ticket_id,customer_ticket_id,request_from,request_to,remedy_ticket_status,customer_ticket_status,ebonding_result,transcation_result,created_time from legacy_ebonding.ebonding_master_audit WHERE customer_ticket_id= :ticketId or ((customer_ticket_id is null or length(customer_ticket_id)!=11) and remedy_ticket_id = (select remedy_ticket_id from legacy_ebonding.ebonding_master_audit where customer_ticket_id= :ticketId and remedy_ticket_id is not null and length(remedy_ticket_id) =15 limit 1)) order by created_time desc")
	public List<EbondingHistory> getCustomerHistoryDataOfTicket(@Param("ticketId") String ticketId);

	@Query(nativeQuery = true, value = "SELECT taget_company FROM legacy_ebonding.ebonding_companies WHERE source_company = :customerName")
	public String getTargetCustomers(@Param("customerName") String customerName);
}