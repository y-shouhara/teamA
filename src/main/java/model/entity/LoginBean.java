package model.entity;

import java.io.Serializable;

public class LoginBean implements Serializable {
	//フィールドの設定
	private String loginId;
	private String salt;
	private String hashSolt;
	private int managerId;

	//publicな引数無しのコンストラクタを定義
	public LoginBean() {
	}

	//データ格納用のコンストラクタ
	public LoginBean(String loginId, String salt, String hashSolt, int managerId) {
		this.loginId = loginId;
		this.salt = salt;
		this.hashSolt = hashSolt;
		this.managerId = managerId;
	}

	//アクセッサ

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashSolt() {
		return hashSolt;
	}

	public void setHashSolt(String hashSolt) {
		this.hashSolt = hashSolt;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
