package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import admin.Admin;
import admin.AdminImpl;
import login.Login;
import login.LoginImpl;
import reserv.Reserv;
import reserv.ReservImpl;
import user.User;
import user.UserImpl;
import util.FileUtil;
import vo.AirInfoVO;
import vo.ReservVO;
import vo.UserVO;

public class MainUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private int pageCnt = 0;
	private UserVO user = null;

	private List<UserVO> list_user = null;
	private List<AirInfoVO> list_info = null;
	private List<ReservVO> list_reserv = null;

//	������
	public MainUI() {

		list_user = fu.getUser();
		list_info = fu.getInfo();
		list_reserv = fu.getReserv();

		if (list_user.size() == 0) {
			UserVO admin = new UserVO();

			admin.setId("user");
			admin.setPw("1111");
			admin.setName("������");
			admin.setBirth("1900-01-01");

			list_user.add(admin);
		}
	}

//	Util ����
	FileUtil fu = new FileUtil();

//	���� �ҷ�����

	public void menu() {
		aa: while (true) {
			if (user == null) {
//				�α���ȭ�� UI
				loginPage();
				continue aa;
			} else if (user.getId().equals("user")) {
				pageCnt = 3;
			}

			switch (pageCnt) {
			case 1:
//				ȸ�� ������
				UserPage();
				break;
			case 2:
//				���� ������
				reservPage();
				break;
			case 3:
//				���� ������
				adminPage();
				break;
			}
		}
	}

//	�α��� ȭ��
	private void loginPage() {
		Login lg = new LoginImpl();
		try {
			char select;

			do {
				System.out.print("[1]�α���   [2]ȸ������   [3]���� => ");
				select = br.readLine().charAt(0);
			} while (select < '1' || select > '3');

			if (select == '3') {
				fu.save_UserF();
				fu.save_AirInfoF();
				fu.save_ReservF();

				System.exit(0);
			}

			switch (select) {
			case '1':
//				�α��� �ż��� �̵�
				user = lg.UserLogin(list_user);

				if (user != null) {
					pageCnt = 1;
				}

				break;
			case '2':
//				ȸ������ �ż��� �̵�
				lg.addId(list_user);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	�α��� ���� ȭ��
	private void UserPage() {
		User us = new UserImpl();
		try {
			char select;

			do {
				System.out.print("[1]����   [2]��ȸ   [3]ȸ����������  [4]ȸ��Ż��  [5]�α׾ƿ� => ");
				select = br.readLine().charAt(0);
			} while (select < '1' || select > '5');

			if (select == '1') {
				pageCnt = 2;
				return;
			}

			if (select == '5') {
				user = null;
				return;
			}

			switch (select) {
			case '2':
//				��ȸ �ż���
				us.reservSearch(user, list_user, list_reserv);
				break;
			case '3':
//				ȸ���������� �ż���
				UserVO vo = us.updateUser(user, list_user);
				if (vo != null) {
					user = null;
					user = vo;
				}
				break;
			case '4':
//				ȸ��Ż�� �ż���
				boolean check = us.deleteUser(user, list_user, list_reserv);

				if (check) {
					user = null;
					pageCnt = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	���� ȭ��
	private void reservPage() {
		Reserv re = new ReservImpl();
		try {
			char select;

			do {
				System.out.print("[1]����   [2]���   [3]�ڷΰ��� => ");
				select = br.readLine().charAt(0);
			} while (select < '1' || select > '3');

			if (select == '3') {
				pageCnt = 1;
				
				return;
			}

			switch (select) {
			case '1':
				re.reserv(list_reserv, list_info, user.getId());
				break;
//				���� �ż���
			case '2':
				re.cancleReserv(list_reserv, list_info, user.getId());
				break;
//				���� ��� �ż���
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	���� ȭ��
	private void adminPage() {
		Admin ad = new AdminImpl();
		try {
			char select;

			do {
				System.out.println("[1]�װ�����  [2]�װ����߰�  [3]�װ������");
				System.out.print("[4]ȸ������     [5]����  => ");
				select = br.readLine().charAt(0);
			} while (select < '1' || select > '5');

			if (select == '5') {
				user = null;
				pageCnt = 0;
				fu.save_UserF();
				fu.save_AirInfoF();
				fu.save_ReservF();
				return;
			}

			switch (select) {
			case '1':
//				�װ��� ��� ��ȸ �ż���
				ad.airList(list_info);
				break;
			case '2':
//				�װ��� �߰� �ż���
				ad.addAirList(list_info);
				break;
			case '3':
//				�װ��� ���� �ż���
				ad.deleteAirList(list_info, list_reserv);
				break;
			case '4':
//				ȸ�� ���� �ż���
				ad.userControl(list_user);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
