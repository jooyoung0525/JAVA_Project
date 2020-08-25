package login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import vo.UserVO;

public class LoginImpl implements Login {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//	ȸ������
	@Override
	public UserVO UserLogin(List<UserVO> list_user) {
		try {
			String id, pwd;
			
//			���̵� �Է¹޾� �ش� ���̵� list_user(������������Ʈ)�� �����ϴ��� Ȯ��
			System.out.print("���̵� ? ");
			id = br.readLine();
		
			UserVO vo = findById(id, list_user);

			if (vo == null) {
				System.out.println("�������� �ʴ� ID �Դϴ�.");
				return null;
			}

//			�Է��� ��й�ȣ�� �ǹٸ� ��й�ȣ���� Ȯ��
			System.out.print("��й�ȣ ? ");
			pwd = br.readLine();

			if (!vo.getPw().equals(pwd)) {
				System.out.println("��й�ȣ�� �ǹٸ��� �ʽ��ϴ�.");
				return null;
			}

//			������� �޼���
			System.out.print(vo.getName() + "�� ������ ���������� �α��� �Ǿ����ϴ�.\n");
//			MainUI���� UserVO user�� �־��ֱ� ���ؼ� ��ȯ
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addId(List<UserVO> list_user) {
		try {
			String id;

//			���̵� �Է¹޾� �ش� ���̵� �̹� ��ϵ� ���̵� �´��� Ȯ��
			System.out.print("���̵� ?");
			id = br.readLine();
			
			UserVO vo = findById(id, list_user);

//			�̹� �����Ѵٸ� �޼��� ���
			if (vo != null) {
				System.out.println("�̹� �����ϴ� ���̵� �Դϴ�.\n");
				return false;
			}

//			��������������, ȸ������������ �ʿ��� ���� �޾� ����
			UserVO vo1 = new UserVO();
			vo1.setId(id);
			System.out.print("��й�ȣ ? ");
			vo1.setPw(br.readLine());
			System.out.print("�̸� ? ");
			vo1.setName(br.readLine());
			System.out.print("������� ? ");
			vo1.setBirth(br.readLine());
			
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			
//			�����Ͱ� �ΰ��� �ƴ϶�� ���������� ȸ����������Ʈ�� �߰�
			if (vo1 != null) {
				list_user.add(vo1);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	���̵� �ߺ� üũ
//	true : �ش� ���̵� ����
//	false : �ش� ���̵� ����
	@Override
	public UserVO findById(String id, List<UserVO> list_user) {
		try {
			for (UserVO vo : list_user) {
				if (vo.getId().equals(id)) {
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
