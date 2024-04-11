package dao;

import java.sql.*;
import model.*;
import java.util.*;

public class paymentTypeDAO {
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public paymentTypeDAO() throws ClassNotFoundException, SQLException {
        con = util.openConnection.getConnections();
    }
    
    // Method to get all payment types
    public List<PaymentType> get() {
        List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
        String query = "SELECT * FROM payment_types";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                PaymentType paymentType = new PaymentType();
                paymentType.setId(rs.getInt("id"));
                paymentType.setName(rs.getString("name"));
                // Add the payment type to the list
                paymentTypes.add(paymentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentTypes;
    }
    
    // Method to create a new payment type
    public boolean create(PaymentType paymentType) {
        boolean created = false;
        String query = "INSERT INTO payment_types (name) VALUES (?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, paymentType.getName());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                created = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }
    
    // Method to edit payment type
    public boolean edit(PaymentType paymentType) {
        boolean edited = false;
        String query = "UPDATE payment_types SET name = ? WHERE id = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, paymentType.getName());
            pst.setInt(2, paymentType.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                edited = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edited;
    }
}
