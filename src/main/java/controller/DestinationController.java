package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;
import model.*;
import dao.*;

@MultipartConfig
@WebServlet("/DestinationController")
public class DestinationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	placeDAO placeDao = null;
	countryDAO countryDao = null;
       

    public DestinationController() throws ClassNotFoundException, SQLException {
        super();
        placeDao = new placeDAO();
        countryDao = new countryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "edit":
				edit(request, response);
				break;
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if(action != null) {
			switch(action) {
			
			case "save":
				save(request, response);
				break;
				
			case "updated":
				update(request, response);
				break;
				
			}
		}
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeid = request.getParameter("placeid");
		
		Place place = placeDao.getById(Integer.parseInt(placeid));
		List<Country> countries = countryDao.get();
		
		request.setAttribute("countries", countries);
		request.setAttribute("place", place);
		request.getRequestDispatcher("/views/admin/destination.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String countryID = request.getParameter("countryID");
	    String price = request.getParameter("price");
	    boolean flag = false;
	    
	    Part filePart = request.getPart("file");
	    
	    String fileName = "";
	    String updated_filename = null;
	    
	    Place is_already_exist = placeDao.getByName(name);
	    System.out.println(is_already_exist);
	    if(is_already_exist == null) {
	    	
	    	System.out.println("not exist");
	    	if (filePart != null) {
		        fileName = filePart.getSubmittedFileName();
		        // You can save the file to a directory or perform other operations as needed
		        int min = 1000;
			    int max = 10000;
			    int random_number = (int) (Math.random()*(max-min+1)+min);  
			     updated_filename = random_number + "_" + fileName;

			    //Define destination directory
		        String uploadDir = "C:\\Users\\acer\\Desktop\\travel-booking\\src\\main\\webapp\\assets\\images"; // Example: "C:/eclipse_workspace/upload"
		        
		        // Write file to the destination directory
		        OutputStream out = null;
		        InputStream fileContent = null;
		        try {
		            out = new FileOutputStream(new File(uploadDir + File.separator + updated_filename));
		            fileContent = filePart.getInputStream();

		            int read;
		            final byte[] bytes = new byte[1024];
		            while ((read = fileContent.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		        } catch (FileNotFoundException fne) {
		            // Handle file not found exception
		            fne.printStackTrace();
		        } finally {
		            if (out != null) {
		                out.close();
		            }
		            if (fileContent != null) {
		                fileContent.close();
		            }
		        }
		        // For simplicity, we are just printing the file name
		        System.out.println("Uploaded file name: " + fileName);
		        
		    }

	        Place place = new Place();
		    place.setCountryID(Integer.parseInt(countryID));
		    place.setDescription(description);
		    place.setName(name);
		    place.setImage(updated_filename);
		    place.setPrice(Integer.parseInt(price));
		    
		    boolean is_inserted = placeDao.create(place);
		    if(is_inserted) flag = true;
	    }
	    
	    System.out.println("Name: " + name);
	    System.out.println("Description: " + description);
	    System.out.println("Country ID: " + countryID);
	    
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
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String countryID = request.getParameter("countryID");
	    String price = request.getParameter("price");
	    String placeid = request.getParameter("placeid");
	    Part filePart = request.getPart("file");
	    
	    String fileName = "";
	    String updated_filename = null;
	    
	    Place place = new Place();
	    place.setCountryID(Integer.parseInt(countryID));
	    place.setDescription(description);
	    place.setName(name);
	    place.setPrice(Integer.parseInt(price));
	    place.setId(Integer.parseInt(placeid));
	    


	    	if (filePart != null) {
		        fileName = filePart.getSubmittedFileName();
		        // You can save the file to a directory or perform other operations as needed
		        int min = 1000;
			    int max = 10000;
			    int random_number = (int) (Math.random()*(max-min+1)+min);  
			     updated_filename = random_number + "_" + fileName;
			     
				    System.out.println(fileName);
				if(fileName != null && !fileName.equals("")) {
					//Define destination directory
			        String uploadDir = "C:\\Users\\acer\\Desktop\\travel-booking\\src\\main\\webapp\\assets\\images"; // Example: "C:/eclipse_workspace/upload"
			        
			        // Write file to the destination directory
			        OutputStream out = null;
			        InputStream fileContent = null;
			        try {
			            out = new FileOutputStream(new File(uploadDir + File.separator + updated_filename));
			            fileContent = filePart.getInputStream();
			    	    place.setImage(updated_filename);

			            int read;
			            final byte[] bytes = new byte[1024];
			            while ((read = fileContent.read(bytes)) != -1) {
			                out.write(bytes, 0, read);
			            }
			            
			        } catch (FileNotFoundException fne) {
			            // Handle file not found exception
			            fne.printStackTrace();
			        } finally {
			            if (out != null) {
			                out.close();
			            }
			            if (fileContent != null) {
			                fileContent.close();
			            }
			        }
			        // For simplicity, we are just printing the file name
			        System.out.println("Uploaded file name: " + fileName);
				}
		    
		    if(placeDao.edit(place)) {
		    	response.sendRedirect(request.getContextPath() + "/AdminController");
		    }
	    }
	}

}
