package admin;

import java.util.List;

import vo.AirInfoVO;
import vo.ReservVO;
import vo.UserVO;

public interface Admin {
//	�װ��� ��� ��ȸ
	public void airList(List<AirInfoVO> list_info);

//	�װ��� ����
	public void addAirList(List<AirInfoVO> list_info);

//	�װ���������
	public void deleteAirList(List<AirInfoVO> list_info, List<ReservVO> list_reserv);

//	���� ����
	public void userControl(List<UserVO> list_user);

//	�װ����� �ߺ�Ȯ��
	public AirInfoVO readInfo(AirInfoVO vo, List<AirInfoVO> list_info);
}
