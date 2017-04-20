package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentit.dao.PostedItemsDAO;
@WebServlet("/UpdatePaymentServlet")
public class UpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePaymentServlet() {
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
		boolean status = false;

		Integer order_id = Integer.valueOf(request.getParameter("order_id"));
		String card_number = request.getParameter("cardNumber");
		String month = request.getParameter("expityMonth");
		String year = request.getParameter("expityYear");
		String card_holder_name = request.getParameter("card_holder_name");
		try {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			status = PostedItemsDAO.updateOrder(card_holder_name, year, month, card_number, order_id);
			
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		if (!status)
		{
			request.setAttribute("order_id", order_id);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("PaymentInterface.jsp");
		    requestDispatcher.forward(request, response);
		}else
		{
			response.sendRedirect("HistoryServlet");
		}
		out.close();
	}

}
