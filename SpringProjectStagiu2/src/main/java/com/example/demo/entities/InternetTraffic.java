package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_internet_traffic")
public class InternetTraffic {
	
	@Id
	@GeneratedValue
	private Integer id_traffic;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="start_time")
	private double start_time=0;
	
	@Column(name="end_time")
	private double end_time=0;

	public InternetTraffic() {
		
	}
	
	public InternetTraffic(String phone_number) {
		super();
		this.phone_number = phone_number;
	}

	public Integer getId_traffic() {
		return id_traffic;
	}

	public void setId_traffic(Integer id_traffic) {
		this.id_traffic = id_traffic;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public double getStart_time() {
		return start_time;
	}

	public void setStart_time(double start_time) {
		this.start_time = start_time;
	}

	public double getEnd_time() {
		return end_time;
	}

	public void setEnd_time(double end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "InternetTraffic [id_traffic=" + id_traffic + ", phone_number=" + phone_number + ", start_time="
				+ start_time + ", end_time=" + end_time + "]";
	}

	
	
	
	

}
