package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import vo.ReservVO;
import vo.UserVO;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public boolean reservSearch(UserVO user, List<UserVO> list_user, List<ReservVO> list_reserv) {
		List<ReservVO> result = new LinkedList<ReservVO>();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String today = sdf.format(date);

		// �α��ε� ������� ���� �ð� ���� ���ų��� ��ȸ
		for (ReservVO vo : list_reserv) { 
			if (vo.getId().equals(user.getId()) && vo.getS_Day().compareTo(today) >= 0) {
				result.add(vo);
			}
		}

		// ��߳�¥ ������������ ����
		Comparator<ReservVO> comp = new Comparator<ReservVO>() {

			@Override
			public int compare(ReservVO o1, ReservVO o2) {
				return o1.getS_Day().compareTo(o2.getS_Day());
			}
		};

		Collections.sort(result, comp);
		
		if (result.size() == 0) {
			System.out.print("���ŵ� ������ �����ϴ�.\n");
			return false;
		}
		
		System.out.println("\n[**���ų���**]\n");
		System.out.print("�����ȣ\t\t�����\t������\t��߽ð�\n");
		for (ReservVO vo : result) {
			System.out.print(
					vo.getReservNo() + "\t" + vo.getDeparture() + "\t" + vo.getArrival() + "\t" + vo.getS_Day() + "\n");
		}

		return true;
	}

	@Override
	public UserVO updateUser(UserVO user, List<UserVO> list_user) {
		UserVO return_vo = null;
		char select;
		int cnt = 0;
		try {
			for (UserVO vo : list_user) {
				if (vo.getId().equals(user.getId())) {
					return_vo = vo;

					while (true) {
						System.out.print("[1]��й�ȣ����  [2]�̸�����  [3]������Ϻ��� [4]����  => ");
						select = br.readLine().charAt(0);

						if (select == '4') {
							if (cnt == 0) {
								System.out.println("����Ȱ��� �����ϴ�.\n");
								return null;
							}
							System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
							list_user.remove(vo);
							list_user.add(return_vo);
							return return_vo;
						}

						switch (select) {
						case '1':
							// ��й�ȣ����
							System.out.print("������ ��й�ȣ�� �Է����ּ���.");
							return_vo.setPw(br.readLine());
							break;
						case '2':
							// �̸�����
							System.out.print("������ �̸��� �Է����ּ���.");
							return_vo.setName(br.readLine());
							break;
						case '3':
							// ������Ϻ���
							System.out.print("������ ��������� �Է����ּ���");
							return_vo.setBirth(br.readLine());
							break;
						}
						cnt++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//ȸ��Ż��
	@Override
	public boolean deleteUser(UserVO user, List<UserVO> list_user, List<ReservVO> list_reserv) {
		try {
			String pwd;

			System.out.print("����Ȯ���� ���ؼ� ��й�ȣ�� �Է����ּ���.");
			pwd = br.readLine();
			
			// �α��ε� ������� ��й�ȣ Ȯ��
			if (!checkPwd(pwd, user)) {
				System.out.println("��й�ȣ�� �ǹٸ��� �ʽ��ϴ�.");
				return false;
			}
			
			// �α��ε� ������� ���ų����� �����ϴ��� Ȯ��
			for (ReservVO vo : list_reserv) {
				if (vo.getId().equals(user.getId())) {
					System.out.println("���ŵ� ǥ�� �ֽ��ϴ�.");
					System.out.println("ȸ��Ż�� ���Ͻø� ���ŵ� ǥ�� ����� �ּ���.");
					return false;
				}
			}
			
			for (UserVO vo : list_user) {
				if (vo.getId().equals(user.getId())) {
					list_user.remove(vo);
				}
			}

			System.out.print(user.getName() + "�� ���̵� ���������� �����Ǿ����ϴ�.\n");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ��й�ȣ�� ��ġ�ϴ��� ��
	@Override
	public boolean checkPwd(String pwd, UserVO user) {
		if (pwd.equals(user.getPw())) {
			return true;
		}
		return false;
	}

}
