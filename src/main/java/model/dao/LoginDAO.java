package model.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import model.entity.LoginBean;

public class LoginDAO {
	//新規登録時にユーザー名に重複がないか確認
	public LoginBean getLogin(String userName) throws ClassNotFoundException, SQLException {
		LoginBean loginBean = null;
		//SQL文の設定
		String sql = "SELECT login_id,salt,hash_salt,manager_id FROM m_login WHERE login_id=?";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, userName);
			//SQLの実行
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				String loginId = res.getString("login_id");
				String salt = res.getString("salt");
				String hashSalt = res.getString("hash_salt");
				int managerId = res.getInt("manager_id");
				loginBean = new LoginBean(loginId, salt, hashSalt, managerId);
			}
		}
		return loginBean;
	}

	//ソルトを発行
	public String getSalt() {
		//ソルトのベースとなる変数を生成
		byte[] byteSalt = new byte[16];
		//デフォルトの乱数アルゴリズムを実装する、セキュリティ保護された乱数ジェネレータ(RNG)を構築
		SecureRandom random = new SecureRandom();
		//.nextBytes(byte[])：指定したバイト数の乱数バイト数を生成
		random.nextBytes(byteSalt);
		String salt = Base64.getEncoder().encodeToString(byteSalt);
		return salt;
	}

	//ソルト＋ハッシュでパスワードを暗号化
	public String getHashSalt(String salt, String pass) throws NoSuchAlgorithmException {
		//getInstance("要求するアルゴリズムの名前")で
		//指定されたアルゴリズムを実装するMessageDigestオブジェクトを返す
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		//.update(byte[])：指定されたバイト配列を使用してダイジェストを更新
		md.update(salt.getBytes());
		//String.getBytes()：結果のバイト配列を返す
		md.update(pass.getBytes());
		//.digest()：ハッシュ計算を行い、ハッシュ地に対するバイト・データの配列を返す
		byte[] hashBytes = md.digest();
		//Base64.getEncoder()：基本型base64を使用してエンコードする
		//.encodeToString(byte[])：Base64でエンコードされた文字を格納するString返す
		String hashSalt = Base64.getEncoder().encodeToString(hashBytes);
		return hashSalt;
	}

	//データベースに新規ユーザー情報を登録
	public int insertLogin(LoginBean loginBean) throws ClassNotFoundException, SQLException {
		int resultNum = 0;
		//SQL文の設定
		String sql = "INSERT INTO m_login(login_id,salt,hash_salt,manager_id) VALUES (?,?,?,?)";
		//データベースに接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダに値を設定
			pstmt.setString(1, loginBean.getLoginId());
			pstmt.setString(2, loginBean.getSalt());
			pstmt.setString(3, loginBean.getHashSolt());
			pstmt.setInt(4, loginBean.getManagerId());
			//SQLの実行
			resultNum = pstmt.executeUpdate();
		}
		return resultNum;
	}

}
