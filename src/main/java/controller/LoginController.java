package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.userDAO;
import model.User;
import util.Hash;



@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	userDAO userDao = null;

    public LoginController() throws ClassNotFoundException, SQLException {
        super();
        userDao = new userDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginProcess(request, response);
	}
	
	private void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader reader = request.getReader();
	    StringBuilder jsonBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonBuilder.append(line);
	    }
	    reader.close();
	    boolean flag = false;
	    String jsonString = jsonBuilder.toString();
	    Gson gson = new Gson();
	    JsonObject responseObject = new JsonObject();
	    JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
	    String password = jsonObject.get("password").getAsString();
	    String email = jsonObject.get("email").getAsString();
	    String role = jsonObject.get("role").getAsString();
		
		System.out.println("password " + password);
		System.out.println("email " + email);
		
		User getuser = null;
		
		if(!password.isEmpty() && !email.isEmpty() && !role.isEmpty()) {
			getuser = userDao.getByEmail(email);
			if(getuser == null) {
				responseObject.addProperty("status", "false");
			}else {
				if(role.equals("0")) {
					session.setAttribute("admin", getuser);
					boolean isexit = Hash.verifyPassword(password, getuser.getPassword());
					 if(isexit) flag = true;
					 else responseObject.addProperty("status", "false");
					// check password for admin
				}else {
					// check password for user
					session.setAttribute("user", getuser);
					 boolean isexit = Hash.verifyPassword(password, getuser.getPassword());
					 if(isexit) flag = true;
					 else responseObject.addProperty("status", "false");
				}
			}
		}
		if (flag) {
	    	System.out.println("response is created");
	        if (role.equals("1")) {
	            responseObject.addProperty("status", "user");
	        } else if (role.equals("0")) {
	            responseObject.addProperty("status", "admin");
	        }
	    }
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(responseObject.toString());
	    out.flush();
	}

}
