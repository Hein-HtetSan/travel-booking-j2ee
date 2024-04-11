package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;

import model.Place;
import model.*;
import dao.*;
/**
 * Servlet implementation class AccommodationController
 */
@MultipartConfig
@WebServlet("/AccommodationController")
public class AccommodationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	accommodationDAO accDao = null;
	roomDAO roomDao = null;
	placeDAO placeDao = null;

    public AccommodationController() throws ClassNotFoundException, SQLException {
        super();
        accDao = new accommodationDAO();
        placeDao = new placeDAO();
        roomDao = new roomDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		System.out.println(page);
		if(page != null) {
			switch(page) {
			
			case "edit":
				String accid = request.getParameter("accid");
				Accommodation acco = accDao.getByID(Integer.parseInt(accid));
				List<Room> rooms = roomDao.get();
				List<Place> places = placeDao.get();
				
				request.setAttribute("rooms", rooms);
				request.setAttribute("places", places);
				request.setAttribute("acco", acco);
				request.getRequestDispatcher("/views/admin/accommodation.jsp").forward(request, response);
				break;
				
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action2");
		System.out.println("action " + action);
		if(action != null) {
			switch(action) {
			
			case "save":
				save(request, response);
				break;
				
			case "update":
				update(request, response);
				break;
			
			}
		}
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String roomID = request.getParameter("roomID");
	    String price = request.getParameter("price");
	    String placeID = request.getParameter("placeID");
	    
	    boolean flag = false;
	    
	    Accommodation is_already_exist = accDao.getByName(name);
	    System.out.println(is_already_exist);
	    if(is_already_exist != null) {
	    	flag = false;
	    }else {
	    	Accommodation accommodation = new Accommodation();
	        accommodation.setName(name);
	        accommodation.setPrice(Integer.parseInt(price));
	        accommodation.setPlaceID(Integer.parseInt(placeID));
	        accommodation.setRoomID(Integer.parseInt(roomID));
	        accommodation.setDescription(description);
		    
		    boolean is_inserted = accDao.create(accommodation);
		    if(is_inserted) flag = true;
	    }
	    
	    System.out.println("Name: " + name);
	    System.out.println("Description: " + description);	    
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();

	    // Construct a JSON object representing the response
	    JsonObject jsonResponse = new JsonObject();
	    if(flag) {
	    	jsonResponse.addProperty("status", "true");
	    }else {
	    	jsonResponse.addProperty("status", "false");
	    }
	    // Convert the JSON object to a string and send it as the response
	    out.println(jsonResponse.toString());
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String roomID = request.getParameter("roomID");
		String placeID = request.getParameter("placeID");
		String accoID = request.getParameter("accid");
		
		System.out.println(name);
		
		Accommodation acco = new Accommodation();
		acco.setName(name);
		acco.setPrice(Integer.parseInt(price));
		acco.setPlaceID(Integer.parseInt(placeID));
        acco.setRoomID(Integer.parseInt(roomID));
        acco.setDescription(description);
        acco.setId(Integer.parseInt(accoID));
		
		try {
			boolean updated = accDao.edit(acco);
			if(updated) {
				response.sendRedirect(request.getContextPath() + "/AdminController");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
