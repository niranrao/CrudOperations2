package com.lumen.ebonding.dto;

import java.io.Serializable;
import java.util.Date;

public class UsersDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String cuId;
	private String emailId;
	private boolean superUser;
	private boolean status;
	private String modifiedBy;
	private Date modifiedDate;
	
	public UsersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", cuId=" + cuId + ", emailId=" + emailId + ", superUser=" + superUser
				+ ", status=" + status +  ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
	

}
