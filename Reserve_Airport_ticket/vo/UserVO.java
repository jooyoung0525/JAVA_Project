package vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // ȸ���̸�
	private String id; // ���̵�
	private String pw; // ��й�ȣ
	private String birth; // �������

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
}
