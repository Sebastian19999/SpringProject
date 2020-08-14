package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_sms")
public class SmsContact {
	
	@Id
	@GeneratedValue
	private Integer id_sms;

	@Column(name="appellant_number_sms")
	private String appellant_number_sms;
	@Column(name="receiver_number_sms")
	private String receiver_number_sms;
	@Column(name="sms_number")
	private int sms_number=0;
	
	
	private boolean sameNetwork=false;
	
	public SmsContact() {
		
	}

	public SmsContact(String appellant_number_sms) {
		this.appellant_number_sms = appellant_number_sms;
	}

	public Integer getId_sms() {
		return id_sms;
	}

	public void setId_sms(Integer id_sms) {
		this.id_sms = id_sms;
	}

	public String getAppellant_number_sms() {
		return appellant_number_sms;
	}

	public void setAppellant_number_sms(String appellant_number_sms) {
		this.appellant_number_sms = appellant_number_sms;
	}

	public String getReceiver_number_sms() {
		return receiver_number_sms;
	}

	public void setReceiver_number_sms(String receiver_number_sms) {
		this.receiver_number_sms = receiver_number_sms;
	}

	public int getSms_number() {
		return sms_number;
	}

	public void setSms_number(int sms_number) {
		this.sms_number = sms_number;
	}

	public boolean isSameNetwork() {
		return sameNetwork;
	}

	public void setSameNetwork(boolean sameNetwork) {
		this.sameNetwork = sameNetwork;
	}

	@Override
	public String toString() {
		return "SmsContact [id_sms=" + id_sms + ", appellant_number_sms=" + appellant_number_sms
				+ ", receiver_number_sms=" + receiver_number_sms + ", sms_number=" + sms_number + ", sameNetwork="
				+ sameNetwork + "]";
	}
	
	
	
	

}
