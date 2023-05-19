package com.keduit.hotel.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.keduit.hotel.common.Util;
import com.keduit.hotel.model.Admin;
import com.keduit.hotel.model.Customer;
import com.keduit.hotel.model.Room;

public class RoomManager implements IRoomManageable {

	private static List<Room> roomList = new ArrayList<>();
	private static long earnMoney = 0L;

	public RoomManager() {
		roomList.add(new Room(105, "일반룸", 80000));
		roomList.add(new Room(104, "스위트룸", 130000));
		roomList.add(new Room(102, "스위트룸", 130000));
		roomList.add(new Room(101, "일반룸", 80000));
		roomList.add(new Room(103, "일반룸", 80000));
	}

	@Override
	public void add() {
		// 객실 추가
		showList();
		try {
			System.out.print("방 호수를 숫자로 입력하세요 : ");
			int roomNo = Integer.parseInt(Util.getScanner().nextLine());
			System.out.print("방의 종류를 입력하세요 : ");
			String roomType = Util.getScanner().nextLine();
			System.out.print("방의 가격을 입력하세요 : ");
			int roomPrice = Integer.parseInt(Util.getScanner().nextLine());
			if (checkIsExist(roomNo)) {
				System.out.println("\n이미 존재하는 방입니다.\n");
			} else {
				roomList.add(new Room(roomNo, roomType, roomPrice));
				System.out.println("\n방이 추가되었습니다.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력값입니다.\n");
		} catch (Exception e) {
			System.out.println("알 수 없는 오류가 발생했습니다.");
			e.printStackTrace();
		}

	}

	@Override
	public void delete() {
		// 객실 제거
		showList();
		try {
			System.out.print("삭제할 객실의 호수를 숫자로 입력하세요 : ");
			int roomNo = Integer.parseInt(Util.getScanner().nextLine());
			if (checkStatus(roomNo)) {
				System.out.print(roomNo + "호를 객실 목록에서 삭제하시겠습니까? (Y/N) : ");
				String decide = Util.getScanner().nextLine();
				if (decide.toUpperCase().equals("Y")) {
					int idx = -1;
					for (Room room : roomList) {
						if (room.getRoomNo() == roomNo) {
							idx = roomList.indexOf(room);
						}
					}
					roomList.remove(idx);
					System.out.println("해당 객실이 삭제되었습니다.\n");
				} else {
					System.out.println("취소합니다.\n");
				}
			} else {
				System.out.println("해당 객실이 이미 예약되어 있거나, 객실을 찾을 수 없습니다.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력값입니다.\n");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("객실 목록을 처리하는 중 문제가 발생했습니다.\n");
		} catch (Exception e) {
			System.out.println("알 수 없는 오류가 발생했습니다.\n");
		}
	}

	@Override
	public void showList() {
		// 호텔 정보 확인
		System.out.println("-".repeat(35));
		System.out.println("          전체 객실 목록");
		System.out.println("-".repeat(35));
		String reservable;
		Collections.sort(roomList, (n, m) -> n.getRoomNo() - m.getRoomNo());
		for (Room room : roomList) {
			reservable = room.isReservable() ? "예약 가능" : "예약 불가";
			System.out.println(room.getRoomNo() + "호, " + room.getRoomType() + ", " + reservable + ", "
					+ room.getRoomPrice() + "원");
		}
		System.out.println();
	}

	@Override
	public boolean checkIsExist(int roomNo) {
		// 방 번호가 존재하는지 체크
		boolean isExist = false;
		for (Room room : roomList) {
			if (room.getRoomNo() == roomNo) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public boolean checkStatus(int roomNo) {
		// 해당 방이 예약 가능한지 체크 (비어있으면 true, 찼으면 false)
		for (Room room : roomList) {
			if (room.getRoomNo() == roomNo) {
				return room.isReservable();
			}
		}
		return false;
	}

	@Override
	public void reserve() {
		// 객실 예약..............3중 조건문.....ㅋ
		// 메서드로 빼면...좋았을텐데... notime....ㅠㅠ
		showList();
		try {
			System.out.print("예약할 객실의 호수를 숫자로 입력하세요 : ");
			int roomNo = Integer.parseInt(Util.getScanner().nextLine());
			if (checkIsExist(roomNo)) { // 객실이 있으면
				if (checkStatus(roomNo)) { // 객실이 예약 가능하면
					System.out.println(roomNo + "호에 예약이 가능합니다.");
					System.out.print("예약하시겠습니까? (Y/N) : ");
					String decide = Util.getScanner().nextLine();
					if (decide.toUpperCase().equals("Y")) { // 사용자가 Y를 입력했으면
						int roomIdx = -1;
						Room r;
						// roomIdx를 해당 방의 인덱스로 지정
						for (Room room : roomList) {
							if (room.getRoomNo() == roomNo) {
								roomIdx = roomList.indexOf(room);
							}
						}
						// 인덱스가 roomIdx인 방의 예약 가능 여부를 변경
						// 해당 방의 가격만큼 수익을 증가
						roomList.get(roomIdx).setReservable(false);
						earnMoney += roomList.get(roomIdx).getRoomPrice();
						// 현재 회원 객체 가져오기
						int custIdx = -1;
						for (Customer customer : CustomerManager.getCustomerList()) {
							if (customer.getId().equals(CustomerManager.getCurrentID())) {
								custIdx = CustomerManager.getCustomerList().indexOf(customer);
							}
						}
						// 회원 객체의 예약 목록에 객실을 추가
						r = roomList.get(roomIdx);
						// customerlist에서 현재 로그인된 customer의 인덱스를 가지고 현재 회원의 객체를 가져와서
						CustomerManager.getCustomerList().get(custIdx).getReservationList().add(r);
						System.out.println("객실이 성공적으로 예약되었습니다.\n");
					} else {
						System.out.println("취소합니다.\n");
					}
				} else {
					System.out.println("\n해당 객실은 이미 예약되어 있거나, 관리자가 예약할 수 없도록 설정한 객실입니다.\n");
				}
			} else {
				System.out.println("\n존재하지 않는 객실입니다.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력값입니다.\n");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("객실 예약을 처리하는 중 문제가 발생했습니다.\n");
		} catch (Exception e) {
			System.out.println("알 수 없는 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	@Override
	public void cancel() {
		// 객실 예약 취소
		showReservation();
		try {
			int custIdx = -1;
			List<Customer> custList = CustomerManager.getCustomerList();
			for (Customer customer : custList) {
				if (customer.getId().equals(CustomerManager.getCurrentID())) {
					custIdx = custList.indexOf(customer);
				}
			}
			// 회원이 예약된 객실이 있을때에만 취소할 객실의 호수 입력을 받음
			if (custIdx != -1) {
				List<Room> custReservList = custList.get(custIdx).getReservationList();
				if (!(custReservList.isEmpty())) {
					System.out.print("예약을 취소할 객실의 호수를 숫자로 입력해주세요 : ");
					int roomNo = Integer.parseInt(Util.getScanner().nextLine());
					// 입력한 호수에 해당하는 예약된 객실이 있는지 확인
					int rsvListIdx = -1;
					for (Room room : custReservList) {
						if (room.getRoomNo() == roomNo) {
							rsvListIdx = custReservList.indexOf(room);
						}
					}
					if (rsvListIdx != -1) {
						// 입력된 호수의 예약된 객실 예약 취소
						System.out.print(roomNo + "호의 예약을 취소하시겠습니까? (Y/N) : ");
						String decide = Util.getScanner().nextLine();
						if (decide.toUpperCase().equals("Y")) {
							custReservList.remove(rsvListIdx);
							System.out.println("예약이 취소되었습니다.");
							// 예약 가능으로 변경
							int idx = -1;
							for (Room room : roomList) {
								if (room.getRoomNo() == roomNo) {
									idx = roomList.indexOf(room);
								}
							}
							if (idx != -1) {
								roomList.get(idx).setReservable(true);
								earnMoney -= roomList.get(idx).getRoomPrice();
							} else {
								System.out.println("객실 리스트에서 객실의 예약 상태를 변경하는 중 문제가 발생했습니다.\n");
							}
							System.out.println();
						} else {
							System.out.println("예약을 취소하지 않고 메뉴로 돌아갑니다.\n");
						}
					} else {
						System.out.println("입력한 객실 번호에 해당하는 객실이 없습니다.\n");
					}
				}

			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("객실 예약 취소를 처리하는 중 문제가 발생했습니다.");
			e.printStackTrace();
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력값입니다.");
			e.printStackTrace();
			System.out.println();
		} catch (Exception e) {
			System.out.println("알 수 없는 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	@Override
	public void showReservation() {
		// 예약 정보 확인
		System.out.println("-".repeat(20));
		System.out.println("  예약한 객실 목록");
		System.out.println("-".repeat(20));
		// 회원 리스트에서 현재 로그인된 회원의 객체를 가져와 해당 객체의 인덱스를 저장
		int custIdx = -1;
		List<Customer> custList = CustomerManager.getCustomerList();
		for (Customer customer : custList) {
			if (customer.getId().equals(CustomerManager.getCurrentID())) {
				custIdx = custList.indexOf(customer);
			}
		}
		// 회원이 예약한 객실 목록 출력
		List<Room> custReservList = custList.get(custIdx).getReservationList();
		Collections.sort(custReservList, (n, m) -> n.getRoomNo() - m.getRoomNo());
		if (!(custReservList.isEmpty())) {
			for (Room room : custReservList) {
				System.out.println(room.getRoomNo() + "호, " + room.getRoomType() + ", " + room.getRoomPrice() + "원");
			}
			System.out.println();
		} else {
			System.out.println("예약한 객실이 없습니다.\n");
		}
	}

	@Override
	public long showEarnMoney() {
		// 매출 확인
		return RoomManager.earnMoney;
	}

	@Override
	public void main() {
		// 서브메뉴 출력
		System.out.println("-".repeat(20));
		System.out.println("    관리자 메뉴");
		System.out.println("-".repeat(20));
		try {
			int decide;
			do {
				decide = menu(Util.getScanner());
				switch (decide) {
				case 1: // 호텔 정보 확인
					showList();
					break;
				case 2: // 객실 추가
					add();
					break;
				case 3: // 객실 제거
					delete();
					break;
				case 4: // 매출 확인
					System.out.println("회사의 총 매출은 " + showEarnMoney() + "원입니다.");
					System.out.println();
					break;
				case 5:
					Util.getCm().showList();
					break;
				case 6: // 로그아웃
					Admin.setLogin(false);
					System.out.println("로그아웃 되었습니다.\n");
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
		// 관리자 서브 메뉴 - 호텔 정보 확인, 객실 추가, 객실 제거, 매출 확인, 로그아웃
		int decide;
		System.out.println("1. 호텔 정보 확인");
		System.out.println("2. 객실 추가");
		System.out.println("3. 객실 제거");
		System.out.println("4. 매출 확인");
		System.out.println("5. 고객 리스트");
		System.out.println("6. 로그아웃");
		System.out.print(">> ");
		try {
			decide = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("입력값이 잘못되었습니다. 다시 입력해주세요.");
			decide = -1;
		}
		return decide;
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
