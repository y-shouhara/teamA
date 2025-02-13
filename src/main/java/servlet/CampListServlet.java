package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CampDAO;
import model.entity.CampBean;

/**
 * Servlet implementation class CampListServlet
 */
@WebServlet("/CampList")
public class CampListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CampListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//キャンプ場一覧の表示

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 検索の絞り込み処理 ＠近藤

		// Campリストを格納する変数
		List<CampBean> CampList = null;

		// TweetDAOクラスのインスタンス生成
		CampDAO dao = new CampDAO();

		try {
			// CampDAOクラスのgetCampListメソッド呼び出し、Campリスト取得
			CampList = dao.getCampList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// リクエストスコープにtweetリストをセット
		request.setAttribute("CampList", CampList);

		// camp-list.jspのキャンプ場一覧画面へ転送
		RequestDispatcher rd = request.getRequestDispatcher("camp-list.jsp");
		rd.forward(request, response);
	}

}