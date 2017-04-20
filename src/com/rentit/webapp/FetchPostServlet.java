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

import com.rentit.dao.PostedItemsDAO;
import com.rentit.valueobjects.ItemsVO;
import com.rentit.valueobjects.OrderVO;
@WebServlet("/FetchPostServlet")
public class FetchPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		String userId = session.getAttribute("userId").toString();  
		
        RequestDispatcher rd = null;
        String itemId;
        //itemId = request.getPathInfo().substring(1);
        itemId = request.getParameter("item_id");
        List<ItemsVO> itemsVOList = PostedItemsDAO.getItemDetailsForItemId(Integer.parseInt(itemId));
        request.setAttribute("itemsVOList", itemsVOList);
        rd = request.getRequestDispatcher("UpdatePost.jsp");
		rd.forward(request, response);
	}

}
