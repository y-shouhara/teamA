package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ReservationDAO;
import model.entity.ReservationBean;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//予約画面の表示処理
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String campName = request.getParameter("campName");
		//現在日付の取得
		LocalDate targetDay = LocalDate.now();
		//ReservationDAOのインスタンス生成
		ReservationDAO reservationDAO = new ReservationDAO();
		//キャンプ場名の予約情報を取得
		List<ReservationBean> reservationBeanList = reservationDAO.getAvailability(campName, targetDay);
		//リクエストスコープの設定
		request.setAttribute("reservationBeanList", reservationBeanList);
		//画面遷移先の設定
		RequestDispatcher rd = request.getRequestDispatcher("reservation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//予約の登録処理
	}

}
