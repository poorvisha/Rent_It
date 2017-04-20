/**International Technological University, SanJose
 * 
 */

package com.rentit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import com.rentit.valueobjects.UserVO;


public class RegisterDAO {
	private static ResultSet resultSet;

	public static UserVO enterUserCredentials( String firstName,
			 String lastName, String eMailId, String userPassword,
			String user_type,String is_google_user) throws ParseException {
		// TODO Auto-generated method stub
		    
		    boolean status = false;
		    Connection conn = null;  
	        PreparedStatement pst = null;  
	        PreparedStatement pst1 = null;
	        resultSet = null;  
	        UserVO uservo = null;
	        
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/rent_it"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	            
	            
	            pst=conn.prepareStatement(  
	            		"insert into user_details(first_name, last_name, email_id,password,user_type,is_google_user) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
	            		
	            		
	            pst.setString(1,firstName);
	            pst.setString(2,lastName);
	            pst.setString(3,eMailId);
	            pst.setString(4,userPassword);
	            /*pst.setString(5,addrLine1);
	            pst.setString(6,addrLine2);
	            pst.setString(7,city);
	            pst.setString(8,state);
	            pst.setString(9,country);
	            pst.setString(10,zipCode);
	            pst.setString(11,phone);*/
	            pst.setString(5,user_type);
	            pst.setString(6,is_google_user);
	            		
	            pst.executeUpdate(); 
	            		
	            resultSet = pst.getGeneratedKeys();
	                     if(resultSet != null && resultSet.next()){
	                         System.out.println("Generated user Id: "+resultSet.getInt(1));
	                         pst1 = conn  
	         	                    .prepareStatement("select * from user_details where user_id=?");  
	         	            pst1.setInt(1, resultSet.getInt(1));
	         	           resultSet = pst1.executeQuery();
	                         uservo = LoginDAO.buildUserVo(resultSet);
	                         status = true;
	                     }	
	}
	        catch (Exception e) {  
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
                if (pst1 != null) {  
	                try {  
	                    pst1.close();  
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
	        return uservo;  
	} 

	}
