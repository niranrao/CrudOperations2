package com.lumen.ebonding.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * Customer configuration to save the business processes and credentials for the
 * customer
 * 
 * @author Devesh Joshi
 * @since Oct 13, 2020
 *
 */

@Entity
@Table(name = "conf_customer_configuration")
public class CustomerConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pk_configId")
	private Integer id;

	@Version
	@Column(name = "config_version")
	private Integer version;

	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "fk_customerId", referencedColumnName = "pk_customerId")
	private Customer customer;

	@Column(name = "conf_business_process", nullable = false, length = 45)
	private String businessProcess;

	@Column(name = "conf_action", nullable = false, length = 45)
	private String action;

	@Column(name = "conf_source", nullable = false, length = 45)
	private String source;

	@Column(name = "conf_target", nullable = false, length = 45)
	private String target;

	@Column(name = "conf_status")
	private boolean active;

	@Column(name = "conf_ci_class", length = 45)
	private String ciClass;

	@Column(name = "conf_ci_type", length = 45)
	private String ciType;

	@Column(name = "customer_user_id", length = 45)
	private String customerUserId;

	@Column(name = "customer_password", length = 45)
	private String customerPassword;

	@Column(name = "modified_by")
	private String modifedBy;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "ci_type_subtype")
	private String ciTypeSubType;

	@Column(name = "ticket_sys_company_name")
	private String ticketSysCompanyName;

	@Column(name = "src_tool_date_format")
	private String srcToolDateFormat;

	@Column(name = "src_ticket_create_dt_field")
	private String srcTicketCreateDateField;

	@Column(name = "src_ticket_update_dt_field")
	private String srcTicketUpdateDateField;

	@Column(name = "sb_component")
	private String sbComponent;

	@Column(name = "target_tool_user_id")
	private String targetToolUserId;

	@Column(name = "target_user_id")
	private String targetUserId;

	@Column(name = "target_password")
	private String targetPassword;

	@Column(name = "src_ticket_id_field")
	private String srcTicketIdField;

	@Column(name = "src_external_id_field")
	private String srcExternalIdField;

	@Column(name = "target_ticket_id_field")
	private String targetTicketIdField;

	@Column(name = "target_external_id_field")
	private String targetExternalIdField;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCiClass() {
		return ciClass;
	}

	public void setCiClass(String ciClass) {
		this.ciClass = ciClass;
	}

	public String getCiType() {
		return ciType;
	}

	public void setCiType(String ciType) {
		this.ciType = ciType;
	}

	public String getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(String customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getModifedBy() {
		return modifedBy;
	}

	public void setModifedBy(String modifedBy) {
		this.modifedBy = modifedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCiTypeSubType() {
		return ciTypeSubType;
	}

	public void setCiTypeSubType(String ciTypeSubType) {
		this.ciTypeSubType = ciTypeSubType;
	}

	public String getTicketSysCompanyName() {
		return ticketSysCompanyName;
	}

	public void setTicketSysCompanyName(String ticketSysCompanyName) {
		this.ticketSysCompanyName = ticketSysCompanyName;
	}

	public String getSrcToolDateFormat() {
		return srcToolDateFormat;
	}

	public void setSrcToolDateFormat(String srcToolDateFormat) {
		this.srcToolDateFormat = srcToolDateFormat;
	}

	public String getSrcTicketCreateDateField() {
		return srcTicketCreateDateField;
	}

	public void setSrcTicketCreateDateField(String srcTicketCreateDateField) {
		this.srcTicketCreateDateField = srcTicketCreateDateField;
	}

	public String getSrcTicketUpdateDateField() {
		return srcTicketUpdateDateField;
	}

	public void setSrcTicketUpdateDateField(String srcTicketUpdateDateField) {
		this.srcTicketUpdateDateField = srcTicketUpdateDateField;
	}

	public String getSbComponent() {
		return sbComponent;
	}

	public void setSbComponent(String sbComponent) {
		this.sbComponent = sbComponent;
	}

	public String getTargetToolUserId() {
		return targetToolUserId;
	}

	public void setTargetToolUserId(String targetToolUserId) {
		this.targetToolUserId = targetToolUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetPassword() {
		return targetPassword;
	}

	public void setTargetPassword(String targetPassword) {
		this.targetPassword = targetPassword;
	}

	public String getSrcTicketIdField() {
		return srcTicketIdField;
	}

	public void setSrcTicketIdField(String srcTicketIdField) {
		this.srcTicketIdField = srcTicketIdField;
	}

	public String getSrcExternalIdField() {
		return srcExternalIdField;
	}

	public void setSrcExternalIdField(String srcExternalIdField) {
		this.srcExternalIdField = srcExternalIdField;
	}

	public String getTargetTicketIdField() {
		return targetTicketIdField;
	}

	public void setTargetTicketIdField(String targetTicketIdField) {
		this.targetTicketIdField = targetTicketIdField;
	}

	public String getTargetExternalIdField() {
		return targetExternalIdField;
	}

	public void setTargetExternalIdField(String targetExternalIdField) {
		this.targetExternalIdField = targetExternalIdField;
	}

}
