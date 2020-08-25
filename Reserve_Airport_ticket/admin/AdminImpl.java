package admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import user.User;
import user.UserImpl;
import util.DateUtil;
import vo.AirInfoVO;
import vo.ReservVO;
import vo.UserVO;

public class AdminImpl implements Admin {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	DateUtil du = new DateUtil();
	private Scanner sc = new Scanner(System.in);

	@Override
	public AirInfoVO readInfo(AirInfoVO addVO, List<AirInfoVO> list_info) {
		for (AirInfoVO vo : list_info) {
			if (vo.getA_Name().equals(addVO.getA_Name()) && vo.getS_Day().equals(addVO.getS_Day())) { //�װ��̸��̶� ��߳�¥ ������
				return vo; //�װ�������ü ����
			}
		}
		return null; //�װ��̸��̶� ��߳�¥ �ٸ��� null����
	}
	

	@Override
	public void airList(List<AirInfoVO> list_info) {
		if (list_info.size() == 0) { //����Ʈ�� �ƹ������� ������ -> ����Ʈ ������ 0
			System.out.println("�װ������� �����ϴ�.\n");
			return;
		}

		System.out.print("�װ����\t�����\t������\t��߳�¥\t\t\t������¥\t\t\t�����¼�\n");
		for (AirInfoVO vo : list_info) { //���� for������ ����Ʈ ��ü ���� ���
			System.out.print(vo.getA_Name() + "\t");
			System.out.print(vo.getDeparture() + "\t");
			System.out.print(vo.getArrival() + "\t");
			System.out.print(vo.getS_Day() + "\t");
			System.out.print(vo.getE_Day() + "\t");
			System.out.print(vo.getSeat_cnt() + "\n");
		}

	}

