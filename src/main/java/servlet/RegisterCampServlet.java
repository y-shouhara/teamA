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
 * Servlet implementation class RegisterCampServlet
 */
@WebServlet("/RegisterCamp")
public class RegisterCampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCampServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//RequestDispatcher→サーブレットからJSPを表示するためのインターフェイス
		RequestDispatcher rd = request.getRequestDispatcher("register-camp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String campName = request.getParameter("campName");
		String location = request.getParameter("location");
		String tel = request.getParameter("tel");
		int charge = Integer.parseInt(request.getParameter("charge"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		CampBean campBean = new CampBean(campName, location, tel, charge, capacity);

		//CampDAOのインスタンス生成(DAOを使ってデータベースに登録)
		CampDAO campDAO = new CampDAO();
		try {
			//登録処理をするメソッドを呼び出す
			int resultNum = campDAO.RegistCampList(campBean);
			//画面遷移先の設定
			System.out.println("成功");
			response.sendRedirect(request.getContextPath() + "/CampList");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("失敗");
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}

}
