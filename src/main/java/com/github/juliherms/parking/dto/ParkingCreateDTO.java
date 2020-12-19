package com.github.juliherms.parking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class represents DTO to Create Parking
 * @author jlv
 *
 */
public class ParkingCreateDTO {

	@NotEmpty(message = "The Parking license cannot be empty")
	@NotNull(message = "The Parking license cannot be null")
	private String license;
	
	private String state;
	private String model;
	private String color;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
