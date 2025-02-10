package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.CampBean;

public class CampDAO {
	//キャンプ場で絞り込み
	public CampBean getCampListByCampName(CampBean campBean) throws ClassNotFoundException, SQLException {
		//SQL文（caharge修正必要）
		String sql = "SELECT camp_name,location,tel,caharge,capacity FROM camp WHERE camp_name=?";
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
				int charge = res.getInt("caharge");
				int capacity = res.getInt("capacity");
				campBean = new CampBean(campName, location, tel, charge, capacity);
			}
		}
		return campBean;
	}

	//編集登録処理
	public int updateCampList(CampBean campBean) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
