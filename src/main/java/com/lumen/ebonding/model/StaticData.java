package com.lumen.ebonding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entity class to maintain the static data for the application
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
@Entity
@Table(name = "conf_static_data")
public class StaticData implements Serializable {

	private static final long serialVersionUID = 3342474348865330423L;

	@Id
	@Column(name = "pk_dataId")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@Column(name = "data_type")
	private String name;

	@Column(name = "data_value")
	private String value;

	@Column(name = "data_status")
	private boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
