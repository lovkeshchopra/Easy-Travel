package com.easytravel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class BusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "Bus_number")
	private String busNumber;

	@Column(name = "driver_name")
	private String driverName;

	@ManyToOne
	@JoinColumn(name = "a_id")
	private AreaInfoEntity areaInfoEntity;

	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "seats")
	private Integer seats;

	@Column(name = "available_seats")
	private Integer availableSeats;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "driver_mobile")
	private String driverMobileNumber;

	@Column(name = "bus_cat")
	private String busCategory;

	@Column(name = "morning_Time")
	private String morningTime;

	@Column(name = "evening_Time")
	private String eveningTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public AreaInfoEntity getAreaInfoEntity() {
		return areaInfoEntity;
	}

	public void setAreaInfoEntity(AreaInfoEntity areaInfoEntity) {
		this.areaInfoEntity = areaInfoEntity;
	}

}
