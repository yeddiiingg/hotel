package com.keduit.hotel.common;

import java.util.Scanner;

import com.keduit.hotel.manager.CustomerManager;
import com.keduit.hotel.manager.RoomManager;

public class Util {

	private static Scanner scanner = new Scanner(System.in);
	private static CustomerManager cm = new CustomerManager();
	private static RoomManager rm = new RoomManager();

	public static Scanner getScanner() {
		return scanner;
	}

	public static CustomerManager getCm() {
		return cm;
	}

	public static RoomManager getRm() {
		return rm;
	}

	public static void setScanner(Scanner scanner) {
		Util.scanner = scanner;
	}

	public static void setCm(CustomerManager cm) {
		Util.cm = cm;
	}

	public static void setRm(RoomManager rm) {
		Util.rm = rm;
	}

}
