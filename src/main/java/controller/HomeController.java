package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import dao.*;
import java.util.*;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    countryDAO countryDao = null;
    roomDAO roomDao = null;
    accommodationDAO accoDao = null;
    placeDAO placeDao = null;

    public HomeController() throws ClassNotFoundException, SQLException {
        super();
        countryDao = new countryDAO();
        roomDao = new roomDAO();
        placeDao = new placeDAO();
        accoDao = new accommodationDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");

		
		if(page == null) {
			List<Accommodation> accos = accoDao.get();
			List<Place> places = placeDao.get();
			
			request.setAttribute("places", places);
			request.setAttribute("accos", accos);
			request.getRequestDispatcher("views/user/index.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
