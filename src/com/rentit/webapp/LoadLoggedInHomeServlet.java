/**International Technological University, SanJose
 * 
 */
package com.rentit.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentit.dao.LoginDAO;
import com.rentit.dao.PostedItemsDAO;
import com.rentit.valueobjects.ItemsVO;
import com.rentit.valueobjects.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/home")
public class LoadLoggedInHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadLoggedInHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   response.setContentType("text/html"); 
		   PrintWriter out = response.getWriter();    
		   RequestDispatcher rd = null;
	      
	        
	        HttpSession session = request.getSession(); 
	        if(session!=null)  {
	        	UserVO userVo = (UserVO) session.getAttribute("userVo");
	        	         	   
	        	if(null != userVo)
	        	{
	        		session.setAttribute("userVo", userVo); 
	        		session.setAttribute("userId", userVo.user_id); 
	        		session.setAttribute("loggedin_status", true);
	        		session.setAttribute("first_name", userVo.getFirst_name());
	        		session.setAttribute("user_type", userVo.user_type);

	        		if(userVo.user_type.equals("Owner"))
	        		{
	                    List<ItemsVO> itemsVOList = PostedItemsDAO.fetchPostedItemsForOwner(userVo);
	                    userVo.setItemsVOList(itemsVOList);
	                    //request.setAttribute("postedItemsList", itemsVOList);
	                    session.setAttribute("homePageList", itemsVOList);
	        			rd = request.getRequestDispatcher("OwnerHomePage.jsp");
	        		}
	        		else
	        		{
	        			List<ItemsVO> itemsVOList = PostedItemsDAO.fetchPostedItemsForRenter(userVo);
	                    userVo.setItemsVOList(itemsVOList);
	                    //request.setAttribute("postedItemsList", itemsVOList);
	                    session.setAttribute("homePageList", itemsVOList);
	        		rd = request.getRequestDispatcher("RenterHomePage.jsp");
	        		}
	        		
					rd.forward(request, response);
	        	}else
	        	{
	        		rd = request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);
	        	}
	        }
	   out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
