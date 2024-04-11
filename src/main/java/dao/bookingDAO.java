package dao;

import java.util.*;

import model.Booking;
import java.time.LocalDate;
import java.util.Date;
import java.sql.*;


public class bookingDAO {
	
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	
	
	public bookingDAO() throws ClassNotFoundException, SQLException {
		con = util.openConnection.getConnections();
	}
	
	// get all
	public List<Booking> get(){
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT bookings.*, users.name as userName, payments.paymentStatus as paymentstatus, accommodations.name as accommodationName, places.name as placeName FROM bookings "
				+ "LEFT JOIN users ON bookings.userID = users.id "
				+ "LEFT JOIN accommodations ON accommodations.id = bookings.accommodationID "
				+ "LEFT JOIN payments ON bookings.id = payments.bookingID "
				+ "LEFT JOIN places ON bookings.placeID = places.id WHERE bookings.status = 0";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Booking books = new Booking();
				books.setAccommodationID(rs.getInt("accommodationID"));
				books.setAccommodationName(rs.getString("accommodationName"));
				books.setCheckInDate(rs.getString("checkInDate"));
				books.setCheckoutDate(rs.getString("checkOutDate"));
				books.setCreated_at(rs.getString("created_at"));
				books.setId(rs.getInt("id"));
				books.setNumberOfGuests(rs.getInt("numberOfGuests"));
				books.setPlaceID(rs.getInt("placeID"));
				books.setPlaceName(rs.getString("placeName"));
				books.setStatus(rs.getInt("status"));
				books.setTotalPrice(rs.getInt("totalPrice"));
				books.setUpdated_at(rs.getString("updated_at"));
				books.setUserID(rs.getInt("userID"));
				books.setUserName(rs.getString("userName"));
				books.setPaymentStatus(rs.getInt("paymentstatus"));
				bookings.add(books);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookings;
	}
	