	@Override
	public void addAirList(List<AirInfoVO> list_info) {
		try {
			Date date = new Date(); //Date��ü ����
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String today = sdf.format(date); //yyyy-MM-dd HH:mm���·� ���� ��¥ date��ü�� string���� ����


			AirInfoVO vo = new AirInfoVO(); //�װ����� ��ü ����

			System.out.print("�װ���� ? ");
			vo.setA_Name(br.readLine()); //���پ� �Է��ϸ鼭 ������ �װ����� ��ü�� �־��ش�.
			System.out.print("����� ? ");
			vo.setDeparture(br.readLine());
			System.out.print("������ ? ");
			vo.setArrival(br.readLine());

			
			
			System.out.print("��߳�¥ ? ");
			String arrivalday = br.readLine(); //��߳�¥ string�� �Է¹޾Ƽ�
			
			String change_arrivday = du.standardDate(arrivalday); //�پ��� ������ ��߳�¥ string���¸�  yyyy-MM-dd HH:mm�� �����ϰ� ������Ŵ
			
			if (du.isValidDate(arrivalday) == true) { //�Է¹��� ��¥��Ʈ���� ��ȿ�ϰ�
				if(change_arrivday.compareTo(today)>=0) { // ��߳�¥�� ���ó�¥�� ��������, ���ó�¥���� ��߳�¥�� �����̸�
					vo.setS_Day(change_arrivday); //�Է¹��� ��¥�� �װ����� ��ü�� ����
				}
				else { //���ó�¥���� ��߳�¥�� �����̸� �߸��� �װ������̹Ƿ� ����
					System.out.println("�߸��� ��¥�Դϴ�.");
					return;
				}
			} else { //��¥ ��Ʈ���� ��ȿ���� ���� ���̸� ����
				System.out.println("�߸��� ��¥�Դϴ�.");
				return;
			}

			
			
			System.out.print("������¥ ? "); 
			String departureday = br.readLine();                     //������¥ string �Է�
			
			String change_departday = du.standardDate(departureday); //������¥�� ��߳�¥�� ���������� yyyy-MM-dd HH:mm���·� �����ϰ� ������Ŵ
			
			if (du.isValidDate(departureday) == true) {             //�Է¹��� ������¥�� ��ȿ�ϰ�,
				
				if(change_departday.compareTo(change_arrivday)>=0) //������¥���� ��߳�¥�� �����̸�,  (��߳�¥ - ������¥ ��)
					vo.setE_Day(change_departday);                 //�װ����� ��ü�� ��������
				
				else {                                              //������¥���� ��߳�¥�� �����̸�,  (������¥ - ��߳�¥ ��) ����
					System.out.println("�߸��� ��¥�Դϴ�.");
					return;
				}					
			} else {                                               //������¥�� ��ȿ���� ���� ���̸� ����
				System.out.println("�߸��� ��¥�Դϴ�.");
				return;
			}

			
			
			System.out.print("�����¼� ? ");
			vo.setSeat_cnt(Integer.parseInt(br.readLine()));      // ������� �¼� ���� �Է¹ް� ����.

			AirInfoVO test = readInfo(vo, list_info);             //���� �Է¹޴� �װ����� ��ü�� Airinfo.txt�� ����� ����Ʈ���� �ߺ��� �ִ��� �˻�

			
			if (test != null) {                                    // �Է¹��� AirInfoVO��ü�� air����Ʈ�� ������ => "�ߺ��� �װ������Դϴ�." ���
				System.out.println("�ߺ��� �װ������Դϴ�.\n");
				return;
			}

			list_info.add(vo);                                     // �ߺ��� ��ü�� ���ٸ� air�� �ش� �Է¹��� ��ü ���� �Ѱ���.
			System.out.println("�װ������� ��ϵǾ����ϴ�.");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void deleteAirList(List<AirInfoVO> list_info, List<ReservVO> list_reserv) {
		try {
			AirInfoVO vo = new AirInfoVO();
			String[][] test = new String[list_info.size()][6];     //test��� String 2���� �迭 ���� (���� ����Ʈ�� ����/ ���� �װ����....�����ð�,�����¼� = 6����)
			int cnt = 0;
			int select;

			System.out.println("\n[���׸��]\n");
			System.out.print("��ȣ\t�װ����\t�����\t������\t��߽ð�\t\t\t�����ð�\t\t\t�����¼�\n");
			for (AirInfoVO vi : list_info) {                                      // Airinfo.txt�� ����� ����Ʈ ���� [(��ȣ) �װ���� ����� ������ ��߽ð� �����ð� �����¼�]�� ���·� ���
				                                                                  // txt������ ������ test������ �迭�� �ٽ� ����.
				System.out.print("(" + (cnt + 1) + ")\t" + vi.getA_Name() + "\t");
				test[cnt][0] = vi.getA_Name();
				System.out.print(vi.getDeparture() + "\t");
				test[cnt][1] = vi.getDeparture();
				System.out.print(vi.getArrival() + "\t");
				test[cnt][2] = vi.getArrival();
				System.out.print(vi.getS_Day() + "\t");
				test[cnt][3] = vi.getS_Day();
				System.out.print(vi.getE_Day() + "\t");
				System.out.print(vi.getSeat_cnt() + "\n");

				cnt++;                            //���� ��Ͽ��� ��ȣ�� ����ϱ� ���� 
			}

			do {
				System.out.print("���Ͻô� �װ����� ��ȣ�� �Է����ּ���."); //���� ������ ����Ʈ ��Ͽ��� ���ϴ� ��ȣ�� �����ϸ�
				select = sc.nextInt(); 
			} while (select < 1 || select > list_info.size());

			int sel = select - 1;                                      //����Ʈ�� 0������ �����̴ϱ� �ε��� ���� �����ѹ�ȣ -1 �� ����

			vo.setA_Name(test[sel][0]);                                //������ ��ȣ�� ����Ʈ ������  vo�װ�������ü�� �ٽ� ���� 
			vo.setDeparture(test[sel][1]);
			vo.setArrival(test[sel][2]);
			vo.setS_Day(test[sel][3]);

			AirInfoVO result = readInfo(vo, list_info); //������ ��ü�� ����Ʈ�� ���� ��ü�� �ִ��� Ȯ��, ���� ��ü�� ������ ��ü ���Ϲް�, ������ null ���Ϲ���

			if (result == null) {                       // ��ü�� ����Ʈ�� ����Ǿ����� ������
				System.out.println("�ش� ������ �����ϴ�.\n");
				return;                                 // "�ش� ������ �����ϴ�"
			}

			for (ReservVO vf : list_reserv) {         
				if (vf.getA_Name().equals(vo.getA_Name())) {
					list_reserv.remove(vf);
				}
			}

			list_info.remove(result); // �ش� ��ü ����
			System.out.print("�ش������� �����Ǿ����ϴ�.\n");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	@Override
	public void userControl(List<UserVO> list_user) {
		List<UserVO> result = new LinkedList<UserVO>();
		int cnt = 0;
		char select;

		for (UserVO vo : list_user) {         //���� ����Ʈ�� Ž��
			if (!vo.getId().equals("user")) { //"user"�� ������ ��� ����ڸ� ���� ������ result ����Ʈ�� ���� �߰�
				result.add(vo);               
			}
		}

		if (result.size() == 0) {      //���� ����Ʈ ����� 0�̸� ����� ������ ����
			System.out.println("����� �����Ͱ� �����ϴ�. \n");
			return;
		}

		
		// User.txt�� ����� �������� ������ [(��ȣ) �̸� ���̵� �н����� �������]�� ���·� ���
		// ���ÿ� ���� ������ ���ο� string ������ �迭�� ����
		String[][] userInfo = new String[result.size()][4]; 

		System.out.print("��ȣ\t�̸�\t���̵�\t�н�����\t�������\n");
		for (UserVO vo : result) {
			System.out.print("(" + (cnt + 1) + ")\t" + vo.getName() + "\t");
			userInfo[cnt][0] = vo.getName();
			System.out.print(vo.getId() + "\t");
			userInfo[cnt][1] = vo.getId();
			System.out.print(vo.getPw() + "\t");
			userInfo[cnt][2] = vo.getPw();
			System.out.print(vo.getBirth() + "\n");
			userInfo[cnt][3] = vo.getBirth();
			cnt++;
		}

		
		
		try {
			do {
				System.out.print("[1]��������  [2]������������  [3]����");
				select = br.readLine().charAt(0); 
			} while (select < '1' || select > '3');

			if (select == '3') {
//				����
				return;
			}

			switch (select) {
			case '1':
//				��������
				int select_delete = 0;
				try {
					do {
						
						System.out.print("������ ��ȣ"); //���� ���� ����Ʈ���� ������ ��ȣ�� �Է¹���
						select_delete = sc.nextInt();
						if(select_delete > result.size() || select_delete <1) {
							System.out.println("�ش��ϴ� ��ȣ�� �����ϴ�.");
							return;
						}
					} while (select_delete < 1 || select_delete > result.size()); 
				

				} catch (InputMismatchException e) {
					sc.nextLine();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}

				select_delete--;  //������ ��ȣ�� -1 ������,
				
				if (select_delete == -1) { //-1�̸� ���� (��ȣ�� 1���ͽ��� )
					System.out.println("����");
					return;
				}

				

				for (UserVO vo : list_user) {
					if (vo.getName().equals(userInfo[select_delete][0]) && vo.getId().equals(userInfo[select_delete][1]) //���� ����Ʈ���� ������ ������ �̸���, �н����� ������ ���� ������ ������
							&& vo.getPw().equals(userInfo[select_delete][2])
							&& vo.getBirth().equals(userInfo[select_delete][3])) {
						list_user.remove(vo);                                                           //����
						System.out.println("\n���̵� �����Ǿ����ϴ�.");
						break;
					}
				}

				break;
			case '2':
//				������������
				User us = new UserImpl();
				int select_update = 0;

				try {
					do {
						System.out.print("������ ��ȣ");
						select_update = sc.nextInt();
						if(select_update > result.size() || select_update <1) {
							System.out.println("�ش��ϴ� ��ȣ�� �����ϴ�.");
							return;
						}
					} while (select_update < 1 || select_update > result.size());

				} catch (InputMismatchException e) {
					//sc.nextLine();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}

				select_update--;

				if (select_update == -1) {
					System.out.println("����");
					return;
				}

				for (UserVO vo : list_user) {
					if (vo.getName().equals(userInfo[select_update][0]) && vo.getId().equals(userInfo[select_update][1]) //���� ����Ʈ���� ������ ������ �̸���,���̵�,�н�����,������ ���� ������ ������
							&& vo.getPw().equals(userInfo[select_update][2])
							&& vo.getBirth().equals(userInfo[select_update][3])) {
						us.updateUser(vo, list_user);                           //����Ʈ�� �ش� ��ü�� ������ ������Ʈ
						break;
					}
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
