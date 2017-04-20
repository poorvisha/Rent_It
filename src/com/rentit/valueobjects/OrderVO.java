/**International Technological University, SanJose
 * 
 */
package com.rentit.valueobjects;

import java.util.Date;

/**
 * @author PoorvishaMuthusamy
 *
 */
public class OrderVO {
	
	private int order_id;
	private int item_id;
	private int renter_id;
	private int owner_id;
	private String reserve_from;
	private String reserve_to;
	private double total_cost;
	private String order_status;
	private String order_date;
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	private ItemsVO itemsvo;
	private String user_first_name;
	public String getUser_first_name() {
		return user_first_name;
	}
	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getRenter_id() {
		return renter_id;
	}
	public void setRenter_id(int renter_id) {
		this.renter_id = renter_id;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getReserve_from() {
		return reserve_from;
	}
	public void setReserve_from(String reserve_from) {
		this.reserve_from = reserve_from;
	}
	public String getReserve_to() {
		return reserve_to;
	}
	public void setReserve_to(String reserve_to) {
		this.reserve_to = reserve_to;
	}
	public double getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public ItemsVO getItemsvo() {
		return itemsvo;
	}
	public void setItemsvo(ItemsVO itemsvo) {
		this.itemsvo = itemsvo;
	}

}
