package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class accommodationDAO {

    private Connection connection;

    // Constructor
    public accommodationDAO() throws ClassNotFoundException, SQLException {
        connection = util.openConnection.getConnections();
    }

    // Get list of accommodations
    public List<Accommodation> get()  {
        List<Accommodation> accommodations = new ArrayList<>();
        String query = "SELECT a.id, a.name, a.description, a.price, a.roomID, a.placeID, r.name AS roomName, p.name AS placeName " +
                "FROM accommodations a " +
                "JOIN rooms r ON a.roomID = r.id " +
                "JOIN places p ON a.placeID = p.id";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Accommodation accommodation = extractAccommodationFromResultSet(resultSet);
                accommodations.add(accommodation);
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return accommodations;
    }

    // Get accommodation by ID
    public Accommodation getByID(int id) {
    	Accommodation acco = null;
        String query = "SELECT a.id, a.name, a.description, a.price, a.roomID, a.placeID, r.name AS roomName, p.name AS placeName " +
                "FROM accommodations a " +
                "JOIN rooms r ON a.roomID = r.id " +
                "JOIN places p ON a.placeID = p.id " +
                "WHERE a.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	acco = new Accommodation();
                	acco.setDescription(resultSet.getString("description"));
                	acco.setId(resultSet.getInt("id"));
                	acco.setName(resultSet.getString("name"))	;
                	acco.setPlaceID(resultSet.getInt("placeID"))	;
                	acco.setPlaceName(resultSet.getString("placeName"))	;
                	acco.setPrice(resultSet.getInt("price"))	;
                	acco.setRoomID(resultSet.getInt("roomID"))	;
                	acco.setRoomName(resultSet.getString("roomName"))	;
                }
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return acco;
    }
    

 // Get accommodation by ID
    public List<Accommodation> getByPlaceID(int id) {
    	List<Accommodation> accos = new ArrayList<Accommodation>();
        String query = "SELECT a.id, a.name, a.description, a.price, a.roomID, a.placeID, r.name AS roomName, p.name AS placeName " +
                "FROM accommodations a " +
                "JOIN rooms r ON a.roomID = r.id " +
                "JOIN places p ON a.placeID = p.id " +
                "WHERE a.placeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                	Accommodation acco = new Accommodation();
                	acco.setDescription(resultSet.getString("description"));
                	acco.setId(resultSet.getInt("id"));
                	acco.setName(resultSet.getString("name"))	;
                	acco.setPlaceID(resultSet.getInt("placeID"))	;
                	acco.setPlaceName(resultSet.getString("placeName"))	;
                	acco.setPrice(resultSet.getInt("price"))	;
                	acco.setRoomID(resultSet.getInt("roomID"))	;
                	acco.setRoomName(resultSet.getString("roomName"))	;
                	accos.add(acco);
                }
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return accos;
    }

    // Get accommodation by name
    public Accommodation getByName(String name) {
    	Accommodation acco = null;
        String query = "SELECT a.id, a.name, a.description, a.price, a.roomID, a.placeID, r.name AS roomName, p.name AS placeName " +
                "FROM accommodations a " +
                "JOIN rooms r ON a.roomID = r.id " +
                "JOIN places p ON a.placeID = p.id " +
                "WHERE a.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    acco = new Accommodation();
                    acco.setId(rs.getInt("id"));
                    acco.setDescription(rs.getString("description"));
                    acco.setPlaceID(rs.getInt("placeID"));
                    acco.setPlaceName(rs.getString("placeName"));
                    acco.setRoomID(rs.getInt("roomID"));
                    acco.setRoomName(rs.getString("roomName"));
                    acco.setPrice(rs.getInt("price"));
                }
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return acco;
    }

    // Create accommodation
    public boolean create(Accommodation accommodation) {
        boolean flag = false;
        String query = "INSERT INTO accommodations (name, description, price, roomID, placeID) values (?,?,?,?,?)";
        try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, accommodation.getName());
			pst.setString(2, accommodation.getDescription());
			pst.setInt(3, accommodation.getPrice());
			pst.setInt(4, accommodation.getRoomID());
			pst.setInt(5, accommodation.getPlaceID());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return flag;
    }

    // Edit accommodation
    public boolean edit(Accommodation accommodation) throws SQLException {
    	boolean flag = false;
        String query = "UPDATE accommodations SET name = ?, description=?, price=?, roomID=?, placeID=? WHERE id=?";
        try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, accommodation.getName());
			pst.setString(2, accommodation.getDescription());
			pst.setInt(3, accommodation.getPrice());
			pst.setInt(4, accommodation.getRoomID());
			pst.setInt(5, accommodation.getPlaceID());
			pst.setInt(6, accommodation.getId());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return flag;
    }

    // Helper method to extract accommodation from ResultSet
    private Accommodation extractAccommodationFromResultSet(ResultSet resultSet) throws SQLException {
        return new Accommodation(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getInt("roomID"),
                resultSet.getInt("placeID"),
                resultSet.getString("roomName"),
                resultSet.getString("placeName")
        );
    }
}
