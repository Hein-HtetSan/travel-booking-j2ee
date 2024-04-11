package dao;

import java.sql.*;
import model.*;
import java.util.*;

public class roomDAO {
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public roomDAO() throws ClassNotFoundException, SQLException {
        con = util.openConnection.getConnections();
    }
    
    // Method to get all rooms
    public List<Room> get() {
        List<Room> rooms = new ArrayList<Room>();
        String query = "SELECT * FROM rooms";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                // Add the room to the list
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
    // Method to create a new room
    public boolean create(Room room) {
        boolean created = false;
        String query = "INSERT INTO rooms (name) VALUES (?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, room.getName());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                created = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }
    
    // Method to edit room
    public boolean edit(Room room) {
        boolean edited = false;
        String query = "UPDATE rooms SET name = ? WHERE id = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, room.getName());
            pst.setInt(2, room.getId());
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
