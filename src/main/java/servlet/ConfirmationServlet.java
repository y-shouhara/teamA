package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ReservationDAO;
import model.entity.ReservationBean;

/**
 * Servlet implementation class ConfirmationServlet
 */
@WebServlet("/Confirmation")
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//予約確認画面へ遷移する処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//ログインユーザー名の取得
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("userName");
		//ReservationDAOのインスタンス生成
		ReservationDAO reservationDAO = new ReservationDAO();
		//指定ユーザー名の予約情報を取得
		List<ReservationBean> reservationBeanList = reservationDAO.getReservationList(loginId);
		//リクエストスコープの設定
		request.setAttribute("reservationBeanList", reservationBeanList);
		//画面遷移先の設定
		RequestDispatcher rd = request.getRequestDispatcher("confirmation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
