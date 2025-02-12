package model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationBean implements Serializable {
	//フィールドの設定
	private int reserveId;
	private String loginId;
	private String campName;
	private LocalDate reserveDate;
	private LocalDateTime insertDate;
	private CampBean campBean;

	//publicな引数無しのコンストラクタを定義
	public ReservationBean() {
	}

	public ReservationBean(int reserveId, String loginId, String campName, LocalDate reserveDate,
			LocalDateTime insertDate, CampBean campBean) {
		this.reserveId = reserveId;
		this.loginId = loginId;
		this.campName = campName;
		this.reserveDate = reserveDate;
		this.insertDate = insertDate;
		this.campBean = campBean;
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

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public CampBean getCampBean() {
		return campBean;
	}

	public void setCampBean(CampBean campBean) {
		this.campBean = campBean;
	}
}
