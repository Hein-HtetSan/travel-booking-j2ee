<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Accounts</title>
</head>
<body>
    <h1>User Accounts</h1>
    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Password</th>
                <th>Ph No</th>
                <!-- Add more columns as needed -->
            </tr>
        </thead>
        <tbody>
            <% 
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    // Database connection parameters
                    String driver = "com.mysql.cj.jdbc.Driver";
                    String url = "jdbc:mysql://localhost:3306/login";
                    String username = "root";
                    String password = "1234";
                    
                    // Register JDBC driver
                    Class.forName(driver);
                    
                    // Open a connection
                    conn = DriverManager.getConnection(url, username, password);
                    
                    // SQL query to select user accounts
                    String sql = "SELECT * FROM user";
                    
                    // Create statement
                       stmt = conn.prepareStatement(sql);
                    
                    // Execute the query
                    rs = stmt.executeQuery();
                    
                    // Iterate over the result set and display user accounts
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    while (rs.next()) {
            %>
            <tr>
                <% for (int i = 1; i <= columnCount; i++) { %>
                    <td><%= rs.getString(i) %></td>
                <% } %>
            </tr>
            <% 
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace(); // Print stack trace for debugging
                } finally {
                    // Close JDBC resources
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </tbody>
    </table>
    
    	<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Upload Package</h2>
					
						<form method="post" action="package" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Package Name" / required>
							</div>
							<div class="form-group">
								<label for="price"><i class="zmdi zmdi-email"></i></label> <input
									type="price" name="price" id="email" placeholder="Price" / required>
							</div>
							<div class="form-group">
								<label for="image"><i class="zmdi zmdi-lock"></i></label> <input
									type="file" name="image" id="pass" placeholder="image" / required>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="submit" id="submit"
									class="form-submit" value="submit" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if(status == "success"){
			swal("Congrats", "Account Created Successfully", "success");
		}
	</script>
</body>
</html>
