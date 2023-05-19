package com.keduit.hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private static int serialID = 0;

	private List<Room> reservationList = new ArrayList<>();

	private String id;
	private String password;
	private String name;
	private String phoneNo;

	public Customer(String id, String password, String name, String phoneNo) {
		Customer.serialID++;
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNo = phoneNo;
	}

	public static int getSerialID() {
		return serialID;
	}

	public List<Room> getReservationList() {
		return reservationList;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public static void setSerialID(int serialID) {
		Customer.serialID = serialID;
	}

	public void setReservationList(List<Room> reservationList) {
		this.reservationList = reservationList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
