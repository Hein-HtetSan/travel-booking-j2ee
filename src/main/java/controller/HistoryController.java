package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.*;
import model.*;
import java.util.*;

@WebServlet("/HistoryController")
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	bookingDAO bookDao = null;

    public HistoryController() throws ClassNotFoundException, SQLException {
        super();
        bookDao = new bookingDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		//for admin
		String page = request.getParameter("page");
		if(page != null) {
			if(page.equals("forAdmin")) {
				List<Booking> cancled = bookDao.getCanceledList();
				List<Booking> successes = bookDao.getSuccessList();
				request.setAttribute("successes", successes);
				request.setAttribute("canceleds", cancled);
				request.getRequestDispatcher("/views/admin/history.jsp").forward(request, response);
				return;
			}
		}
		
		
		// for user
		List<Booking> bookings = bookDao.getPendingList(Integer.parseInt(userid));
		
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/views/user/history.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
