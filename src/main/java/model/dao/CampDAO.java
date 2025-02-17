package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CampBean;

public class CampDAO {
	//キャンプ場で絞り込み
	public CampBean getCampListByCampName(CampBean campBean) throws ClassNotFoundException, SQLException {
		//SQL文
		String sql = "SELECT camp_name,location,tel,charge,capacity FROM camp WHERE camp_name=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, campBean.getCampName());
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				String campName = res.getString("camp_name");
				String location = res.getString("location");
				String tel = res.getString("tel");
				int charge = res.getInt("charge");
				int capacity = res.getInt("capacity");
				campBean = new CampBean(campName, location, tel, charge, capacity);
			}
		}
		return campBean;
	}

	//都道府県で絞り込み
	public List<CampBean> getCampListByLocation(String location) throws ClassNotFoundException, SQLException {
		List<CampBean> CampList = new ArrayList<>();
		//SQL文
		String sql = "SELECT camp_name,location,tel,charge,capacity FROM camp WHERE location=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, location);
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				String campname = res.getString("camp_name"); // campテーブルcamp_nameカラムの値
				location = res.getString("location"); // campテーブルlocationカラムの値
				String tel = res.getString("tel"); // campテーブルtelカラムの値
				int charge = res.getInt("charge"); // campテーブルchargeカラムの値
				int capacity = res.getInt("capacity"); // campテーブルcapacityカラムの値
				System.out.println(campname);
				// DBから取得した値を初期値として、CampListのインスタンス生成
				CampBean todo = new CampBean(campname, location, tel, charge, capacity);

				// CampListにインスタンスを追加
				CampList.add(todo);
			}
		}
		return CampList;
	}

	//編集登録処理
	public int updateCampList(CampBean campBean) throws ClassNotFoundException, SQLException {
		int resultNum = 0;
		//SQL文
		String sql = "UPDATE camp SET";
		sql += " camp_name=?,";
		sql += " location=?,";
		sql += " tel=?,";
		sql += " charge=?,";
		sql += " capacity=?";
		sql += " WHERE camp_name=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, campBean.getCampName());
			pstmt.setString(2, campBean.getLocation());
			pstmt.setString(3, campBean.getTel());
			pstmt.setInt(4, campBean.getCharge());
			pstmt.setInt(5, campBean.getCapacity());
			pstmt.setString(6, campBean.getCampName());
			//SQLの実行
			resultNum = pstmt.executeUpdate();
		}
		return resultNum;
	}

	//キャンプ場の新規登録をするメソッド
	public int RegistCampList(CampBean campBean) throws ClassNotFoundException, SQLException {
		//SQL
		String sql = "INSERT INTO camp (camp_name,location,tel,charge,capacity )VALUES (?, ?, ?, ?, ?)";
		int resultNum = 0;
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection(); //スロー宣言の追加
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, campBean.getCampName());
			pstmt.setString(2, campBean.getLocation());
			pstmt.setString(3, campBean.getTel());
			pstmt.setInt(4, campBean.getCharge());
			pstmt.setInt(5, campBean.getCapacity());

			//SQLの実行
			resultNum = pstmt.executeUpdate();
			System.out.println(campBean.getCampName());
		}
		return resultNum;
	}

	// キャンプ場全データ表示 ＠近藤
	public List<CampBean> getCampList() throws ClassNotFoundException, SQLException {

		// リストの初期化
		List<CampBean> CampList = new ArrayList<>();

		// SQL文
		String sql = "SELECT * FROM camp";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// SQL実行し、実行結果の表と現在の行を指しているカーソルを取得
			ResultSet res = pstmt.executeQuery();

			// 実行結果の表から順番に値を取得
			// nextでカーソルを1行ずつ移動させる
			while (res.next()) {
				String campname = res.getString("camp_name"); // campテーブルcamp_nameカラムの値
				String location = res.getString("location"); // campテーブルlocationカラムの値
				String tel = res.getString("tel"); // campテーブルtelカラムの値
				int charge = res.getInt("charge"); // campテーブルchargeカラムの値
				int capacity = res.getInt("capacity"); // campテーブルcapacityカラムの値
				System.out.println(campname);
				// DBから取得した値を初期値として、CampListのインスタンス生成
				CampBean todo = new CampBean(campname, location, tel, charge, capacity);

				// CampListにインスタンスを追加
				CampList.add(todo);
			}
		}
		return CampList;
	}

}