/**International Technological University, SanJose
 * 
 */

package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentit.dao.PostedItemsDAO;
import com.rentit.dao.RegisterDAO;
import com.rentit.valueobjects.ItemsVO;
import com.rentit.valueobjects.UserVO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/ReserveItemServlet")
public class ReserveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		RequestDispatcher requestDispatcher = null;
		
		String item_id;
		
		item_id = request.getParameter("item_id");
		request.setAttribute("item_id", item_id);
		List<ItemsVO> itemsVOList = PostedItemsDAO.getItemDetailsForItemId(Integer.parseInt(item_id));
        request.setAttribute("itemsVOList", itemsVOList);
		requestDispatcher = request.getRequestDispatcher("ReserveItem.jsp");
		requestDispatcher.forward(request, response);
		out.close();
	}

}
