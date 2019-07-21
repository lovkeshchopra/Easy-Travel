package com.easytravel.responseDTO;

import com.easytravel.entity.BusEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BusDTO {

	private String busNumber;

	private String driverName;

	private String aadharNumber;

	private Integer seats;

	private Integer availableSeats;

	private String source;

	private String destination;

	private Integer amount;

	private String driverMobileNumber;

	private String busCategory;

	private String morningTime;

	private String eveningTime;

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}

	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}

	public String getBusCategory() {
		return busCategory;
	}

	public void setBusCategory(String busCategory) {
		this.busCategory = busCategory;
	}

	public String getMorningTime() {
		return morningTime;
	}

	public void setMorningTime(String morningTime) {
		this.morningTime = morningTime;
	}

	public String getEveningTime() {
		return eveningTime;
	}

	public void setEveningTime(String eveningTime) {
		this.eveningTime = eveningTime;
	}

	public void setDataFromEntity(BusEntity profile) {
		this.setBusNumber(profile.getBusNumber());
		this.setAvailableSeats(profile.getAvailableSeats());
		this.setSource(profile.getSource());
		this.setDestination(profile.getDestination());
		this.setAmount(profile.getAmount());
		this.setDriverMobileNumber(profile.getDriverMobileNumber());
		this.setBusCategory(profile.getBusCategory());
		this.setMorningTime(profile.getMorningTime());
		this.setEveningTime(profile.getEveningTime());

	}

}
