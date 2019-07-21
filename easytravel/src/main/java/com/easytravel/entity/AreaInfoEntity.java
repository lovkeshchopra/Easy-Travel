package com.easytravel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "area_info")
public class AreaInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private Long id;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "area")
	private String area;

	@Column(name = "pincode")
	private String pincode;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = BusEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "a_id", referencedColumnName = "a_id")
	private List<BusEntity> customerBooking;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public List<BusEntity> getCustomerBooking() {
		return customerBooking;
	}

	public void setCustomerBooking(List<BusEntity> customerBooking) {
		this.customerBooking = customerBooking;
	}

}
