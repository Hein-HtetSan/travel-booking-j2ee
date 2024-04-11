package dao;

import java.sql.*;
import model.*;
import java.util.*;

public class userDAO {
    
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public userDAO() throws ClassNotFoundException, SQLException {
        con = util.openConnection.getConnections();
    }
    
    // Method to get all users
    public List<User> get(){
        List<User> users = new ArrayList<User>();
        String query = "SELECT * FROM users";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                // Add the user to the list
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    // Method to get user by ID
    public User getById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Method to get user by name
    public User getByName(String name) {
        User user = null;
        String query = "SELECT * FROM users WHERE name = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Method to get user by email
    public User getByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Method to create a new user
    public boolean create(User user) {
        boolean created = false;
        String query = "INSERT INTO users (name, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getRole());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                created = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }
    
    // Method to edit user
    public boolean edit(User user) {
        boolean edited = false;
        String query = "UPDATE users SET name = ?, email = ?, phone = ?, role = ? WHERE id = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getRole());
            pst.setInt(5, user.getId());
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
