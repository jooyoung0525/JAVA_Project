package reserv;

import java.util.List;

import vo.AirInfoVO;
import vo.ReservVO;

public interface Reserv {
	public void reserv(List<ReservVO> list_reserv, List<AirInfoVO> list_info, String id); // ����
	public void cancleReserv(List<ReservVO> list_reserv, List<AirInfoVO> list_info, String id); // �������
	public ReservVO readReservNO(ReservVO vo, List<ReservVO> list_reserv); // ���Ź�ȣ ����
	
}
