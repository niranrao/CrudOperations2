package com.lumen.ebonding.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class to maintain Customer
 * 
 * @author Devesh Joshi
 * @since Oct 12, 2020
 */
@Entity
@Table(name = "conf_customer_details")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_customerId")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@Column(name = "cust_name")
	private String customerName;

	@Column(name = "cust_ebonding_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondingStartDate;

	@Column(name = "cust_ebonding_end_date")
	private Date ebondingEndDate;

	@Column(name = "cust_time_zone")
	private String timeZone;

	@Column(name = "cust_ticket_system")
	private String custTicketSystem;

	@Column(name = "cust_spoc_name")
	private String custSpocName;

	@Column(name = "cust_spoc_email")
	private String custSpocEmail;

	@Column(name = "cust_spoc_contact")
	private String custSpocContact;

	@Column(name = "cust_email_id")
	private String custEmail;

	@Column(name = "lumen_ops_spoc_name")
	private String lumenOpsSpocName;

	@Column(name = "lumen_ops_spoc_email")
	private String lumenOpsSpocEmail;

	@Column(name = "lumen_escalation_spoc_name")
	private String lumenEscalationSpocName;

	@Column(name = "lumen_escalation_spoc_email_id")
	private String lumenEscalationSpocEmailid;

	@Column(name = "cust_onboard_date")
	private Date custOnboardDate;

	@Column(name = "cust_status")
	private boolean active;

	@Column(name = "ebond_incident")
	private boolean ebondIncident;

	@Column(name = "ebond_change_request")
	private boolean ebondChangeRequest;

	@Column(name = "ebond_service_request")
	private boolean ebondServiceRequest;

	@Column(name = "ebond_problem")
	private boolean ebondProblem;

	@Column(name = "ebond_cmdb")
	private boolean ebondCmdb;

	@Column(name = "cmdb_ebond_direction")
	private String cmdbEbondDirection;

	@Column(name = "comment")
	private String comment;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "ebond_incident_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondIncidentStartDate;

	@Column(name = "ebond_problem_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondProblemStartDate;

	@Column(name = "ebond_change_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondChangeStartDate;

	@Column(name = "ebond_service_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondServiceStartDate;

	@Column(name = "ebond_cmdb_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ebondCmdbStartDate;

	@Column(name = "scheduled")
	private boolean scheduled;

	@Column(name = "response_required")
	private boolean responseRequired;

	@Column(name = "support_api_id")
	private int supportAPIDetailsId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getEbondingStartDate() {
		return ebondingStartDate;
	}

	public void setEbondingStartDate(Date ebondingStartDate) {
		this.ebondingStartDate = ebondingStartDate;
	}

	public Date getEbondingEndDate() {
		return ebondingEndDate;
	}

	public void setEbondingEndDate(Date ebondingEndDate) {
		this.ebondingEndDate = ebondingEndDate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCustTicketSystem() {
		return custTicketSystem;
	}

	public void setCustTicketSystem(String custTicketSystem) {
		this.custTicketSystem = custTicketSystem;
	}

	public String getCustSpocName() {
		return custSpocName;
	}

	public void setCustSpocName(String custSpocName) {
		this.custSpocName = custSpocName;
	}

	public String getCustSpocEmail() {
		return custSpocEmail;
	}

	public void setCustSpocEmail(String custSpocEmail) {
		this.custSpocEmail = custSpocEmail;
	}

	public String getCustSpocContact() {
		return custSpocContact;
	}

	public void setCustSpocContact(String custSpocContact) {
		this.custSpocContact = custSpocContact;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getLumenOpsSpocName() {
		return lumenOpsSpocName;
	}

	public void setLumenOpsSpocName(String lumenOpsSpocName) {
		this.lumenOpsSpocName = lumenOpsSpocName;
	}

	public String getLumenOpsSpocEmail() {
		return lumenOpsSpocEmail;
	}

	public void setLumenOpsSpocEmail(String lumenOpsSpocEmail) {
		this.lumenOpsSpocEmail = lumenOpsSpocEmail;
	}

	public String getLumenEscalationSpocName() {
		return lumenEscalationSpocName;
	}

	public void setLumenEscalationSpocName(String lumenEscalationSpocName) {
		this.lumenEscalationSpocName = lumenEscalationSpocName;
	}

	public String getLumenEscalationSpocEmailid() {
		return lumenEscalationSpocEmailid;
	}

	public void setLumenEscalationSpocEmailid(String lumenEscalationSpocEmailid) {
		this.lumenEscalationSpocEmailid = lumenEscalationSpocEmailid;
	}

	public Date getCustOnboardDate() {
		return custOnboardDate;
	}

	public void setCustOnboardDate(Date custOnboardDate) {
		this.custOnboardDate = custOnboardDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEbondIncident() {
		return ebondIncident;
	}

	public void setEbondIncident(boolean ebondIncident) {
		this.ebondIncident = ebondIncident;
	}

	public boolean isEbondChangeRequest() {
		return ebondChangeRequest;
	}

	public void setEbondChangeRequest(boolean ebondChangeRequest) {
		this.ebondChangeRequest = ebondChangeRequest;
	}

	public boolean isEbondServiceRequest() {
		return ebondServiceRequest;
	}

	public void setEbondServiceRequest(boolean ebondServiceRequest) {
		this.ebondServiceRequest = ebondServiceRequest;
	}

	public boolean isEbondProblem() {
		return ebondProblem;
	}

	public void setEbondProblem(boolean ebondProblem) {
		this.ebondProblem = ebondProblem;
	}

	public boolean isEbondCmdb() {
		return ebondCmdb;
	}

	public void setEbondCmdb(boolean ebondCmdb) {
		this.ebondCmdb = ebondCmdb;
	}

	public String getCmdbEbondDirection() {
		return cmdbEbondDirection;
	}

	public void setCmdbEbondDirection(String cmdbEbondDirection) {
		this.cmdbEbondDirection = cmdbEbondDirection;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getEbondIncidentStartDate() {
		return ebondIncidentStartDate;
	}

	public void setEbondIncidentStartDate(Date ebondIncidentStartDate) {
		this.ebondIncidentStartDate = ebondIncidentStartDate;
	}

	public Date getEbondProblemStartDate() {
		return ebondProblemStartDate;
	}

	public void setEbondProblemStartDate(Date ebondProblemStartDate) {
		this.ebondProblemStartDate = ebondProblemStartDate;
	}

	public Date getEbondChangeStartDate() {
		return ebondChangeStartDate;
	}

	public void setEbondChangeStartDate(Date ebondChangeStartDate) {
		this.ebondChangeStartDate = ebondChangeStartDate;
	}

	public Date getEbondServiceStartDate() {
		return ebondServiceStartDate;
	}

	public void setEbondServiceStartDate(Date ebondServiceStartDate) {
		this.ebondServiceStartDate = ebondServiceStartDate;
	}

	public Date getEbondCmdbStartDate() {
		return ebondCmdbStartDate;
	}

	public void setEbondCmdbStartDate(Date ebondCmdbStartDate) {
		this.ebondCmdbStartDate = ebondCmdbStartDate;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public boolean isResponseRequired() {
		return responseRequired;
	}

	public void setResponseRequired(boolean responseRequired) {
		this.responseRequired = responseRequired;
	}

	public int getSupportAPIDetailsId() {
		return supportAPIDetailsId;
	}

	public void setSupportAPIDetailsId(int supportAPIDetailsId) {
		this.supportAPIDetailsId = supportAPIDetailsId;
	}

}
