/**International Technological University, SanJose
 * 
 */
package com.rentit.valueobjects;

import java.util.List;

/**
 * @author PoorvishaMuthusamy
 *
 */
public class UserVO {
 
	public int user_id;
	public String first_name;
	public String last_name;
    public String email;
    public String password;
    public String user_type;
    private int current_order_id;
    List<ItemsVO> itemsVOList;

	
    public List<ItemsVO> getItemsVOList() {
		return itemsVOList;
	}
	public void setItemsVOList(List<ItemsVO> itemsVOList) {
		this.itemsVOList = itemsVOList;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public int getCurrent_order_id() {
		return current_order_id;
	}
	public void setCurrent_order_id(int current_order_id) {
		this.current_order_id = current_order_id;
	}

}
