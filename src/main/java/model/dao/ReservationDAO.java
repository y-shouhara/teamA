package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.CampBean;
import model.entity.ReservationBean;

public class ReservationDAO {
	//個人予約情報を表示する
	public List<ReservationBean> getReservationList(String loginId) throws ClassNotFoundException, SQLException {
		List<ReservationBean> reservationBeanList = new ArrayList<ReservationBean>();
		//SQL文の設定
		String sql = "SELECT r.reserve_id,r.login_id,r.camp_name,r.reserve_date,r.insert_date,c.location,c.tel,c.charge,c.capacity";
		sql += " FROM reserve as r";
		sql += " LEFT JOIN camp as c ON c.camp_name = r.camp_name";
		sql += " WHERE login_id=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, loginId);
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				//reserveテーブルデータ
				int reserveId = res.getInt("reserve_id");
				loginId = res.getString("login_id");
				String campName = res.getString("camp_name");
				LocalDate reserveDate = res.getDate("reserve_date").toLocalDate();
				LocalDateTime insertDate = res.getTimestamp("insert_date").toLocalDateTime();
				//campテーブルデータ
				String location = res.getString("location");
				String tel = res.getString("tel");
				int charge = res.getInt("charge");
				int capacity = res.getInt("capacity");
				CampBean campBean = new CampBean(campName, location, tel, charge, capacity);
				ReservationBean reservationBean = new ReservationBean(reserveId, loginId, campName, reserveDate,
						insertDate, campBean);
				reservationBeanList.add(reservationBean);
			}
		}
		return reservationBeanList;
	}

	//個人予約のキャンセル
	/**
	 * reserveから、該当reserve_idのレコードを削除する
	 * @return 削除件数
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */

	public int deleteReservation(int reserveId) throws ClassNotFoundException, SQLException {
		//戻り値用の変数定義
		int deleteCount = 0;
		//⓵sql文の準備 今回はWHERE句に使用するので、プレースホルダを使用
		String sql = "DELETE FROM reserve WHERE reserve_id = ?";
		//⓶DBとの接続およびsqlの実行準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//⓷プレースホルダに値をセット
			pstmt.setInt(1, reserveId);
			//⓸sqlの実行
			deleteCount = pstmt.executeUpdate();
		}
		return deleteCount;

	}

	//予約画面に表示するカレンダーの日付情報を取得
	public void name(LocalDate targetDay) {

	}

	//キャンプ場名と日付で予約状況表示用データを取得
	public List<ReservationBean> getAvailability(String campName, LocalDate targetDay)
			throws ClassNotFoundException, SQLException {
		List<ReservationBean> reservationBeanList = new ArrayList<ReservationBean>();
		//絞り込み期間の設定
		LocalDate targetDayPlus = targetDay.plusDays(6);
		//LocalDate⇒sql.Dateに変換
		Date startDay = java.sql.Date.valueOf(targetDay);
		Date endDay = java.sql.Date.valueOf(targetDayPlus);
		//SQL文の取得
		String sql = "SELECT camp_name,reserve_date FROM reserve";
		sql += " WHERE camp_name=?";
		sql += " AND reserve_date BETWEEN ? AND ?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, campName);
			pstmt.setDate(2, startDay);
			pstmt.setDate(3, endDay);
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				ReservationBean reservationBean = new ReservationBean();
				reservationBean.setCampName(res.getString("camp_name"));
				reservationBean.setReserveDate(res.getDate("reserve_date").toLocalDate());
				reservationBeanList.add(reservationBean);
			}
		}
		return reservationBeanList;
	}

	public int reservation(ReservationBean reservation) throws SQLException, ClassNotFoundException {
		int count =0;
		String query = "INSERT INTO reserve (login_id, camp_name, reserve_date) VALUES (?, ?, ?)";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1,reservation.getLoginId() );
			pstmt.setString(2,reservation.getCampName() );
			pstmt.setDate(3,java.sql.Date.valueOf(reservation.getReserveDate()) );
			//⓸sqlの実行
			count = pstmt.executeUpdate();
		}
		return count;
	}

	public ReservationBean getDeleteReservation(int reservationId) throws ClassNotFoundException, SQLException {
		ReservationBean reservationBean = null;
		//SQL文の設定
		String sql = "SELECT r.reserve_id,r.login_id,r.camp_name,r.reserve_date,r.insert_date,c.location,c.tel,c.charge,c.capacity";
		sql += " FROM reserve as r";
		sql += " LEFT JOIN camp as c ON c.camp_name = r.camp_name";
		sql += " WHERE reserve_id=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setInt(1, reservationId);
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				//reserveテーブルデータ
				int reserveId = res.getInt("reserve_id");
				String loginId = res.getString("login_id");
				String campName = res.getString("camp_name");
				LocalDate reserveDate = res.getDate("reserve_date").toLocalDate();
				LocalDateTime insertDate = res.getTimestamp("insert_date").toLocalDateTime();
				//campテーブルデータ
				String location = res.getString("location");
				String tel = res.getString("tel");
				int charge = res.getInt("charge");
				int capacity = res.getInt("capacity");
				CampBean campBean = new CampBean(campName, location, tel, charge, capacity);
				 reservationBean = new ReservationBean(reserveId, loginId, campName, reserveDate,
						insertDate, campBean);
				//System.out.println(campName);
			}
		}
		return reservationBean;
	}
}
