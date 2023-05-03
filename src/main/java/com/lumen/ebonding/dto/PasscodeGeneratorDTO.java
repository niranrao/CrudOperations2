package com.lumen.ebonding.dto;

import java.io.Serializable;
import java.util.Date;

public class PasscodeGeneratorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String cuId;
	private String passcode;
	private Date generatedTime;
	public PasscodeGeneratorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PasscodeGeneratorDTO( String cuId, String passcode, Date generatedTime) {
		super();
		this.cuId = cuId;
		this.passcode = passcode;
		this.generatedTime = generatedTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public Date getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}
	
}
