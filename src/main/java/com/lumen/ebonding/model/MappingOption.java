package com.lumen.ebonding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entity class for managing mapping options
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */

@Entity
@Table(name = "conf_mapping_option")
public class MappingOption implements Serializable {

	private static final long serialVersionUID = -2973881077584815840L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pk_optionId")
	private Integer id;

	@Column(name = "source_field")
	private String source;

	@Column(name = "target_field")
	private String target;

	@Column(name = "option_taxt")
	private String text;

	@Column(name = "option_length")
	private String length;

	@OneToOne(targetEntity = StaticData.class)
	@JoinColumn(name = "fk_placementId", referencedColumnName = "pk_dataId")
	private StaticData placementOption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public StaticData getPlacementOption() {
		return placementOption;
	}

	public void setPlacementOption(StaticData placementOption) {
		this.placementOption = placementOption;
	}

}
