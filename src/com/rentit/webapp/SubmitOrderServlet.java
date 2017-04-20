package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentit.dao.PostedItemsDAO;
@WebServlet("/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderServlet() {
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
		String userId = session.getAttribute("userId").toString(); 

		Integer item_id,renter_id,owner_id;
		String reserve_from,reserve_to,order_status,order_date;
		Double total_cost,item_costperday;
		int order_id = 0;
		 
		item_costperday = Double.valueOf(request.getParameter("item_costperday"));
		item_id = Integer.parseInt(request.getParameter("item_id"));
		renter_id = Integer.parseInt(userId);
		owner_id = Integer.parseInt(request.getParameter("owner_id"));
		reserve_from = request.getParameter("reserve_from");
		reserve_to = request.getParameter("reserve_to");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		try{
		start.setTime(formatter.parse(reserve_from));
		end.setTime(formatter.parse(reserve_to));
		}catch(ParseException ex){
			
		}
		int days = (int) ((end.getTimeInMillis() - start.getTimeInMillis())/86400000);

		
		total_cost = item_costperday * days;
		order_status = "PAYMENT_PENDING";
		order_date = new Date().toString();
		try {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			order_id = PostedItemsDAO.insertNewOrder(item_id, 
					renter_id, owner_id, reserve_from, reserve_to, total_cost,order_status,order_date);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		if (order_id != 0)
		{
			request.setAttribute("order_id", order_id);
			request.setAttribute("total_cost", total_cost);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("PaymentInterface.jsp");
		    requestDispatcher.forward(request, response);
		}else
		{
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("RenterHomePage.jsp");
		    requestDispatcher.forward(request, response);
		}
		out.close();
	}

}
