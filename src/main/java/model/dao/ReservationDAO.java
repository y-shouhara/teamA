package model.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.ReservationBean;

public class ReservationDAO {
	//個人予約情報を表示する
	public List<ReservationBean> getReservationList(String loginId) {
		List<ReservationBean> reservationBeanList = new ArrayList<ReservationBean>();

		return reservationBeanList;
	}

	//個人予約のキャンセル（名称、ログインID、予約日でフィルター）

	//選択したキャンプ場の予約状況を取得
	public void name() {

	}

	//キャンプ場名と日付で予約状況表示用データを取得
	public List<ReservationBean> getAvailability(String campName, LocalDate targetDay) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
