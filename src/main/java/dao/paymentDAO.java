package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class paymentDAO{
	
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	
	public paymentDAO() throws ClassNotFoundException, SQLException {
		con = util.openConnection.getConnections();
	}
	
	// get payment type
	public List<PaymentType> getType(){
		List<PaymentType> pys = new ArrayList<PaymentType>();
		String query = "SELECT * FROM payment_type";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				PaymentType py = new PaymentType();
				py.setId(rs.getInt("id"));
				py.setName(rs.getString("name"));
				pys.add(py);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pys;
	}
	
	// create
	  public  boolean create(Payment payment) {
		boolean flag = false;
		String query = "INSERT INTO payments(amount, paymentStatus, paymentType, bookingID, userID)" + "VALUES (?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, payment.getAmount());
			pst.setInt(2, payment.getPaymentStatus());
			pst.setInt(3, payment.getPaymentType());
			pst.setInt(4, payment.getBookingID());
			pst.setInt(5, payment.getUserID());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
}