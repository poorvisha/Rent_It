package com.rentit.webapp;
/**
 * International Technological University, San Jose 
 * @author poorvisha
 * created on : 03/18/2016
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/imageservlet/*")
public class FetchImageServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;




 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  * edited on : 03/18/2016
  * @author Poorvisha Muthusamy
  */

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  response.setContentType("image/jpeg");
	  Connection conn = null;
	  PreparedStatement pst = null;
	  ResultSet resultSet = null;
	  String itemId = request.getParameter("item_id");
	  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
	  String driver = "com.mysql.jdbc.Driver";
	  String userName = "root";
	  String password = "root";
	  try {
		  itemId = request.getPathInfo().substring(1);
		   Class.forName(driver).newInstance();
		   conn = DriverManager
		    .getConnection(DB_URL, userName, password);
		   pst = conn
		    .prepareStatement("select * from post_items where item_id=?");
		   pst.setString(1, itemId);
		
		   resultSet = pst.executeQuery();
		   if (resultSet.next()) {
			    byte[] content = resultSet.getBytes("item_image");
			    response.setContentType("image/jpeg");
			    response.setContentLength(content.length);
			    response.getOutputStream().write(content);
		   } else {
			   response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
		   }
	  }catch (Exception e) {
		  System.out.println(e);
	  } finally {
		   if (conn != null) {
			    try {
			    	conn.close();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
		   }
		   if (pst != null) {
			    try {
			    	pst.close();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
		   }
		   if (resultSet != null) {
			    try {
			    	resultSet.close();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
		   }
	  }	
	}
}