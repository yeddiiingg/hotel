package com.keduit.hotel.model;

public class Admin {

	private static final String ID = "admin";
	private static final String PASSWORD = "1q2w3e4r!";
	private static boolean isLogin = false;

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public static String getId() {
		return ID;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		Admin.isLogin = isLogin;
	}

}
