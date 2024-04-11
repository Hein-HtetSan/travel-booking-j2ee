package model;

public class Accommodation {
	
	private int id;
	private String name;
	private String description;
	private int price;
	private int roomID;
	private int placeID;
	private String roomName;
	private String placeName;
	
	public Accommodation() {
		
	}
	
	// Constructor
    public Accommodation(int id, String name, String description, double price, int roomID, int placeID, String roomName, String placeName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = (int) price;
        this.roomID = roomID;
        this.placeID = placeID;
        this.roomName = roomName;
        this.placeName = placeName;
    }
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

	
	
}