<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Account</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="bg-light">
  
  <div class="container">
    <div class="row d-flex align-items-center justify-content-center mt-5">
      <div class="col-md-5">
        <div class="card shadow p-4">
          <h1 class="fs-4 fw-bold text-primary">Login!</h1>
          <span class="text-muted">Discover tailored travel suggestions based on your preferences.</span>
          <hr>
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/LoginController" method="POST">
            	<input type="hidden" name="action" value="login">
              <div class="form-group">
                <label for="email" class="form-label fw-bold text-muted">Email</label>
                <input type="email" class="form-control" id="email" placeholder="Email">
              </div>
              <div class="form-group mt-3">
                <label for="email" class="form-label fw-bold text-muted">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password">
              </div>
              <div class="form-group mt-3">
                <label for="email" class="form-label fw-bold text-muted">Role</label>
                <select name="" id="role" class="form-select">
                  <option value="">Choose role...</option>
                  <option value="1">User</option>
                  <option value="0">Admin</option>
                </select>
              </div>
              <a href="" class="btn btn-link mt-2">Forget Password</a>
              <div class="form-group ">
                <button id="submit-btn" class="btn btn-primary mt-3">Login</button>
                <a href="${pageContext.request.contextPath}/HomeController" class="btn btn-secondary mt-3">Back</a>
              </div>
              <hr>
			  <a href="${pageContext.request.contextPath}/views/register.jsp" class="btn btn-link d-block text-center">New to here?</a>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <input type="hidden" id="status" value="">
  
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>  
  <!--sweet alert cdn link-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script>
$(document).ready(function() {
	console.log("document is ready")
    // Add event listener to the submit button
    $("#submit-btn").click(function(e) {

        console.log("clicked");
		e.preventDefault();
        // Collect data from the form
        var email = $("#email").val();
        var password = $("#password").val();
        var role = $("#role").val();

        // Configure the data object
        var data = {
            password: password,
            email: email,
            role: role,
        };

        // Send the AJAX request
        $.ajax({
            url: "http://localhost:9095/testing_project/LoginController",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(response) {
                console.log(response.status);
                // Handle success message here if needed
                if(password === '' || email === '' || role === ""){
                	Swal.fire({
                        title: "Wrong",
                        text: "Please fill all the field",
                        icon: "error"
                    });
                }else{
                	if(response.status == 'false'){
                		Swal.fire({
                            title: "Error",
                            text: "Email or password was wrong.",
                            icon: "error"
                     	});
                	}else{
                		$("#status").val(response.status);
                		Swal.fire({
                		    title: "Success",
                		    text: "Login Successfull",
                		    icon: "success"
                		}).then((result) => {
                		    if (result.isConfirmed && response.status == 'user') {
                		        // Redirect to HomeController
                		        window.location.href = "http://localhost:9095/testing_project/HomeController";
                		    }
                		    if (result.isConfirmed && response.status == 'admin') {
                		        // Redirect to HomeController
                		        window.location.href = "http://localhost:9095/testing_project/AdminController";
                		    }
                		});
                	}
                	$("#password").val('');
                   	$("#email").val('');
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX error:", error);
            }
        });
    });
});

	
	</script>

</body>
</html>

