package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ReservationDAO;
import model.entity.ReservationBean;

/**
 * Servlet implementation class DeleteReservationServlet
 */
@WebServlet("/DeleteReservation")
public class DeleteReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteReservationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		int reservationId = Integer.parseInt(request.getParameter("reserveId"));
		System.out.println("確認画面へ");

		//DAOのインスタンス生成
		ReservationDAO reservationDao = new ReservationDAO();
		try {
			ReservationBean reservationBean = reservationDao.getDeleteReservation(reservationId);
			System.out.println(reservationBean.getCampName());
			request.setAttribute("reservationBean", reservationBean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//確認画面へ遷移
		RequestDispatcher rd = request.getRequestDispatcher("delete-reservation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("削除ボタンが押下されました");
		//リクエストパラメータを取得
		int reservationId = Integer.parseInt(request.getParameter("reserveId"));

		//DAOのインスタンス生成
		ReservationDAO reservationDao = new ReservationDAO();
		try {
			System.out.println("成功");
			//削除メソッドを呼び出し、削除情報を取得
			int deleteCount = reservationDao.deleteReservation(reservationId);

		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			System.out.println("失敗");
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		response.sendRedirect(request.getContextPath() + "/Confirmation");
	}

}
