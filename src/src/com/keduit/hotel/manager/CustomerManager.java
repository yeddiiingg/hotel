package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.common.Util;
import com.keduit.hotel.model.Admin;
import com.keduit.hotel.model.Customer;

public class CustomerManager implements ICustomerManageable {

	private static List<Customer> customerList = new ArrayList<>();

	private static boolean isLogin = true;
	private static String currentId;

	@Override
	public void add() {

		boolean isExist = false; // 중복값이 없다

		System.out.print("ID를 입력하세요 : ");
		String id = Util.getScanner().nextLine();
		System.out.print("PASSWORD를 입력하세요 : ");
		String password = Util.getScanner().nextLine();
		System.out.print("이름을 입력하세요 : ");
		String name = Util.getScanner().nextLine();
		System.out.print("핸드폰번호를 입력하세요 : ");
		String phoneNo = Util.getScanner().nextLine();

		if (!customerList.isEmpty()) { // customerList가 비어있지 않다
			for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
				Customer c = (Customer) iterator.next();
				if (c.getId().equals(id)) { // 중복값찾기
					isExist = true;
				}
			}
		}

		if (!isExist) { // 중복값이 없으면 회원추가!
			Customer customer = new Customer(id, password, name, phoneNo);
			System.out.println("회원가입 하시겠습니까? Y/N");
			do {
				System.out.print(">>");
				String yesorno = Util.getScanner().nextLine();

				if (yesorno.toUpperCase().equals("Y")) {
					customerList.add(customer);
					System.out.println("회원가입 완료");
					return; // 메인메뉴로 이동
				} else if (yesorno.toUpperCase().equals("N")) {
					System.out.println("회원가입 취소되었습니다.");
					return;
				} else {
					System.out.println("다시 입력해주세요.");
				}
			} while (true);

		} else {
			System.out.println("이미 존재하는 아이디입니다.");
			return;

		}

	}

	@Override
	public void login() {
		System.out.print("ID를 입력하세요 : ");
		String id = Util.getScanner().nextLine();
		System.out.print("PASSWORD를 입력하세요 : ");
		String password = Util.getScanner().nextLine();

		if (check(id, password) == true) {
			System.out.println("로그인 되었습니다.");
			isLogin = true;
			CustomerManager.currentId = id;
			main();
		} else if (Admin.getId().equals(id) && Admin.getPassword().equals(password)) {
			System.out.println("관리자로 로그인 되었습니다.");
			Util.getRm().main();
		} else {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}

	}

	@Override
	public void delete() {
		System.out.print("ID를 입력하세요 : ");
		String id = Util.getScanner().nextLine();
		System.out.print("PASSWORD를 입력하세요 : ");
		String password = Util.getScanner().nextLine();

		int i = -1;
		if (check(id, password) == true) {
			for (Customer customer : customerList) {
				if (customer.getId().equals(id)) {
					i = customerList.indexOf(customer);
				}
			}
			customerList.remove(customerList.get(i));
			System.out.println("회원 탈퇴 되었습니다.");
		} else {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}
	}

	@Override
	public void showList() {
		System.out.println("-".repeat(30));

		for (Customer customer : customerList) {
			System.out.printf("%s | %s | %s | %s%n", customer.getId(), customer.getPassword(), customer.getName(),
					customer.getPhoneNo());
		}
		System.out.println("-".repeat(30));

	}

	@Override
	public boolean check(String id, String password) {
		boolean isExist = false;
		for (Customer customer : customerList) {
			if (customer.getId().equals(id)) {
				if (customer.getPassword().equals(password)) {
					isExist = true;
				}
			}

		}
		return isExist;
	}

	@Override
	public void main() {

		try {
			int decide;
			do {
				decide = menu(Util.getScanner());
				switch (decide) {
				case 1:
					Util.getRm().showList();
					break;
				case 2:
					Util.getRm().reserve();
					break;
				case 3:
					Util.getRm().cancel();
					break;
				case 4:
					Util.getRm().showReservation();
					break;
				case 5:
					System.out.println("로그아웃 되었습니다.");
					isLogin = false;
					return;
				default:
					break;
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int menu(Scanner sc) {
		int decide;
		System.out.println("1. 호텔 정보 확인");
		System.out.println("2. 객실 예약");
		System.out.println("3. 객실 예약 취소");
		System.out.println("4. 예약 정보 확인");
		System.out.println("5. 로그아웃");
		System.out.print(">> ");
		try {
			decide = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("입력값이 잘못되었습니다.");
			decide = -1;
		}
		return decide;

	}

	public static List<Customer> getCustomerList() {
		return customerList;
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setCustomerList(List<Customer> customerList) {
		CustomerManager.customerList = customerList;
	}

	public static void setLogin(boolean isLogin) {
		CustomerManager.isLogin = isLogin;
	}

	public static String getCurrentID() {
		return currentId;
	}

	public static void setCurrentID(String currentId) {
		CustomerManager.currentId = currentId;
	}

}
