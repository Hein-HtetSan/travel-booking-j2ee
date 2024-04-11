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
		
			<div class="col-md-5">
				<div class="card  p-3 mb-3">
				<img src="${pageContext.request.contextPath}/assets/images/${place.image}" width="auto">
				<div class="card-body text-auto">
					<h5 class="fw-bold">Place name : ${place.name }</h5>
					<span class="d-block">Place detail : ${place.description }</span>
					<span class="d-block">Travel Cost per person: <span class="fw-bold" id="travel-cost">${place.price} MMK</span> </span>
					<span class="d-block">Country: <span class="fw-bold">${place.countryName}</span> </span>
				</div>
				</div>
				
				<h5>Total Price: <span class="fw-bold" id="total_price"></span> MMKs </h5>
				
			</div>
			
			<div class="col-md-4">
			<form id="bookingForm" class="mt-2" method="POST" action="${pageContext.request.contextPath}/BookingController">
					<input type="hidden" name="userid" value="${user.id }">
					<input type="hidden" name="action" value="save">
					<input type="hidden" name="total_price" value="" id="total">
					<input type="hidden" name="method" value="" id="paymentMethod">
					<input type="hidden" name="placeid" value="${place.id}" >
					<div class="form-group">
						<label class="form-label fw-bold text-secondary">Choose Accommodation Types:</label>
						
						<div class="row">
							<c:choose>
								<c:when test="${accos.size() == 0}">
									<h5 class="text-danger mb-5 fw-bold">No accommodation available!</h5>
								</c:when>
								<c:otherwise>
									<c:forEach items="${accos}" var="acco">
									    <div class="col-6">
									        <div class="card border rounded mb-2">
									            <div class="card-body">
									                <input type="radio" name="selectedAcco" value="${acco.id}" class="form-check">
									                <h6 class="fw-bold">Name: ${acco.name}</h6>
									                <h6>Living Cost: <span id="acco-price">${acco.price}</span> MMKs</h6>
									                <h6>Room: ${acco.roomName}</h6>
									            </div>
									        </div>
									    </div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					
					<div class="form-group mb-2">
						<label class="form-label fw-bold text-secondary">Check in date</label>
						<input type="date" id="indate" class="form-control" name="checkindate">
					</div>
					
					<div class="form-group mb-2">
						<label class="form-label fw-bold text-secondary">Check out date</label>
						<input type="date" id="outdate" class="form-control" name="checkoutdate">
					</div>
					<div class="form-group mb-2">
						<label class="form-label fw-bold text-secondary">Number of Guests</label>
						<input type="number" class="form-control" name="guests" id="no_of_guest" value="1">
					</div>
					
					<button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Book Now</button>
					<a class="btn btn-secondary" href="${pageContext.request.contextPath}/HomeController" >Back</a>
					
			</form>
			</div>
			
		</div>
	</div>
	
	<c:if test="${status != null}">
		<input type="hidden" value="${status}" id="status">
	</c:if>
	
	<!-- Modal -->
		<!-- Payment Modal -->
<div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="paymentModalLabel">Payment Information</h5>

        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="paymentForm">
          <!-- Payment form fields -->
          <input type="hidden" name="action" value="save">
          <!-- Example: -->
          <div class="mb-3">
            <label for="cardNumber" class="form-label">Payment Method</label>
            <select name="method" id="method" class="form-select" required>
            	<option value="">Choose method...</option>
            	<c:forEach items="${payments}" var="pay">
				        <option value="${pay.id}" class="${pay.name == 'online payment' ? 'disabled' : 'enabled'}">
				            ${pay.name} 
				            <c:if test="${pay.name == 'online payment'}">
				                <small class="text-danger text-muted" style="color: red !important;">currently unavailable!</small>
				            </c:if>
				        </option>
				    </c:forEach>
            </select>
             <span class="text-warning">Payment must be pay cash on arrival time.</span>
          </div>
          <!-- Add other payment fields as needed -->
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="submitPayment">Submit Payment</button>
      </div>
    </div>
  </div>
</div>




	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    // jQuery document ready function
    console.log("document is ready");

	$(document).ready(function() {
	    var travel_cost = parseInt($("#travel-cost").text());
	    var acco_price = 0;
	
	    updateTotalPrice();
	
	    $('input[name="selectedAcco"]').click(function() {
	        // Get the price associated with the selected accommodation
	        var price = parseInt($(this).closest('.card-body').find('#acco-price').text());
	        acco_price = price;
	
	        // Update the total price
	        updateTotalPrice();
	    });
	    
	    
	
	    // Change event handler for the input number of the form
	    $("#no_of_guest").change(function() {
	        // Update the total price when the input number changes
	        updateTotalPrice();
	    });
	
	    function updateTotalPrice() {
	        var no_of_guest = parseInt($("#no_of_guest").val());
	        var total = parseInt(acco_price) + (travel_cost * no_of_guest);
	        console.log(no_of_guest);
	        console.log(travel_cost);
	        console.log(acco_price);
	        $("#total_price").text(total);
	        $("#total").val(total);
	    }
	    
	    var status = $("#status").val();
	    if(status == 'existed'){
	    	event.preventDefault(); // Prevent form submission
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'You already made booking to this package!',
            });
            return;
	    }
	    
	 // Form submission handler
        $("#bookingForm").submit(function (event) {
            event.preventDefault(); // Prevent default form submission
            var checkInDate = new Date($("#indate").val());
            var checkOutDate = new Date($("#outdate").val());
            var currentDate = new Date();

            // Check if accommodation is selected
            if ($('input[name="selectedAcco"]:checked').length === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Please select an accommodation type!',
                });
                return;
            }

            var checkInDateValue = $("#indate").val();
            if (!checkInDateValue) {
                console.error("Check-in date is not filled.");
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Please select a check-in date!',
                });
                return;
            }

            // Parse check-in date
            var checkInDate = new Date(checkInDateValue);
            if (isNaN(checkInDate.getTime())) {
                console.error("Error parsing check-in date:", checkInDateValue);
                return;
            }

            // Check if check-out date is filled
            var checkOutDateValue = $("#outdate").val();
            if (!checkOutDateValue) {
                console.error("Check-out date is not filled.");
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Please select a check-out date!',
                });
                return;
            }

            // Parse check-out date
            var checkOutDate = new Date(checkOutDateValue);
            if (isNaN(checkOutDate.getTime())) {
                console.error("Error parsing check-out date:", checkOutDateValue);
                return;
            }

            // Check if check-in date is before the current date
            if (checkInDate < currentDate) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Check-in date must be after the current date.',
                });
                return;
            }

            // Check if check-out date is before check-in date
            if (checkOutDate <= checkInDate) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Check-out date must be after the check-in date.',
                });
                return;
            }

            // Proceed with form submission
            $("#paymentModal").modal("show"); // Show payment modal
        });

     // Payment submission handler
        $('#submitPayment').click(function (event) {
            event.preventDefault(); // Prevent default button action
            var method = $("#method").val();
            console.log(method);
            $("#paymentMethod").val(method);
            // Show SweetAlert confirmation
            Swal.fire({
                title: 'Are you sure?',
                text: 'Do you want to continue with the payment?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, continue'
            }).then((result) => {
                if (result.isConfirmed) {
                	$('#bookingForm').off('submit').submit();
                }
            });
        });


	});

	</script>



</body>
</html>