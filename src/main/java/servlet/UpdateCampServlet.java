package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CampDAO;
import model.entity.CampBean;

/**
 * Servlet implementation class UpdateCampServlet
 */
@WebServlet("/UpdateCamp")
public class UpdateCampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCampServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//キャンプ場情報編集画面の表示処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String campName = request.getParameter("campName");
		CampBean campBean = new CampBean();
		campBean.setCampName(campName);
		//CampDAOのインスタンス生成
		CampDAO campDAO = new CampDAO();
		//既存登録情報の取得
		try {
			campBean = campDAO.getCampListByCampName(campBean);
			//リクエストスコープの設定
			request.setAttribute("campBean", campBean);
			//画面遷移先の設定
			RequestDispatcher rd = request.getRequestDispatcher("update-camp.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//編集登録処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String campName = request.getParameter("campName");
		String location = request.getParameter("location");
		String tel = request.getParameter("tel");
		int charge = Integer.parseInt(request.getParameter("charge"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		CampBean campBean = new CampBean(campName, location, tel, charge, capacity);
		//CampDAOのインスタンス生成
		CampDAO campDAO = new CampDAO();
		try {
			//UPDATEの実行
			int resultNum = campDAO.updateCampList(campBean);
			//画面遷移先の設定
			response.sendRedirect(request.getContextPath() + "/CampList");
		} catch (ClassNotFoundException | SQLException e) {
			response.sendRedirect("error.jsp");
		}

	}

}
