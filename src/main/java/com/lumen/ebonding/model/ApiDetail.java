package com.lumen.ebonding.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class to manage Api payload and details
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
@Entity
@Table(name = "conf_api_details")
public class ApiDetail implements Serializable {

	private static final long serialVersionUID = 3217669499338262894L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pk_api_id", unique = true, nullable = false)
	private Integer id;

	@OneToOne(targetEntity = CustomerConfiguration.class)
	@JoinColumn(name = "fk_configId", referencedColumnName = "pk_configId")
	private CustomerConfiguration config;
	
	@Column(name = "api_active")
	private boolean active; 

	@Column(name = "api_url")
	private String apiUrl;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "source_payload")
	private byte[] sourcePayload;

	@Column(name = "source_filename", length = 45)
	private String sourceFileName;

	@Column(name = "source_filetype", length = 15)
	private String sourceFileType;

	@Column(name = "target_url")
	private String targetUrl;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "target_payload")
	private byte[] targetPayload;

	@Column(name = "target_filename")
	private String targetFileName;

	@Column(name = "target_filetype")
	private String targetFileType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerConfiguration getConfig() {
		return config;
	}

	public void setConfig(CustomerConfiguration config) {
		this.config = config;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public byte[] getSourcePayload() {
		return sourcePayload;
	}

	public void setSourcePayload(byte[] sourcePayload) {
		this.sourcePayload = sourcePayload;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getSourceFileType() {
		return sourceFileType;
	}

	public void setSourceFileType(String sourceFileType) {
		this.sourceFileType = sourceFileType;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public byte[] getTargetPayload() {
		return targetPayload;
	}

	public void setTargetPayload(byte[] targetPayload) {
		this.targetPayload = targetPayload;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public String getTargetFileType() {
		return targetFileType;
	}

	public void setTargetFileType(String targetFileType) {
		this.targetFileType = targetFileType;
	}
	
}
