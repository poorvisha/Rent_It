package com.rentit.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentit.dao.PostedItemsDAO;
import com.rentit.valueobjects.OrderVO;
@WebServlet("/ItemDetailServlet")
public class FetchItemDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        String itemId;
        //itemId = request.getPathInfo().substring(1);
        itemId = request.getParameter("item_id");
        List<OrderVO> orderVOList = PostedItemsDAO.getOrderDetailsForItem(itemId);
        request.setAttribute("ownerOrderDetails", orderVOList);
        rd = request.getRequestDispatcher("OwnerItemOrders.jsp");
		rd.forward(request, response);
	}

}
