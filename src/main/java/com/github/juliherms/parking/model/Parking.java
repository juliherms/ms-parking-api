package com.github.juliherms.parking.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TB_PARKING")
public class Parking {

	@Id
	private String id;

	@Column(nullable = false)
	private String license;
	
	@Column(nullable = false)
	private String state;
	
	private String model;
	private String color;
	
	private LocalDateTime entryDate;
	private LocalDateTime exitDate;
	private Double bill;

	public Parking() {
	}

	public Parking(String id, String license, String state, String model, String color) {
		super();
		this.id = id;
		this.license = license;
		this.state = state;
		this.model = model;
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public LocalDateTime getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDateTime exitDate) {
		this.exitDate = exitDate;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}

}
