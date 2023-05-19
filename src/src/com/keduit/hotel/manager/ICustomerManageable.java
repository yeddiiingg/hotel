package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.model.Customer;

public interface ICustomerManageable {

	List<Customer> customerList = new ArrayList<>();

	void add();

	void login();

	void delete();

	void showList();

	boolean check(String id, String password);

	void main();

	int menu(Scanner sc);

}
