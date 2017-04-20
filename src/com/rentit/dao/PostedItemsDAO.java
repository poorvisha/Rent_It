package com.rentit.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rentit.valueobjects.ItemsVO;
import com.rentit.valueobjects.OrderVO;
import com.rentit.valueobjects.UserVO;

import javax.servlet.http.Part;

public class PostedItemsDAO {

	final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String password = "root";

	private static ResultSet rs;

	public static List<ItemsVO> fetchItemsForSearch(String keyword, String filterBy) {
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;

		List<ItemsVO> itemsVOList = new ArrayList<ItemsVO>();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			if (null == keyword && null == filterBy) {
				pst = conn.prepareStatement("select * from post_items");
			} else if (null != keyword && null != filterBy) {
				pst = conn.prepareStatement("select * from post_items where item_name like ? and item_zipcode=?");
				pst.setString(1, "%" + keyword + "%");
				pst.setString(2, filterBy);
			} else if (null != keyword) {
				pst = conn.prepareStatement("select * from post_items where item_name like ? ");
				pst.setString(1, "%" + keyword + "%");
			} else if (null != filterBy) {
				pst = conn.prepareStatement("select * from post_items where item_zipcode=?");
				pst.setString(1, filterBy);
			}
			rs = pst.executeQuery();
			itemsVOList = buildResponseFromResultSet(rs);
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
		}
		return itemsVOList;

	}

	public static List<ItemsVO> fetchPostedItemsForOwner(UserVO userVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;

		List<ItemsVO> itemsVOList = new ArrayList<ItemsVO>();
		try {
			int user_id = userVO.getUser_id();
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			pst = conn.prepareStatement("select * from post_items where item_postedby=?");
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			itemsVOList = buildResponseFromResultSet(rs);
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
		}
		return itemsVOList;

	}

	private static List<ItemsVO> buildResponseFromResultSet(ResultSet rs) {
		Date currentDate = new Date();
		List<ItemsVO> itemsVOList = new ArrayList<ItemsVO>();
		try {
			while (rs.next()) {
				ItemsVO itemVO = new ItemsVO();
				itemVO.setItem_id(Integer.parseInt(rs.getString("item_id")));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_description(rs.getString("item_description"));
				itemVO.setItem_zipcode(rs.getString("item_zipcode"));
				itemVO.setItem_available_from(rs.getDate("item_available_from"));
				itemVO.setItem_available_to(rs.getDate("item_available_to"));
				itemVO.setItem_costperday(rs.getDouble("item_costperday"));
				itemVO.setItem_category(rs.getString("item_category"));
				itemVO.setItem_postedby(Integer.parseInt(rs.getString("item_postedby")));
				itemVO.setItem_posteddate(rs.getDate("item_posteddate"));
				itemVO.setItem_image_url("imageservlet/" + rs.getString("item_id"));
				itemVO.setPickup_address(rs.getString("pickup_address"));
				itemVO.setPickup_city(rs.getString("pickup_city"));
				itemVO.setPickup_state(rs.getString("pickup_state"));
				itemVO.setContact(rs.getString("contact"));
				try {

					Date item_available_to_date = rs.getDate("item_available_to");

					if (rs.getBoolean("item_status") == true && item_available_to_date.after(currentDate)) {
						itemVO.setItem_status("Active");
					} else {
						itemVO.setItem_status("Not Active");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				itemsVOList.add(itemVO);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemsVOList;
	}

	public static List<ItemsVO> fetchPostedItemsForRenter(UserVO userVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;

		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		List<ItemsVO> itemsVOList = new ArrayList<ItemsVO>();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			pst = conn.prepareStatement("select * from post_items");
			rs = pst.executeQuery();
			itemsVOList = buildResponseFromResultSet(rs);

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
		}
		return itemsVOList;

	}

	public static boolean insertNewPost(String item_name, String item_description, String item_image, String zipcode,
			String item_available_from, String item_available_to, String item_costperday, String item_category,
			String item_postedby, String item_status, String item_posteddate, String pickup_addr, String pickup_city,
			String pickup_state, Part filePart, String contact) throws ParseException {
		// TODO Auto-generated method stub

		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;

		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		InputStream inputStream = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				try {
					inputStream = filePart.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pst = conn.prepareStatement(
					"insert into post_items(item_name,item_description,item_image,item_zipcode,item_available_from,item_available_to,item_costperday,item_category,item_postedby,item_status,item_posteddate,pickup_address,pickup_city,pickup_state,contact) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, item_name);
			pst.setString(2, item_description);
			if (inputStream != null) {
				pst.setBlob(3, inputStream);
			}
			pst.setString(4, zipcode);
			pst.setString(5, item_available_from);
			pst.setString(6, item_available_to);
			pst.setString(7, item_costperday);
			pst.setString(8, item_category);
			pst.setString(9, item_postedby);
			pst.setString(10, "1");
			pst.setString(11, item_posteddate);
			pst.setString(12, pickup_addr);
			pst.setString(13, pickup_city);
			pst.setString(14, pickup_state);
			pst.setString(14, contact);

			pst.executeUpdate();

			rs = pst.getGeneratedKeys();
			if (rs != null && rs.next()) {
				System.out.println("Generated user Id: " + rs.getInt(1));
				status = true;
			}
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
		return status;
	}

	public static boolean updatePost(String item_id, String item_name, String item_description, String item_image, String zipcode,
			String item_available_from, String item_available_to, String item_costperday, String item_category,
			String item_postedby, String item_status, String item_posteddate, String pickup_addr, String pickup_city,
			String pickup_state, Part filePart, String contact) throws ParseException {
		// TODO Auto-generated method stub

		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		rs = null;
		int result = 0;
		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		InputStream inputStream = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				try {
					if(filePart.getSize() > 0){
						inputStream = filePart.getInputStream();
						pst1 = conn.prepareStatement(
								"update post_items set item_image=? where item_id=?",
								Statement.RETURN_GENERATED_KEYS);

						pst1.setBlob(1, inputStream);
						pst1.setInt(2, Integer.parseInt(item_id));
						pst1.executeUpdate();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pst = conn.prepareStatement(
					"update post_items set item_name=?,item_description=?,item_zipcode=?,item_available_from=?,item_available_to=?,item_costperday=?,item_category=?,item_postedby=?,item_status=?,item_posteddate=?,pickup_address=?,pickup_city=?,pickup_state=?,contact=? where item_id=?",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, item_name);
			pst.setString(2, item_description);
			pst.setString(3, zipcode);
			pst.setString(4, item_available_from);
			pst.setString(5, item_available_to);
			pst.setString(6, item_costperday);
			pst.setString(7, item_category);
			pst.setString(8, item_postedby);
			pst.setString(9, "1");
			pst.setString(10, item_posteddate);
			pst.setString(11, pickup_addr);
			pst.setString(12, pickup_city);
			pst.setString(13, pickup_state);
			pst.setString(14, contact);
			pst.setInt(15, Integer.parseInt(item_id));

			result = pst.executeUpdate();
			if (result > 0) {
				status = true;
			}

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
			if (pst1 != null) {
				try {
					pst1.close();
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
		return status;
	}
	
	public static boolean deletePost(String item_id) throws ParseException {
		// TODO Auto-generated method stub

		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;
		int result = 0;
		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		InputStream inputStream = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn.prepareStatement(
					"delete from post_items where item_id=?",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, Integer.parseInt(item_id));

			result = pst.executeUpdate();
			if (result > 0) {
				status = true;
			}

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
		return status;
	}

	public static List<ItemsVO> getItemDetailsForItemId(int itemId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Date currentDate = new Date();
		ItemsVO itemVO = new ItemsVO();
		List<ItemsVO> itemsVO = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn.prepareStatement("select * from post_items where item_id=?");
			pst.setInt(1, itemId);

			rs = pst.executeQuery();
			itemsVO = buildResponseFromResultSet(rs);

			while (rs.next()) {
				itemVO.setItem_id(Integer.parseInt(rs.getString("item_id")));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_description(rs.getString("item_description"));
				itemVO.setItem_zipcode(rs.getString("item_zipcode"));
				itemVO.setItem_available_from(rs.getDate("item_available_from"));
				itemVO.setItem_available_to(rs.getDate("item_available_to"));
				itemVO.setItem_costperday(rs.getDouble("item_costperday"));
				itemVO.setItem_category(rs.getString("item_category"));
				itemVO.setItem_postedby(Integer.parseInt(rs.getString("item_postedby")));
				itemVO.setItem_posteddate(rs.getDate("item_posteddate"));
				itemVO.setUser_name(getUserNameForUserId(Integer.parseInt(rs.getString("item_postedby"))));
				itemVO.setPickup_address(rs.getString("pickup_address"));
				itemVO.setPickup_city(rs.getString("pickup_city"));
				itemVO.setPickup_state(rs.getString("pickup_state"));
				itemVO.setContact(rs.getString("contact"));

				try {

					Date item_available_to_date = rs.getDate("item_available_to");

					if (rs.getBoolean("item_status") == true && item_available_to_date.after(currentDate)) {
						itemVO.setItem_status("Active");
					} else {
						itemVO.setItem_status("Not Active");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

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
		return itemsVO;
	}

	public static String getUserNameForUserId(int userId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ItemsVO itemVO = new ItemsVO();
		String user_name = "";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn.prepareStatement("select * from user_details where user_id=?");
			pst.setInt(1, userId);

			rs = pst.executeQuery();

			while (rs.next()) {
				user_name = rs.getString("first_name");

			}

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
		return user_name;
	}

	public static int insertNewOrder(Integer item_id, Integer renter_id, Integer owner_id, String reserve_from,
			String reserve_to, Double total_cost, String order_status, String order_date) throws ParseException {
		// TODO Auto-generated method stub

		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;
		int order_id = 0;

		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			pst = conn.prepareStatement(
					"insert into reserve_order(item_id,renter_id,owner_id,reserve_from,reserve_to,total_cost,order_status,order_date) values(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, item_id);
			pst.setInt(2, renter_id);
			pst.setInt(3, owner_id);
			pst.setString(4, reserve_from);
			pst.setString(5, reserve_to);
			pst.setDouble(6, total_cost);
			pst.setString(7, order_status);
			pst.setObject(8, new Date());

			pst.executeUpdate();

			rs = pst.getGeneratedKeys();
			if (rs != null && rs.next()) {
				order_id = rs.getInt(1);
				System.out.println("Generated order Id: " + order_id);
				status = true;
			}
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
		return order_id;
	}

	public static boolean updateOrder(String card_name, String exp_year, String exp_month, String card_number,
			Integer order_id) throws ParseException {
		// TODO Auto-generated method stub

		boolean status = false;
		Connection conn = null;
		PreparedStatement pst = null;
		rs = null;
		int result = 0;

		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rent_it";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);

			pst = conn.prepareStatement(
					"update reserve_order Set card_name = ?,exp_year=?,exp_month=?, card_number =?, order_status=? where order_id =?");

			pst.setString(1, card_name);
			pst.setString(2, exp_year);
			pst.setString(3, exp_month);
			pst.setString(4, card_number);
			pst.setString(5, "RESERVED");
			pst.setInt(6, order_id);
			result = pst.executeUpdate();

			if (result > 0) {
				status = true;
			}

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
		return status;
	}

	public static List<OrderVO> getOrderDetailsForItem(String itemId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn
.prepareStatement(
					"select c.first_name,a.*,b.item_name,b.item_zipcode,b.pickup_address,pickup_city,pickup_state from reserve_order a left join post_items b on a.item_id = b.item_id left join user_details c on a.renter_id = c.user_id where b.item_id = ?");
			pst.setString(1, itemId);
			rs = pst.executeQuery();
			orderVOList = buildOrderResponse(rs);

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
		return orderVOList;
	}

	public static List<OrderVO> getOrderDetailsForRenter(String userId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn
					.prepareStatement("select c.first_name,a.*,b.item_name,b.item_zipcode,b.pickup_address,pickup_city,pickup_state from reserve_order a left join post_items b on a.item_id = b.item_id left join user_details c on a.renter_id = c.user_id where a.renter_id = ?");
			pst.setString(1, userId);
			rs = pst.executeQuery();
			orderVOList = buildOrderResponse(rs);

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
		return orderVOList;
	}

	/*public static List<ItemsVO> getPostDetailsForUser(String userId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ItemsVO> itemVOList = new ArrayList<ItemsVO>();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(DB_URL, userName, password);
			pst = conn.prepareStatement("select * from post_items where item_postedby = ?");
			pst.setString(1, userId);

			rs = pst.executeQuery();
			itemVOList = buildResponseFromResultSet(rs);

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
		return itemVOList;
	}*/

	private static List<OrderVO> buildOrderResponse(ResultSet rs) {
		Date currentDate = new Date();
		List<OrderVO> OrderVOList = new ArrayList<OrderVO>();

		try {
			while (rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOrder_id(Integer.parseInt(rs.getString("order_id")));
				orderVO.setOrder_status(rs.getString("order_status"));
				orderVO.setReserve_from(rs.getString("reserve_from"));
				orderVO.setReserve_to(rs.getString("reserve_to"));
				orderVO.setTotal_cost(Double.valueOf(rs.getString("total_cost")));
				orderVO.setOrder_date(rs.getString("order_date"));
				orderVO.setUser_first_name(rs.getString("first_name"));
				ItemsVO itemVO = new ItemsVO();
				itemVO.setItem_id(Integer.parseInt(rs.getString("item_id")));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_zipcode(rs.getString("item_zipcode"));

				orderVO.setItemsvo(itemVO);
				OrderVOList.add(orderVO);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OrderVOList;
	}
}
