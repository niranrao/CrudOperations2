package com.lumen.ebonding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "um_navigation_master")
public class NavigationMaster implements Serializable {

	/**
	 * @author Devesh Joshi
	 */
	private static final long serialVersionUID = 1023377812175943754L;

	@Id
	@GeneratedValue
	@Column(name = "nav_id")
	private Integer id;

	@Column(name = "nav_url")
	private String navUrl;

	@Column(name = "nav_icon")
	private String imgIcon;

	@Column(name = "nav_title")
	private String title;

	@Column(name = "nav_description")
	private String description;

	@Column(name = "nav_active")
	private boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNavUrl() {
		return navUrl;
	}

	public void setNavUrl(String navUrl) {
		this.navUrl = navUrl;
	}

	public String getImgIcon() {
		return imgIcon;
	}

	public void setImgIcon(String imgIcon) {
		this.imgIcon = imgIcon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
