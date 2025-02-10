package model.dao;

import model.entity.LoginBean;

public class LoginDAO {
	//新規登録時にユーザー名に重複がないか確認
	public boolean checkLoginId(String userName) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	//ソルトを発行
	public String getSalt() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	//ソルト＋ハッシュでパスワードを暗号化
	public String getHashSalt(String salt, String pass) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	//データベースに新規ユーザー情報を登録
	public int insertLogin(LoginBean loginBean, String pass) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	//ユーザー名から情報を取得
	public LoginBean getLogin(String userName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
