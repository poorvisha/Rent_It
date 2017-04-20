package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentit.dao.PostedItemsDAO;
import com.rentit.valueobjects.ItemsVO;
import com.rentit.valueobjects.OrderVO;
import com.rentit.valueobjects.UserVO;
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		String userId = session.getAttribute("userId").toString(); 
		UserVO uservo = (UserVO) session.getAttribute("userVo");
		String userType = session.getAttribute("user_type").toString();
		
		if("renter".equalsIgnoreCase(userType)){
			List<OrderVO> orderVOList = PostedItemsDAO.getOrderDetailsForRenter(userId);
			request.setAttribute("orderItemsList", orderVOList);
			rd = request.getRequestDispatcher("RenterHistory.jsp");
		}else if("owner".equalsIgnoreCase(userType)){
			List<ItemsVO> itemVOList = PostedItemsDAO.fetchPostedItemsForOwner(uservo);
			request.setAttribute("itemVOList", itemVOList);
			rd = request.getRequestDispatcher("OwnerHistory.jsp");
		}
		rd.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
