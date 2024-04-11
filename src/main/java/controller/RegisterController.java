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

import util.Hash;
import dao.*;
import model.*;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	userDAO userDao = null;
       

    public RegisterController() throws ClassNotFoundException, SQLException {
        super();
        userDao = new userDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		registerProcess(request, response);
	}

	private void registerProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	    String role = null;
	    System.out.println("here");
	    // Check if the JSON object contains the expected keys
	    String password = jsonObject.get("password").getAsString();
        String email = jsonObject.get("email").getAsString();
        role = jsonObject.get("role").getAsString();
        String name = jsonObject.get("name").getAsString();
        String phone = jsonObject.get("phone").getAsString();
        
        System.out.println("here under if condition");

        System.out.println("password " + password);
        System.out.println("email " + email);
        String hashed_password = Hash.hashPassword(password);

        User is_user_exist = userDao.getByEmail(email);
        if(is_user_exist == null) {
        	if (role.equals("0")) {
                // save admin
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(hashed_password);
                user.setPhone(phone);
                user.setRole("admin");
                if (userDao.create(user)) {
                	System.out.println("admin is created");
                	session.setAttribute("admin", user);
                    flag = true;
                }
            } else {
                // save user
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(hashed_password);
                user.setPhone(phone);
                user.setRole("user");
                if (userDao.create(user)) {
                	System.out.println("user is created");
                	session.setAttribute("user", user);
                    flag = true;
                }
            }
        	
        }else {
        	responseObject.addProperty("status", "false");
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