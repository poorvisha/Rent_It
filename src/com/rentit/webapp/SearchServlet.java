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
@WebServlet("/search/*")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		 
	        String searchKeyword=request.getParameter("keyword");    
	        String zip=request.getParameter("zip"); 
	        
	        RequestDispatcher rd = null;	        
	        HttpSession session = request.getSession();

            List<ItemsVO> itemsVOList = PostedItemsDAO.fetchItemsForSearch(searchKeyword,zip);
            //request.setAttribute("postedItemsList", itemsVOList);
            session.setAttribute("searchItemList", itemsVOList);
			rd = request.getRequestDispatcher("Search.jsp");
			rd.forward(request, response);
	        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
