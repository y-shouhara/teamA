package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LoginDAO;
import model.entity.LoginBean;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新規ユーザー登録画面の表示
		RequestDispatcher rd = request.getRequestDispatcher("register-user.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新規ユーザー登録の処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		//管理者か一般ユーザーかの識別（パスワードの先頭が管理者⇒管理者登録）
		int managerId = 0; //ユーザー：0　管理者：1
		String checkManager = pass.substring(0, 5); //先頭５文字を切り取り
		//先頭５文字が『kanri』の場合は処理を実行
		if ("kanri".equals(checkManager)) {
			managerId = 1;
			pass = pass.substring(5); //『kanri』を除いた文字列に変更
		}
		//LoginDAOを生成
		LoginDAO loginDAO = new LoginDAO();

		try {
			//重複しているユーザーIDがないか確認
			LoginBean distinctCheck = loginDAO.getLogin(userName);
			if (distinctCheck != null) {
				System.out.println("falseで来たよ");
				request.setAttribute("errorMsg", "既に使用されているユーザー名です。");
				//画面遷移設定
				RequestDispatcher rd = request.getRequestDispatcher("register-user.jsp");
				rd.forward(request, response);
			} else {
				//ソルトを生成
				String salt = loginDAO.getSalt();
				//ハッシュ化を実行
				String hashSalt = loginDAO.getHashSalt(salt, pass);
				//LoginBeanに登録用データの格納
				LoginBean loginBean = new LoginBean(userName, salt, hashSalt, managerId);
				//DBに新規ユーザー情報を登録
				int resultNum = loginDAO.insertLogin(loginBean);
				//画面遷移設定
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
