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
							<div class="card-header">Edit Accommodation</div>
							<div class="card-body">
								<form method="POST" id="accommodationForm" action="${pageContext.request.contextPath}/AccommodationController">
									<input type="hidden" name="action2" value="update">
									<input type="hidden" name="accid" value="${acco.id }">
		                            <div class="mb-3">
		                                <label for="name" class="form-label">Accommodation Name:</label>
		                                <input type="text" class="form-control" id="name" name="name" value="${acco.name}">
		                            </div>
		                            
		                            <div class="mb-3">
		                                <label for="description" class="form-label">Description:</label>
		                                <textarea class="form-control" id="description" rows="5" name="description">${acco.description}</textarea>
		                            </div>
		                            
		                            <div class="mb-3">
		                                <label for="price" class="form-label">Price:</label>
		                                <input type="number" class="form-control" id="price" name="price" value="${acco.price}">
		                            </div>
		                            
		                            <div class="mb-3">
		                                <label for="roomID" class="form-label">Room Type:</label>
		                               <select name="roomID" id="" class="form-select">
		                                <option value="">Choose room type...</option>
		                                <c:forEach items="${rooms}" var="room">
		                                	<option value="${room.id}" ${room.id == acco.roomID ? 'selected' : '' }>${room.name }</option>
		                                </c:forEach>
		                               </select>
		                            </div>
		                            
		                            <div class="mb-3">
		                                <label for="placeID" class="form-label">Destination:</label>
		                                <select name="placeID" id="" class="form-select">
		                                <option value="">Choose destination type...</option>
		                                <c:forEach items="${places}" var="place">
		                                	<option value="${place.id}" ${place.id == acco.placeID ? 'selected' : '' }>${place.name}</option>
		                                </c:forEach>
		                               </select>
		                            </div>
		                            
		                            	<button type="submit" class="btn btn-primary">Update Accommodation</button>	
		                            	<a href="${pageContext.request.contextPath}/AdminController" class="btn btn-secondary">Back</a>	
								</form>
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
 <!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- SweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
$(document).ready(function() {
    // Form submission event handler
    $('#accommodationForm').submit(function(event) {
        event.preventDefault(); // Prevent default form submission
        
        // Show SweetAlert confirmation dialog
        Swal.fire({
            title: 'Are you sure?',
            text: 'Do you want to update this accommodation?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes'
        }).then((result) => {
            if (result.isConfirmed) {
                // If user confirms, continue with form submission
                $(this).off('submit').submit(); // Trigger form submission
            }
        });
    });
});
</script>



</body>
</html>