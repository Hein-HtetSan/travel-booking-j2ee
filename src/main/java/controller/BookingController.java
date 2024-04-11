package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.*;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	placeDAO placeDao = null;
	accommodationDAO accoDao = null;
	bookingDAO bookDao = null;
	paymentDAO pyDao = null;
       

    public BookingController() throws ClassNotFoundException, SQLException {
        super();
        placeDao = new placeDAO();
        accoDao = new accommodationDAO();
        bookDao = new bookingDAO();
        pyDao = new paymentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(action != null) {
			if(action.equals("cancel")) {
				String userid = request.getParameter("userid");
				String bookingid= request.getParameter("bookingid");
				
				boolean canceled = bookDao.cancelBooking(Integer.parseInt(userid), Integer.parseInt(bookingid));
				if(canceled) {
					request.setAttribute("user", user);
					request.setAttribute("status", "canceled");
					request.getRequestDispatcher("/HistoryController").forward(request, response);
					return;
				}
			}
			if(action.equals("detail")) {
				String bookingid= request.getParameter("bookingid");
				
				Booking bookings = bookDao.getById(Integer.parseInt(bookingid));
				request.setAttribute("booking", bookings);
				request.getRequestDispatcher("/views/admin/bookingDetail.jsp").forward(request, response);
				return;
			}
			if(action.equals("confirm")) {
				String bookingid= request.getParameter("bookingid");
				
				if(bookDao.confirmBooking(Integer.parseInt(bookingid))) {
					request.setAttribute("status", "confirmed");
					request.getRequestDispatcher("/AdminController").forward(request, response);
					return;
				}
			}
			if(action.equals("calloff")) {
				String bookingid= request.getParameter("bookingid");
				
				if(bookDao.calloffBooking(Integer.parseInt(bookingid))) {
					request.setAttribute("status", "called off");
					request.getRequestDispatcher("/AdminController").forward(request, response);
					return;
				}
			}
		}
		
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/LoginController");
			return;
		}
		
		String place_id = request.getParameter("place_id");
		// get all accommodation
		List<Accommodation> accos = accoDao.getByPlaceID(Integer.parseInt(place_id));
		// get the place by id
		Place place = placeDao.getByPlaceId(Integer.parseInt(place_id));
		// get payment type
		List<PaymentType> pys = pyDao.getType();
		
		request.setAttribute("accos", accos);
		request.setAttribute("place", place);
		request.setAttribute("user", user);
		request.setAttribute("payments", pys);
		request.getRequestDispatcher("views/user/detail.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			
			switch(action) {
			
			case "save":
				save(request, response);
				break;
			
			}
		}
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Payment payment = new Payment();
		Random random = new Random();
        // Generate a random 3-digit number
        int randomNumber = random.nextInt(900) + 100; // Generates a random number between 100 (inclusive) and 999 (exclusive)
		
		String inDate = request.getParameter("checkindate");
		String outDate = request.getParameter("checkoutdate");
		String total = request.getParameter("total_price");
		String accID = request.getParameter("selectedAcco");
		String guests = request.getParameter("guests");
		String placeid = request.getParameter("placeid");
		String userid = request.getParameter("userid");
		String paymentid = request.getParameter("method");
		int status = 0;
		
		Booking booking = new Booking();
		booking.setAccommodationID(Integer.parseInt(accID));
		booking.setCheckInDate(inDate);
		booking.setCheckoutDate(outDate);
		booking.setNumberOfGuests(Integer.parseInt(guests));
		booking.setStatus(status);
		booking.setTotalPrice(Integer.parseInt(total));
		booking.setUserID(Integer.parseInt(userid));
		booking.setPlaceID(Integer.parseInt(placeid));
		booking.setId(randomNumber);
		// create booking
		System.out.println(paymentid);
		payment.setAmount(Integer.parseInt(total));
		payment.setBookingID(randomNumber);
		payment.setPaymentStatus(status);
		payment.setPaymentType(Integer.parseInt(paymentid));
		payment.setUserID(Integer.parseInt(userid));
		
		if(bookDao.create(booking)) {
			if(pyDao.create(payment) ) {
				response.sendRedirect(request.getContextPath() + "/HomeController");
				return;
			}
		}
	}

}
