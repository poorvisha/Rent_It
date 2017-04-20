/**International Technological University, SanJose
 * 
 */

package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		String firstName,lastName,email,password,addrLine1,addrLine2,city,state,country,zipCode,phone = null,user_type,is_google_user;
		boolean status = false;
		UserVO uservo = null;
		
		firstName = request.getParameter("first_name");
		lastName = request.getParameter("last_name");
		email = request.getParameter("email");
		password = request.getParameter("password");
		/*addrLine1 = request.getParameter("addressline1");
		addrLine2 = request.getParameter("addressline2");
		city = request.getParameter("city");
		state = request.getParameter("state");
		country = request.getParameter("country");
		zipCode = request.getParameter("zipcode");
		phone = request.getParameter("phone");*/
		user_type = request.getParameter("user_type");
		is_google_user = request.getParameter("is_google_user");
		if("true".equalsIgnoreCase(is_google_user)){
			password = "";
		}
		try {
			uservo = RegisterDAO.enterUserCredentials(firstName, 
			 lastName, email, password,user_type,is_google_user);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		if (null != uservo)
		{
			session.setAttribute("userVo", uservo); 
    		session.setAttribute("userId", uservo.user_id); 
    		session.setAttribute("loggedin_status", true);
    		session.setAttribute("first_name", uservo.getFirst_name());
    		session.setAttribute("user_type", uservo.user_type);
			if(uservo.user_type.equals("Owner"))
    		{
                List<ItemsVO> itemsVOList = PostedItemsDAO.fetchPostedItemsForOwner(uservo);
                uservo.setItemsVOList(itemsVOList);
                //request.setAttribute("postedItemsList", itemsVOList);
                session.setAttribute("postedItemsList", itemsVOList);
                requestDispatcher = request.getRequestDispatcher("OwnerHomePage.jsp");
    		}
    		else
    		{
    			List<ItemsVO> itemsVOList = PostedItemsDAO.fetchPostedItemsForRenter(uservo);
    			uservo.setItemsVOList(itemsVOList);
                //request.setAttribute("postedItemsList", itemsVOList);
                session.setAttribute("postedItemsList", itemsVOList);
                requestDispatcher = request.getRequestDispatcher("RenterHomePage.jsp");
    		}
    		
			requestDispatcher.forward(request, response);
		}else
		{
			requestDispatcher = request.getRequestDispatcher("Register.jsp");
		    requestDispatcher.forward(request, response);
		}
		out.close();
	}

}
