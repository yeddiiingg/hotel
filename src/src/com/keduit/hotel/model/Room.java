package com.keduit.hotel.model;

public class Room {

	private int roomNo;
	private String roomType;
	private boolean isReservable;
	private int roomPrice;

	public Room(int roomNo, String roomType, int roomPrice) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.isReservable = true;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public boolean isReservable() {
		return isReservable;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setReservable(boolean isReservable) {
		this.isReservable = isReservable;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

}
