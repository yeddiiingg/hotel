package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.model.Customer;

public class CustomerManager implements ICustomerManageable {

	private static List<Customer> customerList = new ArrayList<>();

	private static boolean isLogin = true;

	@Override
	public void add() {
		// TODO Auto-generated method stub

		// id, pw, name, phoneNo 입력받음
		// check()

		// check()가 true면 Customer의 생성자를 호출해서
		// 입력받은 id, pw, name, phoneNo를 넣어 인스턴스를 만들고
		// 그 인스턴스를 리스트에 넣기
		// 회원 가입 완료 메시지 출력 후 메인 메뉴로 이동(=return;)

	}

	@Override
	public void login() {
		// TODO Auto-generated method stub

		// id, pw 입력받음
		// check()
		// CustomerManager의 main을 실행 ->
		main();

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

		// id, pw 입력받음
		// check()
	}

	@Override
	public void showList() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(String id, String password) {
		// TODO Auto-generated method stub

		// id를 가지고 회원 리스트를 반복 돌려서
		// id와 같은 id값을 가진 회원(덩어리)이 있으면
		// 덩어리의 pw값과 입력받은 pw를 비교
		// 둘이 같으면 true

		return false;
	}

	@Override
	public void main() {
		// TODO Auto-generated method stub

		// menu()
	}

	@Override
	public int menu(Scanner sc) {
		// TODO Auto-generated method stub

		// 1. 호텔 정보 확인
		// 2. 객실 예약
		// 3. 객실 예약 취소
		// 4. 예약 정보 확인
		// 5. 로그아웃
		return 0;
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

}
