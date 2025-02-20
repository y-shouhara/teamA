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
		
		//リクエストパラメータの取得
		String campName = request.getParameter("campName");
		//インスタンスの生成
		CampDAO campDao = new CampDAO();
		CampBean campBean = new CampBean();
		campBean.setCampName(campName);
		
		try {
			CampBean camp = campDao.getCampListByCampName(campBean); 
	        request.setAttribute("camp", camp); // リクエストスコープに格納
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		RequestDispatcher rd = request.getRequestDispatcher("delete-camp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータがあるので、エンコーディング方法指定
   		request.setCharacterEncoding("UTF-8");
   		System.out.println("削除ボタンが押下されました");
   		//リクエストパラメータを取得
   		String campName =request.getParameter("campName");
   	//DAOのインスタンス生成
   		CampDAO campDao = new CampDAO();
   		try {
   			//削除メソッドを呼び出し、削除情報を取得
   			int deleteCount = campDao.deleteCampByCampName(campName);
   			System.out.println("成功");
   			System.out.println(deleteCount);
   		}catch (NumberFormatException | ClassNotFoundException | SQLException e) {
   			e.printStackTrace();
			response.sendRedirect("error.jsp");
			System.out.println("失敗");
		}
   		response.sendRedirect(request.getContextPath() + "/CampList");
	}

}
