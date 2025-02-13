package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ReservationDAO;

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
		//前の週、次の週ボタンを押下したかで分岐
		if (request.getParameter("dateChange") != null) {
			String dateChange = request.getParameter("dateChange");
			targetDay = LocalDate.parse(request.getParameter("targetDay"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//前の週、次の週ボタンによって処理を分岐
			if ("previous".equals(dateChange)) {
				//前の週ボタンを押下した場合
				targetDay = targetDay.minusDays(7);
			} else if ("next".equals(dateChange)) {
				//次の週ボタンを押下した場合
				targetDay = targetDay.plusDays(7);
			}
		}
		//カレンダーの表示用処理----------------------------------------------------------
		int numDay = 7; //表示するカラム数
		List<String> yearList = new ArrayList<String>(); //年のリスト
		List<String> monthList = new ArrayList<String>(); //月のリスト
		List<String> dateList = new ArrayList<String>(); //日付のリスト
		List<String> dayOfWeekList = new ArrayList<String>(); //曜日のリスト
		//日付のフォーマットを指定
		SimpleDateFormat sdf_year = new SimpleDateFormat("YYYY");
		SimpleDateFormat sdf_month = new SimpleDateFormat("M");
		SimpleDateFormat sdf_day = new SimpleDateFormat("d");
		SimpleDateFormat sdf_DayOfWeek = new SimpleDateFormat("(E)");
		int monthCount = 1;
		int yearCount = 0;
		//日付の取得
		for (int i = 0; i < numDay; i++) {
			//月のリストが空であれば年、月の値を代入
			if (monthList == null || monthList.isEmpty()) {
				yearList.add(String.valueOf(targetDay.getYear()));
				monthList.add(String.valueOf(targetDay.getMonthValue()));
			}

			if (i >= 1 && monthList.size() < 2) {
				if (monthList.get(0).equals(String.valueOf(targetDay.getMonthValue()))) {
					monthCount++;
				} else {
					monthList.add(String.valueOf(targetDay.getMonthValue()));
					if (!(yearList.get(0).equals(String.valueOf(targetDay.getYear())))) {
						yearList.add(String.valueOf(targetDay.getYear()));
						yearCount = monthCount;
					}
				}
			}
			//日付と曜日の代入
			dateList.add(String.valueOf(targetDay.getDayOfMonth()));
			dayOfWeekList.add(String.valueOf(targetDay.getDayOfWeek()));
			//次の繰り返し処理の為に、日付を１日進める
			targetDay = targetDay.plusDays(1);
		}
		//月ごとに表示するカラム数を設定
		int yearFirstColSpan = numDay - yearCount;
		int yearSecondColSpan = yearCount;
		int monthFirstColSpan = monthCount;
		int monthSecondColSpan = numDay - monthCount;

		System.out.println(yearFirstColSpan);
		System.out.println(yearSecondColSpan);
		System.out.println(monthFirstColSpan);
		System.out.println(monthSecondColSpan);

		//ReservationDAOのインスタンス生成
		ReservationDAO reservationDAO = new ReservationDAO();
		//キャンプ場名の予約情報を取得
		//		try {
		//			List<ReservationBean> reservationBeanList = reservationDAO.getAvailability(campName, targetDay);
		//			//リクエストスコープの設定
		//			request.setAttribute("reservationBeanList", reservationBeanList);
		request.setAttribute("targetDay", targetDay);
		request.setAttribute("campName", campName);
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		request.setAttribute("dateList", dateList);
		request.setAttribute("dayOfWeekList", dayOfWeekList);
		request.setAttribute("yearFirstColSpan", yearFirstColSpan);
		request.setAttribute("yearSecondColSpan", yearSecondColSpan);
		request.setAttribute("monthFirstColSpan", monthFirstColSpan);
		request.setAttribute("monthSecondColSpan", monthSecondColSpan);
		//画面遷移先の設定
		RequestDispatcher rd = request.getRequestDispatcher("reservation.jsp");
		rd.forward(request, response);
		//		} catch (ClassNotFoundException | SQLException e) {
		//			// TODO 自動生成された catch ブロック
		//			e.printStackTrace();
		//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//予約の登録処理
	}

}
