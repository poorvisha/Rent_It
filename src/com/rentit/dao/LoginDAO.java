/**International Technological University, SanJose
 * 
 */

package com.rentit.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rentit.valueobjects.UserVO;



public class LoginDAO { 
    
    public static UserVO validate(String eMail, String loginPassword,String status) {          
       
        Connection conn = null;  
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null; 
        ResultSet rs = null;
        ResultSet rs1 = null;
        
        final String DB_URL="jdbc:mysql://127.0.0.1:3306/rent_it"; 
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";
        UserVO userVo = null;
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(DB_URL, userName, password);  
            if("success".equalsIgnoreCase(status)){
            	pst = conn  
	                    .prepareStatement("select * from user_details where email_id=?");  
	            pst.setString(1, eMail);
            }else{
            	pst = conn  
	                    .prepareStatement("select * from user_details where email_id=?  and password=? ");  
	            pst.setString(1, eMail);  
	            pst.setString(2, loginPassword);
            }
  
            rs = pst.executeQuery();
         
            return buildUserVo(rs);
  
        } catch (Exception e) {  
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
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return userVo;  
    }
    
    public static UserVO buildUserVo(ResultSet rs){
    	try{
    		UserVO userVo = null;
	    	if(rs != null && rs.next()){
	    		userVo = new UserVO(); 
	        	int user_id = rs.getInt("user_id");
	        	userVo.setUser_id(user_id);
	    		userVo.setFirst_name(rs.getString("first_name"));
	    		userVo.setLast_name(rs.getString("last_name"));
	    		userVo.setEmail(rs.getString("email_id"));
	    		userVo.setPassword(rs.getString("password"));
	    		/*userVo.setAddress_line1(rs.getString("address_line1"));
	    		userVo.setAddress_line2(rs.getString("address_line2"));
	    		userVo.setCity(rs.getString("city"));
	    		userVo.setState(rs.getString("state"));
	    		userVo.setCountry(rs.getString("country"));
	    		userVo.setZipcode(rs.getString("zipcode"));	 
	    		userVo.setPhone(rs.getString("phone"));	*/
	    		userVo.setUser_type(rs.getString("user_type"));
	    		return userVo;
	    	}         
        }catch(Exception ex){
        	
        }
		return null;
    }
    
    
}
