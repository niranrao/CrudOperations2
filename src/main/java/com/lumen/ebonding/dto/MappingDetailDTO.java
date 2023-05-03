package com.lumen.ebonding.dto;

import java.util.List;

/**
 * Dto class to manage mapping details
 * 
 * @author Devesh Joshi
 * @since Oct 16, 2020
 *
 */
public class MappingDetailDTO {

	private Integer id;
	private CustomerConfigurationDTO config;
	private Boolean active;
	private String sourceField;
	private String targetField;
	private Boolean mandatory;
	private StaticDataDTO mappingType;
	private String defaultValue;
	private List<MappingOptionDTO> mappingOptions;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public Boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public StaticDataDTO getMappingType() {
		return mappingType;
	}

	public void setMappingType(StaticDataDTO mappingType) {
		this.mappingType = mappingType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<MappingOptionDTO> getMappingOptions() {
		return mappingOptions;
	}

	public void setMappingOptions(List<MappingOptionDTO> mappingOptions) {
		this.mappingOptions = mappingOptions;
	}

}
