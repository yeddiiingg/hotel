package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.model.Room;

public interface IRoomManageable {

	List<Room> roomList = new ArrayList<>();
	long earnMoney = 0L;

	void add();

	void delete();

	void showList();

	boolean checkIsExist(int roomNo);

	boolean checkStatus(int roomNo);

	void reserve();

	void cancel();

	void showReservation();

	long showEarnMoney();

	void main();

	int menu(Scanner sc);

}
