package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.model.Room;

public class RoomManager implements IRoomManageable {

	private static List<Room> roomList = new ArrayList<>();
	private static long earnMoney = 0L;

	public RoomManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showList() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIsExist(int roomNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkStatus(int roomNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reserve() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showReservation() {
		// TODO Auto-generated method stub

	}

	@Override
	public long showEarnMoney() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void main() {
		// TODO Auto-generated method stub

	}

	@Override
	public int menu(Scanner sc) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static List<Room> getRoomList() {
		return roomList;
	}

	public static long getEarnMoney() {
		return earnMoney;
	}

	public static void setRoomList(List<Room> roomList) {
		RoomManager.roomList = roomList;
	}

	public static void setEarnMoney(long earnMoney) {
		RoomManager.earnMoney = earnMoney;
	}

}
