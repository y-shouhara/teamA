package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/**
	* データベースの URL
	*/
	private final static String URL = "jdbc:mysql://localhost:3306/CampList";
	/**
	* データベースのユーザー名
	*/
	private final static String USER = "root";
	/**
	* データベースのパスワード
	*/
	private final static String PASSWORD = "Mary28re";

	/**
	* データベースへの接続を取得して返します。
	*
	* @return データベース接続
	* @throws SQLException
	* @throws ClassNotFoundException
	*/
	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// JDBC ドライバのロード
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
