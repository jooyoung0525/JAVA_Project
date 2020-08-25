package util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import vo.AirInfoVO;
import vo.ReservVO;
import vo.UserVO;


/**
 * ��������, �װ�����,ȸ������ ���/����/������ ������ ������Ʈ�ϰ�, 
 * �ҷ����� ����� ��� Ŭ�����Դϴ�.
 * �ش� ������ txt���Ϸ� ����˴ϴ�.
 * @author �̿���
 *
 */
public class FileUtil {
	private List<UserVO> list_user = new LinkedList<>();
	private List<AirInfoVO> list_info = new LinkedList<>();
	private List<ReservVO> list_reserv = new LinkedList<>();

	private String User_Path = "User.txt"; // �������� ����
	private String AirInfo_Path = "AirInfo.txt"; // �װ����� ����
	private String Reserv_Path = "Reserv.txt"; // �������� ����

	// ������
	public FileUtil() {
		load_UserF();
		load_AirInfoF();
		load_ReservF();
	}

	/*
	 * ���� ���� ����ó�� �޼ҵ�
	 */

	public void save_UserF() {
		File file = new File(User_Path);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			for (UserVO vo : list_user) {
				oos.writeObject(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {
				}
			}
		}

	}

	/*
	 * ���� ���� ���� �ε�
	 */
	public void load_UserF() {
		File file = new File(User_Path);
		if (!file.exists()) {
			return;
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));

			while (true) {
				UserVO vo = (UserVO) ois.readObject();
				list_user.add(vo);
			}
		} catch (EOFException e) {
			// �ƹ��͵� �Է��ؼ� �ȵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/*
	 * �װ����� ����ó��
	 */
	public void save_AirInfoF() {
		File file = new File(AirInfo_Path);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			for (AirInfoVO vo : list_info) {
				oos.writeObject(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/*
	 * �װ����� ���� �ε�
	 */
	public void load_AirInfoF() {
		File file = new File(AirInfo_Path);
		if (!file.exists()) {
			return;
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));

			while (true) {
				AirInfoVO vo = (AirInfoVO) ois.readObject();
				list_info.add(vo);
			}
		} catch (EOFException e) {
			// �ƹ��͵� �Է��ؼ� �ȵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/*
	 * �������� ����ó��
	 */
	public void save_ReservF() {
		File file = new File(Reserv_Path);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			for (ReservVO vo : list_reserv) {
				oos.writeObject(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/*
	 * �������� ���� �ε�
	 */
	public void load_ReservF() {
		File file = new File(Reserv_Path);
		if (!file.exists()) {
			return;
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));

			while (true) {
				ReservVO vo = (ReservVO) ois.readObject();
				list_reserv.add(vo);
			}
		} catch (EOFException e) {
			// �ƹ��͵� �Է��ؼ� �ȵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/*
	 * ���Ͽ� �ִ� ������ list�� ���� �޴� �޼ҵ�.
	 */

	public List<UserVO> getUser() {
		return list_user;
	}

	public List<AirInfoVO> getInfo() {
		return list_info;
	}

	public List<ReservVO> getReserv() {
		return list_reserv;
	}
}
