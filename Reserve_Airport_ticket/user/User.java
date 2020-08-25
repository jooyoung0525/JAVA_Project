package user;

import java.util.List;

import vo.ReservVO;
import vo.UserVO;

public interface User {
//	����� ���� ��ȸ
	public boolean reservSearch(UserVO user, List<UserVO> list_user, List<ReservVO> list_reserv);

//	��������
	public UserVO updateUser(UserVO user, List<UserVO> list_user);

//	ȸ��Ż��
	public boolean deleteUser(UserVO user, List<UserVO> list_user, List<ReservVO> list_reserv);
	
//	��й�ȣ Ȯ��
	public boolean checkPwd(String pwd, UserVO user);
}
