package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_contact")
public class Contact {

	@Id
	@GeneratedValue
	private Integer id_contact;

	@Column(name="appellant_number")
	private String appellant_number;
	@Column(name="receiver_number")
	private String receiver_number;
	@Column(name="start_time")
	private double start_time=0;
	@Column(name="end_time")
	private double end_time=0;
	
	private boolean sameNetwork=false;

	public Contact() {
		
	}
	
	public Contact(String appellant_number, String receiver_number) {
		this.appellant_number = appellant_number;
		this.receiver_number = receiver_number;
	}
	
	
	public Integer getId_contact() {
		return id_contact;
	}

	public void setId_contact(Integer id_contact) {
		this.id_contact = id_contact;
	}


	public String getAppellant_number() {
		return appellant_number;
	}

	public void setAppellant_number(String appellant_number) {
		this.appellant_number = appellant_number;
	}

	public String getReceiver_number() {
		return receiver_number;
	}

	public void setReceiver_number(String receiver_number) {
		this.receiver_number = receiver_number;
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

	public boolean isSameNetwork() {
		return sameNetwork;
	}

	public void setSameNetwork(boolean sameNetwork) {
		this.sameNetwork = sameNetwork;
	}
	
	
	
}