	// get by userid and history
	public List<Booking> getPendingList(int userid){
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT bookings.*, users.name as userName, places.image as image, accommodations.name as accommodationName, places.name as placeName FROM bookings "
				+ "LEFT JOIN users ON bookings.userID = users.id "
				+ "LEFT JOIN accommodations ON accommodations.id = bookings.accommodationID "
				+ "LEFT JOIN places ON bookings.placeID = places.id WHERE bookings.status = 0 AND bookings.userID = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, userid);
			rs = pst.executeQuery()	;
			while(rs.next()) {
				Booking books = new Booking();
				books.setAccommodationID(rs.getInt("accommodationID"));
				books.setAccommodationName(rs.getString("accommodationName"));
				books.setCheckInDate(rs.getString("checkInDate"));
				books.setCheckoutDate(rs.getString("checkOutDate"));
				books.setCreated_at(rs.getString("created_at"));
				books.setId(rs.getInt("id"));
				books.setNumberOfGuests(rs.getInt("numberOfGuests"));
				books.setPlaceID(rs.getInt("placeID"));
				books.setPlaceName(rs.getString("placeName"));
				books.setStatus(rs.getInt("status"));
				books.setTotalPrice(rs.getInt("totalPrice"));
				books.setUpdated_at(rs.getString("updated_at"));
				books.setUserID(rs.getInt("userID"));
				books.setImage(rs.getString("image"));
				books.setUserName(rs.getString("userName"));
				bookings.add(books);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookings;
	}
	
	//getCanceledList
	public List<Booking> getCanceledList(){
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT bookings.*, users.name as userName, places.image as image, accommodations.name as accommodationName, places.name as placeName FROM bookings "
				+ "LEFT JOIN users ON bookings.userID = users.id "
				+ "LEFT JOIN accommodations ON accommodations.id = bookings.accommodationID "
				+ "LEFT JOIN places ON bookings.placeID = places.id WHERE bookings.status < 0";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Booking books = new Booking();
				books.setAccommodationID(rs.getInt("accommodationID"));
				books.setAccommodationName(rs.getString("accommodationName"));
				books.setCheckInDate(rs.getString("checkInDate"));
				books.setCheckoutDate(rs.getString("checkOutDate"));
				books.setCreated_at(rs.getString("created_at"));
				books.setId(rs.getInt("id"));
				books.setNumberOfGuests(rs.getInt("numberOfGuests"));
				books.setPlaceID(rs.getInt("placeID"));
				books.setPlaceName(rs.getString("placeName"));
				books.setStatus(rs.getInt("status"));
				books.setTotalPrice(rs.getInt("totalPrice"));
				books.setUpdated_at(rs.getString("updated_at"));
				books.setUserID(rs.getInt("userID"));
				books.setImage(rs.getString("image"));
				books.setUserName(rs.getString("userName"));
				bookings.add(books);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookings;
	}
	
	
	//getCanceledList
		public List<Booking> getSuccessList(){
			List<Booking> bookings = new ArrayList<Booking>();
			String query = "SELECT bookings.*, users.name as userName, places.image as image, accommodations.name as accommodationName, places.name as placeName FROM bookings "
					+ "LEFT JOIN users ON bookings.userID = users.id "
					+ "LEFT JOIN accommodations ON accommodations.id = bookings.accommodationID "
					+ "LEFT JOIN places ON bookings.placeID = places.id WHERE bookings.status > 0";
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Booking books = new Booking();
					books.setAccommodationID(rs.getInt("accommodationID"));
					books.setAccommodationName(rs.getString("accommodationName"));
					books.setCheckInDate(rs.getString("checkInDate"));
					books.setCheckoutDate(rs.getString("checkOutDate"));
					books.setCreated_at(rs.getString("created_at"));
					books.setId(rs.getInt("id"));
					books.setNumberOfGuests(rs.getInt("numberOfGuests"));
					books.setPlaceID(rs.getInt("placeID"));
					books.setPlaceName(rs.getString("placeName"));
					books.setStatus(rs.getInt("status"));
					books.setTotalPrice(rs.getInt("totalPrice"));
					books.setUpdated_at(rs.getString("updated_at"));
					books.setUserID(rs.getInt("userID"));
					books.setImage(rs.getString("image"));
					books.setUserName(rs.getString("userName"));
					bookings.add(books);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bookings;
		}
	
	
	
	// get by userid and history
		public Booking getById(int userid){
			Booking books = null;
			String query = "SELECT bookings.*, users.name as userName, places.image as image, accommodations.name as accommodationName, places.name as placeName FROM bookings "
					+ "LEFT JOIN users ON bookings.userID = users.id "
					+ "LEFT JOIN accommodations ON accommodations.id = bookings.accommodationID "
					+ "LEFT JOIN places ON bookings.placeID = places.id WHERE bookings.id = ?";
			try {
				pst = con.prepareStatement(query);
				pst.setInt(1, userid);
				rs = pst.executeQuery()	;
				if(rs.next()) {
					books = new Booking();
					books.setAccommodationID(rs.getInt("accommodationID"));
					books.setAccommodationName(rs.getString("accommodationName"));
					books.setCheckInDate(rs.getString("checkInDate"));
					books.setCheckoutDate(rs.getString("checkOutDate"));
					books.setCreated_at(rs.getString("created_at"));
					books.setId(rs.getInt("id"));
					books.setNumberOfGuests(rs.getInt("numberOfGuests"));
					books.setPlaceID(rs.getInt("placeID"));
					books.setPlaceName(rs.getString("placeName"));
					books.setStatus(rs.getInt("status"));
					books.setTotalPrice(rs.getInt("totalPrice"));
					books.setUpdated_at(rs.getString("updated_at"));
					books.setUserID(rs.getInt("userID"));
					books.setImage(rs.getString("image"));
					books.setUserName(rs.getString("userName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return books;
		}
	
	// create
	public boolean create(Booking booking) {
		boolean flag = false;
		String query = "INSERT INTO bookings (checkInDate, checkOutDate, numberOfGuests, TotalPrice, status, userID, accommodationID, placeID, id) "
				+ "VALUES (?,?,?,?,?,?,?,?,?) ";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, booking.getCheckInDate());
			pst.setString(2, booking.getCheckoutDate());
			pst.setInt(3, booking.getNumberOfGuests());
			pst.setInt(4, booking.getTotalPrice());
			pst.setInt(5, booking.getStatus());
			pst.setInt(6, booking.getUserID());
			pst.setInt(7, booking.getAccommodationID());
			pst.setInt(8, booking.getPlaceID());
			pst.setInt(9, booking.getId());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// cancel the booking
	public boolean cancelBooking(int userid, int bookingid) {
		boolean flag = false;
		String query = "UPDATE bookings SET status = -1 WHERE bookings.userID = " + userid + " AND bookings.id = " + bookingid;
		String query2 = "DELETE FROM payments WHERE bookingID = " + bookingid;
		try {
			stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			int canceled = stmt.executeUpdate(query);
			int confirmed = stmt2.executeUpdate(query2);
			if(canceled > 0 && confirmed > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// cancel the booking
		public boolean confirmBooking(int bookingid) {
			boolean flag = false;
			String query = "UPDATE bookings SET status = 1 WHERE bookings.id = " + bookingid;
			String query2 = "UPDATE payments SET paymentStatus = 1 WHERE bookingID = " + bookingid;
			try {
				stmt = con.createStatement();
				Statement stmt2 = con.createStatement();
				int confirmed = stmt2.executeUpdate(query2);
				int canceled = stmt.executeUpdate(query);
				if(canceled > 0 && confirmed > 0) flag = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		
		// cancel the booking
				public boolean calloffBooking(int bookingid) {
					boolean flag = false;
					String query = "UPDATE bookings SET status = 1 WHERE bookings.id = " + bookingid;
					String query2 = "DELETE FROM payments WHERE bookingID = " + bookingid;
					try {
						stmt = con.createStatement();
						Statement stmt2 = con.createStatement();
						int confirmed = stmt2.executeUpdate(query2);
						int canceled = stmt.executeUpdate(query);
						if(canceled > 0 && confirmed > 0) flag = true;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return flag;
				}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}