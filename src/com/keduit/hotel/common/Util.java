package com.keduit.hotel.common;

import java.util.Scanner;

public class Util {

	private static Scanner scanner = new Scanner(System.in);

	public static Scanner getScanner() {
		return scanner;
	}

	public static void setScanner(Scanner scanner) {
		Util.scanner = scanner;
	}
}
