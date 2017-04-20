/**International Technological University, SanJose
 * 
 */
package com.rentit.valueobjects;

import java.util.Date;

/**
 * @author PoorvishaMuthusamy
 *
 */
public class ItemsVO{
	public int item_id;	
	public String item_name;
	public String item_description;
	public String item_image_url;
	public String item_zipcode ;
	public Date item_available_from;
	public Date item_available_to;
	public double item_costperday;
	public String item_category;
	public int item_postedby;
	public String item_status;
	public Date item_posteddate;
	public String pickup_address;
	public String pickup_city;
	public String pickup_state;
	public String user_name;
	public String contact;
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_image_url() {
		return item_image_url;
	}
	public void setItem_image_url(String item_image_url) {
		this.item_image_url = item_image_url;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getItem_zipcode() {
		return item_zipcode;
	}
	public void setItem_zipcode(String item_zipcode) {
		this.item_zipcode = item_zipcode;
	}
	public Date getItem_available_from() {
		return item_available_from;
	}
	public void setItem_available_from(Date item_available_from) {
		this.item_available_from = item_available_from;
	}
	public Date getItem_available_to() {
		return item_available_to;
	}
	public void setItem_available_to(Date item_available_to) {
		this.item_available_to = item_available_to;
	}
	public double getItem_costperday() {
		return item_costperday;
	}
	public void setItem_costperday(double item_costperday) {
		this.item_costperday = item_costperday;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public int getItem_postedby() {
		return item_postedby;
	}
	public void setItem_postedby(int item_postedby) {
		this.item_postedby = item_postedby;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	public Date getItem_posteddate() {
		return item_posteddate;
	}
	public void setItem_posteddate(Date item_posteddate) {
		this.item_posteddate = item_posteddate;
	}
	public String getPickup_address() {
		return pickup_address;
	}
	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}
	public String getPickup_city() {
		return pickup_city;
	}
	public void setPickup_city(String pickup_city) {
		this.pickup_city = pickup_city;
	}
	public String getPickup_state() {
		return pickup_state;
	}
	public void setPickup_state(String pickup_state) {
		this.pickup_state = pickup_state;
	}
}
