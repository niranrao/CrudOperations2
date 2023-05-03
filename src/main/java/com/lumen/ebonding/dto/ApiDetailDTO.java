package com.lumen.ebonding.dto;

public class ApiDetailDTO {

	private Integer id;
	private CustomerConfigurationDTO config;
	private boolean active;
	private String apiUrl;
	private byte[] sourcePayload;
	private String sourceFileName;
	private String sourceFileType;
	private String targetUrl;
	private byte[] targetPayload;
	private String targetFileName;
	private String targetFileType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerConfigurationDTO getConfig() {
		return config;
	}

	public void setConfig(CustomerConfigurationDTO config) {
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
