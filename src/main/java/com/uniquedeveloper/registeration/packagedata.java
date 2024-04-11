package com.uniquedeveloper.registeration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class packagedata
 */
@WebServlet("/package")
public class packagedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection con = null;
	    String uname= request.getParameter("packagename");
		String uprice= request.getParameter("price");
		String uimage= request.getParameter("image");
		RequestDispatcher dispatcher = null;

	    // JDBC variables for opening, closing, and managing connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","1234");
			PreparedStatement pst = con
					.prepareStatement("insert into packages(package_name,price,image) values(?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, uprice);
			pst.setString(3, uimage);
			

			// Set the email attribute in the session
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("indexadmin.jsp");
			if (rowCount > 0) {
				request.setAttribute("status", "success");
				
			}
			else {
				request.setAttribute("status", "fail");
			}
			dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
				}finally {
					try {
							con.close();					
					}catch (SQLException e) {
						e.printStackTrace();
					}
					}
	}
	

}
