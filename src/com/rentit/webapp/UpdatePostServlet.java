package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rentit.dao.PostedItemsDAO;

@WebServlet("/UpdatePostServlet")
@MultipartConfig
public class UpdatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String userId = session.getAttribute("userId").toString();
		boolean status = false;

		String item_name, item_description, item_image, zipcode, item_available_from, item_available_to,
				item_costperday, item_category, item_postedby, item_status, item_posteddate, pickup_addr, pickup_city,
				pickup_state, contact,item_id;
		item_posteddate = "";
		item_id = request.getParameter("item_id");
		Date currentDate = new Date();
		item_name = request.getParameter("item_name");
		item_description = request.getParameter("item_description");
		item_image = request.getParameter("item_image");
		zipcode = request.getParameter("zipcode");
		item_available_from = request.getParameter("available_from");
		item_available_to = request.getParameter("available_to");
		item_costperday = request.getParameter("daycost");
		item_category = request.getParameter("item_category");
		item_postedby = userId;
		item_status = "true";
		pickup_addr = request.getParameter("addressline1");
		pickup_city = request.getParameter("city");
		pickup_state = request.getParameter("state");
		contact = request.getParameter("contact");
		Part filePart = request.getPart("photo");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			item_posteddate = sdf.format(currentDate);
			Date item_available_from_date = new SimpleDateFormat("yyyy-MM-dd").parse(item_available_from);
			Date item_available_to_date = new SimpleDateFormat("yyyy-MM-dd").parse(item_available_to);

			if (item_available_from_date.before(currentDate)
					|| item_available_to_date.before(item_available_from_date)) {
				//throw new Exception("Choose a valid date");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			status = PostedItemsDAO.updatePost(item_id,item_name, item_description, item_image, zipcode, item_available_from,
					item_available_to, item_costperday, item_category, item_postedby, item_status, item_posteddate,
					pickup_addr, pickup_city, pickup_state, filePart, contact);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status == true) {
			response.sendRedirect("home");
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("PostItem.jsp");
			requestDispatcher.forward(request, response);
		}
		out.close();
	}

}
