package com.keduit.hotel.view;

import java.util.Scanner;

import com.keduit.hotel.common.Util;
import com.keduit.hotel.manager.CustomerManager;

public class Main {

	public static void main(String[] args) {

		CustomerManager cm = new CustomerManager();

		System.out.println("----------------------------------");
		System.out.println("신라스테이에 오신 것을 환영합니다.");
		System.out.println("  \""+"Welcome to Silla Stay Hotel"+"\"");
		System.out.println("----------------------------------");

		try {
			int decide;
			do {
				decide = menu(Util.getScanner());
				switch (decide) {
				case 1:
					// 로그인
					cm.login();
					break;
				case 2:
					// 회원 가입
					cm.add();
					break;
				case 3:
					// 회원 탈퇴
					cm.delete();
					break;
				case 4:
					return;
				default:
					break;
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int menu(Scanner sc) {
		int decide;
		// 로그아웃 상태
		System.out.println("1. 로그인");
		System.out.println("2. 회원 가입");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 종료");
		System.out.print(">> ");
		try {
			decide = Integer.parseInt(Util.getScanner().nextLine());
		} catch (NumberFormatException e) {
			System.out.println("입력값이 잘못되었습니다.");
			decide = -1;
		}
		return decide;
	}

}
