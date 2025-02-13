package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
		//ReservationDAOのインスタンス生成
		ReservationDAO reservationDAO = new ReservationDAO();
		try {
			//キャンプ場名の予約情報を取得
			List<ReservationBean> reservationBeanList = reservationDAO.getAvailability(campName, targetDay);
			//空き情報を格納用
			HashMap<String, String> availabilityMap = new HashMap<String, String>();

			//カレンダーの表示用処理----------------------------------------------------------
			int numDay = 7; //表示するカラム数
			List<String> yearList = new ArrayList<String>(); //年のリスト
			List<String> monthList = new ArrayList<String>(); //月のリスト
			List<String> dateList = new ArrayList<String>(); //日付のリスト
			List<String> allDateList = new ArrayList<String>(); //年月日のリスト
			HashMap<String, String> dayOfWeekMap = new HashMap<>(); //曜日のリスト
			int monthCount = 1;
			int yearCount = numDay;
			//日付の取得
			for (int i = 0; i < numDay; i++) {
				//月のリストが空であれば年、月の値を代入
				if (monthList == null || monthList.isEmpty()) {
					yearList.add(String.valueOf(targetDay.getYear()));
					monthList.add(String.valueOf(targetDay.getMonthValue()));
				}
				//
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
				//日付の代入
				String strDate = String.valueOf(targetDay.getDayOfMonth()); //Localdate⇒String
				dateList.add(strDate);
				//曜日の変換と代入
				DayOfWeek dayOfWeek = targetDay.getDayOfWeek();
				String strDayOfWeek = "(" + String.valueOf(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.JAPANESE))
						+ ")";
				dayOfWeekMap.put(strDate, strDayOfWeek);
				//変換なしの年月日を格納
				allDateList.add(String.valueOf(targetDay));
				//DBの予約上
				for (ReservationBean reservationBean : reservationBeanList) {
					if (targetDay.equals(reservationBean.getReserveDate())) {
						availabilityMap.put(String.valueOf(reservationBean.getReserveDate()), "◎");
					}
				}
				//次の繰り返し処理の為に、日付を１日進める
				targetDay = targetDay.plusDays(1);
			}
			//月ごとに表示するカラム数を設定
			int yearFirstColSpan = yearCount;
			int yearSecondColSpan = numDay - yearCount;
			int monthFirstColSpan = monthCount;
			int monthSecondColSpan = numDay - monthCount;
			//targetDayを日付取得で加算された分だけ戻す
			targetDay = targetDay.minusDays(numDay);
			//リクエストスコープの設定
			request.setAttribute("availabilityMap", availabilityMap);
			request.setAttribute("targetDay", targetDay);
			request.setAttribute("campName", campName);
			request.setAttribute("allDateList", allDateList);
			request.setAttribute("yearList", yearList);
			request.setAttribute("monthList", monthList);
			request.setAttribute("dateList", dateList);
			request.setAttribute("dayOfWeekMap", dayOfWeekMap);
			request.setAttribute("yearFirstColSpan", yearFirstColSpan);
			request.setAttribute("yearSecondColSpan", yearSecondColSpan);
			request.setAttribute("monthFirstColSpan", monthFirstColSpan);
			request.setAttribute("monthSecondColSpan", monthSecondColSpan);
			//画面遷移先の設定
			RequestDispatcher rd = request.getRequestDispatcher("reservation.jsp");
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
	}

}
