package login;

import java.util.List;

import vo.UserVO;

public interface Login {
//	�α���
	public UserVO UserLogin(List<UserVO> list_user);

//	ȸ������
	public boolean addId(List<UserVO> list_user);

//	���̵����
	public UserVO findById(String id, List<UserVO> list_user);
}
