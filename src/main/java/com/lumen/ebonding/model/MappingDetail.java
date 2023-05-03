package com.lumen.ebonding.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class to maintain the configuration mappings
 * 
 * @author Devesh Joshi
 * @since Oct 15, 2020
 *
 */
@Entity
@Table(name = "conf_mapping_details")
public class MappingDetail implements Serializable {

	private static final long serialVersionUID = -3754911736674133843L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pk_mappingId")
	private Integer id;

	@OneToOne(targetEntity = CustomerConfiguration.class)
	@JoinColumn(name = "fk_configId", referencedColumnName = "pk_configId")
	private CustomerConfiguration config;
	
	@Column(name= "mapping_status")
	private Boolean active;
	
	@Column(name = "source_field", length = 45)
	private String sourceField;
	
	@Column(name = "target_field", length = 45)
	private String targetField;
	
	@Column(name = "mandatory")
	private boolean mandatory;
	
	@OneToOne(targetEntity = StaticData.class)
	@JoinColumn(name = "fk_mapingTypeId", referencedColumnName = "pk_dataId")
	private StaticData mappingType;
	
	@Column(name = "default_value", length = 45)
	private String defaultValue;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_mappingId")
	private List<MappingOption> mappingOptions;

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

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public StaticData getMappingType() {
		return mappingType;
	}

	public void setMappingType(StaticData mappingType) {
		this.mappingType = mappingType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<MappingOption> getMappingOptions() {
		return mappingOptions;
	}

	public void setMappingOptions(List<MappingOption> mappingOptions) {
		this.mappingOptions = mappingOptions;
	}
	
}
