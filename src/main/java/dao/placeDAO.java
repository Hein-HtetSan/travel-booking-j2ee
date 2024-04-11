 package dao;

import java.util.*;
import java.sql.*;
import model.*;

public class placeDAO {
    
    Connection con = null;
    Statement stmt = null;
    
    public placeDAO() throws ClassNotFoundException, SQLException {
        con = util.openConnection.getConnections();
    }
    
    // Retrieve all places
    public List<Place> get() {
        List<Place> places = new ArrayList<>();
        String query = "SELECT places.*, countries.name as country_name FROM places LEFT JOIN countries ON countries.id = places.countryID";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Place place = new Place();
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("name"));
                place.setDescription(rs.getString("description"));
                place.setImage(rs.getString("image"));
                place.setCountryID(rs.getInt("countryID"));
                place.setCountryName(rs.getString("country_name"));
                place.setPrice(rs.getInt("price"));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }
    
    // Retrieve a place by ID
    public Place getById(int id) {
        Place place = new Place();
        String query = "SELECT places.*, countries.name as country_name FROM places LEFT JOIN countries ON countries.id = places.countryID "
        		+ " WHERE places.id=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("name"));
                place.setDescription(rs.getString("description"));
                place.setImage(rs.getString("image"));
                place.setCountryID(rs.getInt("countryID"));
                place.setCountryName(rs.getString("country_name"));
                place.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }
    
 // Retrieve a place by ID
    public Place getByPlaceId(int id) {
        Place place = new Place();
        String query = "SELECT places.*, countries.name as country_name FROM places LEFT JOIN countries ON countries.id = places.countryID "
        		+ " WHERE places.id=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("name"));
                place.setDescription(rs.getString("description"));
                place.setImage(rs.getString("image"));
                place.setCountryID(rs.getInt("countryID"));
                place.setCountryName(rs.getString("country_name"));
                place.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }
    
    // Create a new place
    public boolean create(Place place) {
        String query = "INSERT INTO places (name, description, image, countryID, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, place.getName());
            pst.setString(2, place.getDescription());
            pst.setString(3, place.getImage());
            pst.setInt(4, place.getCountryID());
            pst.setInt(5, place.getPrice());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Retrieve a place by name
    public Place getByName(String name) {
        Place place = null;
        String query = "SELECT * FROM places WHERE name=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	place = new Place();
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("name"));
                place.setDescription(rs.getString("description"));
                place.setImage(rs.getString("image"));
                place.setCountryID(rs.getInt("countryID"));
                place.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    
 // Update an existing place
    public boolean edit(Place place) {
        // Define the SQL query
        String query = (place.getImage() != null) ?
                "UPDATE places SET name=?, description=?, countryID=?, price=?, image=? WHERE id=?" :
                "UPDATE places SET name=?, description=?, countryID=?, price=? WHERE id=?";
        
        try (PreparedStatement pst = con.prepareStatement(query)) {
            // Set parameters based on whether image is present or not
            pst.setString(1, place.getName());
            pst.setString(2, place.getDescription());
            pst.setInt(3, place.getCountryID());
            pst.setInt(4, place.getPrice());
            
            if (place.getImage() != null) {
                pst.setString(5, place.getImage());
                pst.setInt(6, place.getId());
            } else {
                pst.setInt(5, place.getId());
            }
            
            // Execute the update query
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    // Retrieve places by country name
    public List<Place> getByCountry(String countryName) {
        List<Place> places = new ArrayList<>();
        String query = "SELECT * FROM places p INNER JOIN countries c ON p.countryID = c.id WHERE c.name=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, countryName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Place place = new Place();
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("name"));
                place.setDescription(rs.getString("description"));
                place.setImage(rs.getString("image"));
                place.setCountryID(rs.getInt("countryID"));
                place.setPrice(rs.getInt("price"));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }
}
