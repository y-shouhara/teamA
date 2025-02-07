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
