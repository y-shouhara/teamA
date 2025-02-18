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
import javax.servlet.http.HttpSession;

import model.dao.LoginDAO;
import model.entity.LoginBean;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/Auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログインしていない場合はログイン画面へ遷移する処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String transition = request.getParameter("transition");
		if (request.getParameter("campName") != null) {
			request.setAttribute("campName", request.getParameter("campName"));
		}
		//ログインされているか確認（セッションの有無）
		HttpSession session = request.getSession(false);
		if (session.getAttribute("userName") == null) {
			request.setAttribute("nextServlet", transition);
			transition = "login.jsp";
		}
		//遷移先の設定
		RequestDispatcher rd = request.getRequestDispatcher(transition);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログイン認証を行う処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		String campName = "";
		String nextServlet = "";
		if (request.getParameter("campName") != null) {
			campName = request.getParameter("campName");
			request.setAttribute("campName", campName);
		}
		if (request.getParameter("nextServlet") != null) {
			nextServlet = request.getParameter("nextServlet");
			request.setAttribute("nextServlet", nextServlet);
		}

		//LoginDAOのインスタンス生成
		LoginDAO loginDAO = new LoginDAO();
		try {
			//ユーザー名が存在しているか確認
			LoginBean loginBean = loginDAO.getLogin(userName);
			//ユーザー名が存在していない場合はログイン画面へ遷移
			if (loginBean == null) {
				request.setAttribute("errorMsg", "入力されたユーザー名は存在しません。");
				//画面遷移設定
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {
				//リクエストパラメータのパスワードをソルト＋ハッシュに変換
				String salt = loginBean.getSalt();
				String resultHashSalt = loginDAO.getHashSalt(salt, pass);
				//DBとリクエストのソルト＋ハッシュが一致しているか確認
				if (resultHashSalt.equals(loginBean.getHashSolt())) {
					//セッションの作成
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("managerId", loginBean.getManagerId());
				} else {
					//エラーメッセージの設定
					request.setAttribute("errorMsg", "パスワードが違います。");
					//画面遷移設定
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}
			//画面遷移設定
			//キャンプ場予約画面（campName)
			//個人予約状況（なし）
			if ("".equals(nextServlet)) {
				response.sendRedirect(request.getContextPath() + "/CampList");
			} else {
				response.sendRedirect(request.getContextPath() + "/" + nextServlet);
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
