package vo;

import java.io.Serializable;

public class ReservVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // ȸ�����̵�
	private String s_Day; // ��߳�¥
//	private String e_Day; // ������¥
	private String departure; // �����
	private String arrival; // ������
	private String reservNo; // �����ȣ
	private String a_Name; // ������̸�

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getS_Day() {
		return s_Day;
	}

	public void setS_Day(String s_Day) {
		this.s_Day = s_Day;
	}

	/*
	 * public String getE_Day() { return e_Day; } public void setE_Day(String e_Day)
	 * { this.e_Day = e_Day; }
	 */
	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getReservNo() {
		return reservNo;
	}

	public void setReservNo(String reservNo) {
		this.reservNo = reservNo;
	}

	public String getA_Name() {
		return a_Name;
	}

	public void setA_Name(String a_Name) {
		this.a_Name = a_Name;
	}

}
