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
	public int updateCampList(CampBean campBean) throws ClassNotFoundException, SQLException {
		int resultNum = 0;
		//SQL文（caharge修正必要）
		String sql = "UPDATE camp SET";
		sql += " camp_name=?,";
		sql += " location=?,";
		sql += " tel=?,";
		sql += " caharge=?,";
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

}
