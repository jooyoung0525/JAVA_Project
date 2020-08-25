package reserv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import vo.AirInfoVO;
import vo.ReservVO;

public class ReservImpl implements Reserv {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Scanner sc = new Scanner(System.in);

	/*
	 * ���ű��
	 */
	@Override
	public void reserv(List<ReservVO> list_reserv, List<AirInfoVO> list_info, String id) {
		ReservVO re = new ReservVO();
		AirInfoVO in = null;
		String[][] test = new String[list_info.size()][4];
		int cnt = 0;
		int select;

		try {
			if (list_info.size() == 0) {
				System.out.println("�װ������� �����ϴ�.\n");
				return;
			}

			List<AirInfoVO> result = new LinkedList<AirInfoVO>();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String today = sdf.format(date);
			
			
			// �������� �������� ���
			for (AirInfoVO vo : list_info) {
				if (vo.getS_Day().compareTo(today) >= 0) {
					result.add(vo);
				}
			}
			
			// ���������� ���� ���
			if (result.size() == 0) {
				System.out.println("�װ������� �����ϴ�.");
				return;
			}

			System.out.println("\n[���׸��]\n");
			System.out.print("��ȣ\t�װ����\t�����\t������\t��߽ð�\t\t\t�����ð�\t\t\t�����¼�\n");
			// �������� ���
			for (AirInfoVO vo : result) {
				System.out.print("(" + (cnt + 1) + ")\t" + vo.getA_Name() + "\t");
				test[cnt][0] = vo.getA_Name();
				System.out.print(vo.getDeparture() + "\t");
				test[cnt][1] = vo.getDeparture();
				System.out.print(vo.getArrival() + "\t");
				test[cnt][2] = vo.getArrival();
				System.out.print(vo.getS_Day() + "\t");
				test[cnt][3] = vo.getS_Day();
				System.out.print(vo.getE_Day() + "\t");
				System.out.print(vo.getSeat_cnt() + "\n");

				cnt++;
			}

			do {
				System.out.print("���Ͻô� �װ����� ��ȣ�� �Է����ּ���.");
				select = sc.nextInt();
			} while (select < 1 || select > list_info.size());

			int sel = select - 1;

			re.setA_Name(test[sel][0]);
			re.setDeparture(test[sel][1]);
			re.setArrival(test[sel][2]);
			re.setS_Day(test[sel][3]);
			
			// ����� ������ ��߽ð� �װ������ ���������� ��ϵ� �Ͱ� ��ġ���� ������ ���ŵ�������.
			for (AirInfoVO ai : list_info) {
				if (re.getDeparture().equals(ai.getDeparture()) && re.getArrival().equals(ai.getArrival())
						&& re.getS_Day().equals(ai.getS_Day()) && re.getA_Name().equals(ai.getA_Name())) {
					if (ai.getSeat_cnt() <= 0) {
						System.out.println("�¼�����");
						return;
					}

					in = ai;
					break;
				}
			}
			
			if (in == null) {
				System.out.println("�߸��� �Է��Դϴ�.\n");
				return;
			}
			
			// ���Ź�ȣ ����
			String reserv = "20-00000";
			if (list_reserv.size() > 0) {
				reserv = list_reserv.get(list_reserv.size() - 1).getReservNo();
			}

			String reserv1 = reserv.substring(0, reserv.lastIndexOf("-"));
			String reserv2 = reserv.substring(reserv.lastIndexOf("-") + 1);

			int n = Integer.parseInt(reserv2) + 1;

			reserv = reserv1 + "-" + String.format("%05d", n);

			// id���� ���� ��� Ÿ���� ���������� ���Ź�ȣ�� �˰������� ���� �Ҽ� �ֱ� ������
			re.setId(id); // ������ ������ҽ� ������ id���� �־�� �ϱ� ������ �������� ��ü�� ȸ������ id�� �־���
			re.setReservNo(reserv); // ������ ���Ź�ȣ�� �������� ��ü�� �־���
			System.out.println("\n���ŵǾ����ϴ�.");
			System.out.println("���Ź�ȣ : " + reserv); 

			in.setSeat_cnt(in.getSeat_cnt() - 1); // ���� ������ ���� �װ������� ����� �¼���-1
			list_reserv.add(re); // �������� ����Ʈ�� ����
			return;

		} catch (InputMismatchException e) {
			sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	/*
	 * �������
	 */
	@Override
	public void cancleReserv(List<ReservVO> list_reserv, List<AirInfoVO> list_info, String id) {

		try {
			ReservVO vo = new ReservVO();

			System.out.println("���Ÿ� ����� ��ȣ�� �Է����ּ���.");
			vo.setReservNo(br.readLine());

			ReservVO de = readReservNO(vo, list_reserv); // ���Ź�ȣ ���� �޼ҵ带 �ҷ��ͼ� �� ��� ���� de�� �־���

			if (de == null) { // de�� ����Ʈ�� ������
				System.out.println("�������� �ʴ� ���Ź�ȣ�Դϴ�.\n");
				return;
			}
			
			
			// ���������� ����� id���� ȸ�������� id���� �ٸ� �� ���Ź�ȣ�� �´��� ���� �� �� ����
			if (de.getId().equals(id)) {
				list_reserv.remove(de); // �ش� ��ü ����
				System.out.println("���Ű� ��ҵǾ����ϴ�.\n");
				
				for (AirInfoVO iv : list_info) {
					// ������ ���������� �����, ������, ��߳��ڰ� ���������� ������ ���  
					if (de.getDeparture().equals(iv.getDeparture()) && de.getArrival().equals(iv.getArrival())
							&& de.getS_Day().equals(iv.getS_Day())) {
						// �¼����� +1����
						iv.setSeat_cnt(iv.getSeat_cnt() + 1);
						break;
					}
				}

			} else {
				System.out.println("���������� ��ġ���� �ʽ��ϴ�.\n");
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * ���Ź�ȣ ����
	 */
	@Override
	public ReservVO readReservNO(ReservVO vo, List<ReservVO> list_reserv) {
		for (ReservVO re : list_reserv) {
			if (re.getReservNo().equals(vo.getReservNo())) {
				return re;
			}
		}
		return null;
	}
}
