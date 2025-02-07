package model.entity;

import java.io.Serializable;

public class CampBean implements Serializable {
	//フィールドの設定
	private String campName;
	private String location;
	private String tel;
	private int caharge;
	private int capacity;

	//publicな引数無しのコンストラクタを定義
	public CampBean() {
	}

	//アクセッサ
	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCaharge() {
		return caharge;
	}

	public void setCaharge(int caharge) {
		this.caharge = caharge;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
