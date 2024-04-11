package dao;

import java.sql.*;
import model.*;
import java.util.*;

public class countryDAO {
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public countryDAO() throws ClassNotFoundException, SQLException {
        con = util.openConnection.getConnections();
    }
    
    // Method to get all countries
    public List<Country> get() {
        List<Country> countries = new ArrayList<Country>();
        String query = "SELECT * FROM countries";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                // Add the country to the list
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
    
    // Method to create a new country
    public boolean create(Country country) {
        boolean created = false;
        String query = "INSERT INTO countries (name) VALUES (?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, country.getName());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                created = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }
    
    // Method to edit country
    public boolean edit(Country country) {
        boolean edited = false;
        String query = "UPDATE countries SET name = ? WHERE id = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, country.getName());
            pst.setInt(2, country.getId());
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
