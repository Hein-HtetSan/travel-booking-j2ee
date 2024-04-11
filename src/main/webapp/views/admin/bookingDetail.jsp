<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Website</title>

    <link rel="stylesheet" href="style.css">

    <!-- Bootstrap Link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Bootstrap Link -->


    <!-- Font Awesome Cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <!-- Font Awesome Cdn -->


    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
    <!-- Google Fonts -->
</head>
<body class="bg-light">

    <!-- Navbar Start -->
	<div class="container-fluid">
		<div class="row d-flex align-items-start justify-content-center py-5">
		
				<div class="col-md-4">
						<div class="card shadow rounded">
							<img src="${pageContext.request.contextPath}/assets/images/${booking.image}">
							<div class="card-body">
								<h5 class="fw-bold text-muted">${booking.placeName}</h5>
								<h6 class="text-muted">Customer: ${booking.userName}</h6>
								<h6 class="text-muted">Total: ${booking.totalPrice} MMKs</h6>
								<h6 class="text-muted">Accommodation: ${booking.accommodationName}</h6>
								<h6 class="text-muted mb-3">Number Of Guests: ${booking.numberOfGuests }</h6>
								
								<a href="${pageContext.request.contextPath}/AdminController" class="btn btn-secondary">Understand</a>
		
							</div>
						</div>
					</div>
			
		</div>
	</div>
	
	<c:if test="${status != null}">
		<input type="hidden" value="${status}" id="status">
	</c:if>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 



</body>
</html>