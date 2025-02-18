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

/**
 * Servlet implementation class DeleteCampServlet
 */
@WebServlet("/DeleteCamp")
public class DeleteCampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCampServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータがあるので、エンコーディング方法指定
   		request.setCharacterEncoding("UTF-8");
   		System.out.println("削除ボタンが押下されました");
   		//リクエストパラメータを取得
   		String strCampName =request.getParameter("campName");
   	//DAOのインスタンス生成
   		CampDAO campDao = new CampDAO();
   		try {
   			//int型に変換
   			String campName = strCampName;
   			//削除メソッドを呼び出し、削除件数を取得
   			int deleteCount = campDao.deleteCampByCampName(campName);
   		}catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
   	//campListサーブレットにリクエストを転送(dopost)
   			//postリクエスト転送なのでcampListのdopostメソッドが起動される。
   			RequestDispatcher rd = request.getRequestDispatcher("delete-camp.jsp");
   			rd.forward(request, response);
	}

}
