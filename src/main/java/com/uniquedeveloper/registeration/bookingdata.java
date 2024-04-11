package com.uniquedeveloper.registeration;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Booking")
public class bookingdata extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        Connection conn1 = null;
        String uname = request.getParameter("packagename");
        String uemail = request.getParameter("email");
        String uprice = request.getParameter("price");
        int price = Integer.parseInt(uprice); // Parse price to integer
        String uamount = request.getParameter("amount");
        int amount = Integer.parseInt(uamount); // Parse amount to integer
        String udate = request.getParameter("date");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "1234");
            PreparedStatement pstt = conn1.prepareStatement("INSERT INTO booking(pname, email, total_price, amount_of_people, udate) VALUES(?, ?, ?, ?, ?)");
            pstt.setString(1, uname);
            pstt.setString(2, uemail);
            pstt.setInt(3, price); // Set price as integer
            pstt.setInt(4, amount); // Set amount as integer
            pstt.setString(5, udate);

            int rowCount = pstt.executeUpdate();
            dispatcher = request.getRequestDispatcher("index.jsp");
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "fail");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
