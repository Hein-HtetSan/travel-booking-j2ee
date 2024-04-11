package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.*;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	userDAO userDao = null;
	countryDAO countryDao = null;
	roomDAO roomDao = null;
	placeDAO placeDao = null;
	accommodationDAO accoDao = null;
	bookingDAO bookingDao = null;
       

    public AdminController() throws ClassNotFoundException, SQLException {
        super();
        userDao = new userDAO();
        countryDao = new countryDAO();
        roomDao = new roomDAO();
        placeDao = new placeDAO();
        accoDao = new accommodationDAO();
        bookingDao = new bookingDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		User user = (User) session.getAttribute("admin");
		request.setAttribute("admin", user);
		
		if(user == null) {
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			return;
		}
		
		if(page == null) {
			List<Country> countries = countryDao.get();
			List<Room> rooms = roomDao.get();
			List<Place> places = placeDao.get();
			List<Accommodation> accos = accoDao.get();
			List<Booking> bookings = bookingDao.get();
			
			request.setAttribute("bookings", bookings);
			request.setAttribute("places", places);
			request.setAttribute("countries", countries);
			request.setAttribute("rooms", rooms);
			request.setAttribute("accos", accos);
		}
		request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if(action != null) {
			switch(action) {
			
			case "addRoom":
				addRoom(request, response);
				break;
				
			case "addRegion":
				addRegion(request, response);
				break;
				
			}
		}
	}
	
	// 
	private void addRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomName = request.getParameter("roomName");
		System.out.println(roomName);
		Room room = new Room();
		room.setName(roomName);
		if(roomDao.create(room)) {
			response.sendRedirect(request.getContextPath() + "/AdminController");
		}
	}
	
	private void addRegion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stateName = request.getParameter("stateName");
		System.out.println(stateName);
		Country country = new Country();
		country.setName(stateName);
		if(countryDao.create(country)) {
			response.sendRedirect(request.getContextPath() + "/AdminController");
		}
	}

}
