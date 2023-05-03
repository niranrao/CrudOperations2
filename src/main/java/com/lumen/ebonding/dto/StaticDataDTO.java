package com.lumen.ebonding.dto;

/**
 * DTO class to maintain the static data for the application
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
public class StaticDataDTO {

	private Integer id;
	private String name;
	private String value;
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