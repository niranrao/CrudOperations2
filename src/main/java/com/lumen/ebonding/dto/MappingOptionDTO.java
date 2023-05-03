package com.lumen.ebonding.dto;

/**
 * Dto class to manage mapping options for {@link MappingDetailDTO}
 * 
 * @author Devesh Joshi
 * @since Oct 16, 2020
 *
 */

public class MappingOptionDTO {

	private Integer id;
	private String source;
	private String target;
	private String text;
	private String length;
	private StaticDataDTO placementOption;

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

	public StaticDataDTO getPlacementOption() {
		return placementOption;
	}

	public void setPlacementOption(StaticDataDTO placementOption) {
		this.placementOption = placementOption;
	}

}
