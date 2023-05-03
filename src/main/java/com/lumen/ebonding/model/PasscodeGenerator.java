package com.lumen.ebonding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "passcode_generation")
public class PasscodeGenerator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "user_id")
	private int id;
	@Column(name = "cu_id")
	private String cuId;
	@Column(name = "passcode")
	private String passcode;
	@Column(name = "generated_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date generatedTime;
	
	public PasscodeGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasscodeGenerator(String cuId, String passcode, Date generatedTime) {
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
