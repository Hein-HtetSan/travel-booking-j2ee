<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
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
		<div class="row p-5">
				
				<a class="btn btn-secondary w-auto mb-3" href="${pageContext.request.contextPath}/HomeController">Back</a>
			
				<h4 class="fw-bold text-secondary">Booking History</h4>
				
				<c:if test="${bookings.size() == 0}">
					<h6 class="text-warning fw-bold">No record here!</h6>
				</c:if>
				
				<c:forEach items="${bookings}" var="booking">

					<div class="col-md-3 ">
						<div class="card shadow rounded">
							<img src="${pageContext.request.contextPath}/assets/images/${booking.image}">
							<div class="card-body">
								<h5 class="fw-bold text-muted">${booking.placeName}</h5>
								
								<c:set var="bookingCheckInDate" value="${booking.checkInDate}" />
								<fmt:parseDate var="checkInDate" value="${bookingCheckInDate}" pattern="yyyy-MM-dd" />
								<small class="d-block text-muted">Check in date: <fmt:formatDate value="${checkInDate}" pattern="dd-MM-yyyy" /></small>
								
								<c:set var="bookingCheckoutDate" value="${booking.checkoutDate}" />
								<fmt:parseDate var="checkoutDate" value="${bookingCheckoutDate}" pattern="yyyy-MM-dd" />
								<small class="d-block mb-2 text-muted">Check out date: <fmt:formatDate value="${checkoutDate}" pattern="dd-MM-yyyy" /></small>
		
								<h6 class="text-muted">Total: ${booking.totalPrice} MMKs</h6>
								<h6 class="text-muted">Accommodation: ${booking.accommodationName}</h6>
								<h6 class="text-muted mb-3">Number Of Guests: ${booking.numberOfGuests }</h6>
								
								<a href="${pageContext.request.contextPath}/BookingController?action=cancel&userid=${user.id}&bookingid=${booking.id}" onclick="confirmCancellation(event)" class="btn btn-danger" id="canceled-btn">Cancel Booking</a>
		
							</div>
						</div>
					</div>
				</c:forEach>
				
			
			
		</div>
	</div>
	
	<c:if test="${status != null}">
		<input type="hidden" value="${status}" id="status">
	</c:if>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <script>
    function confirmCancellation(event) {
        event.preventDefault(); // Prevent the default action (navigation)

        // Show SweetAlert confirmation
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Yes, cancel it!'
        }).then((result) => {
            if (result.isConfirmed) {
                // If user confirms, navigate to the specified URL
                window.location.href = event.target.href;
            }
        });
    }
</script>




</body>
</html>