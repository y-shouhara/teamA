package model.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class ReservationBean implements Serializable {
	//フィールドの設定
	private int reserveId;
	private String loginId;
	private String campName;
	private LocalDate reserveDate;
	private LocalDate insertDate;

	//publicな引数無しのコンストラクタを定義
	public ReservationBean() {
	}

	//アクセッサ
	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public LocalDate getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(LocalDate reserveDate) {
		this.reserveDate = reserveDate;
	}

	public LocalDate getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDate insertDate) {
		this.insertDate = insertDate;
	}

}
