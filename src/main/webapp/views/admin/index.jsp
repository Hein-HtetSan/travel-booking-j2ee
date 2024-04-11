<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<style>
	#room-table, #country-table{
		height: 300px !important;
		overflow: hidden !important;
		overflow-y: scroll !important;
	}
</style>
<body>
    
    <div class="container-fluid">


        <!-- navbar  -->
        <div class="row bg-light px-3 py-2 shadow">
            <div class="col-12 d-flex align-items-center justify-content-between p-2 gap-3 w-100">
                <span class="fw-bold fs-4 text-primary">Travel Booking System Management</span>
                
                <div class="d-flex align-items-center justify-content-center gap-2">
                	<div class="dropdown">
					  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					    View a bunch of List
					  </a>
					  <ul class="dropdown-menu">
					    <li>
					    	<div class="list-group" id="list-tab" role="tablist">
			                    <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">
			                        <i class="fas fa-chart-line"></i> Dashboard
			                    </a>
			                    <a class="list-group-item list-group-item-action " id="list-profile-list" data-bs-toggle="list" href="#list-profile" role="tab" aria-controls="list-profile">
			                        <i class="fas fa-map-location"></i> Accommodation
			                    </a>
			                    <a class="list-group-item list-group-item-action " id="list-messages-list" data-bs-toggle="list" href="#list-messages" role="tab" aria-controls="list-messages">
			                        <i class="fa-solid fa-earth-americas"></i> Destination
			                    </a>
			                    <a class="list-group-item list-group-item-action " id="list-settings-list" data-bs-toggle="list" href="#list-settings" role="tab" aria-controls="list-settings">
			                        <i class="fas fa-folder"></i> Booking
			                    </a>
			                </div>
					    </li>
					  </ul>
					</div>
					
					<a href="${pageContext.request.contextPath}/HistoryController?page=forAdmin" class="btn btn-success">History</a>
					
	                <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
	                    <i class="fas fa-bars"></i> Menu
	                </button>
                </div>
            </div>
        </div>
        <!-- end of navbar  -->

        <!-- body  -->
        <div class="row mt-3">
            <div class="col-md-8 order-1 order-md-0">
                <!-- col-8 section  -->
                <div class="tab-content" id="nav-tabContent">

                    <!-- for dashboard  -->
                    <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">


                        <div class="row p-3 gap-2 d-flex align-items-center justify-content-center">
                            <div class=" col-3 card bg-primary p-3">
                                <span class="fs-5 fw-bold text-white"> <i class="fas fa-globe"></i> Destinations</span>
                                <hr>
                                <h5 class="text-light">${places.size()}</h5>
                             </div>
                             <div class="col-2 card bg-success p-3">
                                <span class="fs-5 fw-bold text-white"> <i class="fas fa-file"></i> Bookings</span>
                                <hr>
                                <h5 class="text-light">${bookings.size()}</h5>
                            </div>
                            <div class=" col-3 card bg-danger p-3">
                                <span class="fs-5 fw-bold text-white"> <i class="fas fa-dollar"></i> Accommodations</span>
                                <hr>
                                <h5 class="text-light">${accos.size()}</h5>
                            </div>
                        </div>
                        
                        <h6 class="text-muted">Action</h6>
                        <div class="border p-3 rounded shadow bg-light">
                        	<button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                  <i class="fas fa-plus"></i>  Create New Accommodation
                            </button>
                            <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#destination">
                                   <i class="fas fa-plus"></i> Create New Destination
                            </button>
                            <!-- Button to trigger modal -->
							<button class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#addRoomModal">
							  <i class="fas fa-plus"></i> Add Room
							</button>
                            <!-- Button to trigger modal -->
							<button class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#addStatesModal">
							  <i class="fas fa-plus"></i> Add States/Regions
							</button>
                        </div>

                    </div>

                    <!-- for accommodation  -->
                    <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
                        
                        <div class="row">
                            <div class="col-12 d-flex align-items-center justify-content-end p-3">
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    Create New Accommodation
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 px-5 py-3">
                            	<h4 class="text-muted">Accommodation List:</h4>
								<div style="height: 350px !important; overflow: hidden !important; overflow-y: scroll !important;">
								<table class="table">
                                    <thead>
                                      <tr>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Room Type</th>
                                        <th scope="col">Destination</th>
                                        <th scope="col">Action</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${accos}" var="acco">
                                      	<tr>
	                                        <th scope="row">${acco.name}</th>
	                                        <td>${acco.price} MMK</td>
	                                        <td>${acco.roomName}</td>
	                                        <td>${acco.placeName }</td>
	                                        <td>
	                                            <a href="${pageContext.request.contextPath}/AccommodationController?page=edit&accid=${acco.id}" class="btn btn-primary btn-sm" >
	                                                <i class="fas fa-pen"></i>
	                                            </a>
	                                        </td>
	                                      </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
								</div>
                            </div>
                        </div>

                    </div>

                    <!-- for destination  -->
                    <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                        
                        <div class="row">
                            <div class="col-12 d-flex align-items-center justify-content-end p-3">
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#destination">
                                    Create New Destination
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 px-5 py-3">
                            	<h4 class="text-muted">Destination List:</h4>
                                <div style="height: 350px !important; overflow: hidden !important; overflow-y: scroll !important;">
                                <table class="table">
                                    <thead>
                                      <tr>
                                        <th scope="col">Image</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Destination</th>
                                        <th scope="col">Action</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    	
                                      <c:forEach items="${places}" var="place">
                                      	<tr>
	                                        <th scope="row">
	                                         	<img src="${pageContext.request.contextPath }/assets/images/${place.image}" width="150">
	                                        </th>
	                                        <td>${place.name }</td>
	                                        <td>${place.countryName}</td>
	                                        <td>
	                                            <a href="${pageContext.request.contextPath}/DestinationController?page=edit&placeid=${place.id}" class="btn btn-primary btn-sm" >
	                                                <i class="fas fa-pen"></i>
	                                            </a>
	                                        </td>
	                                      </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- end of destination  -->

                    <!-- for booking list  -->
                    <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                        <div class="row">
                            <div class="col-12 px-5 py-3">
                            	<h5 class="text-muted fw-bold">Pending Booking List:</h5>
                                <div style="display: block; overflow:hidden; overflow-y: scroll; height: 400px;">
                                	<table class="table">
                                    <thead>
                                      <tr>
                                        <th scope="col">Customer</th>
                                        <th scope="col">Check in date</th>
                                        <th scope="col">Check out date</th>
                                        <th scope="col">Guests</th>
                                        <th scope="col">Payment</th>
                                        <th scope="col">Action</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    	<c:if test="${bookings.size() == 0}">
                                    		<h6 class="mt-3 text-danger">blank space of booking page!</h6>
                                    	</c:if>
                                      <c:forEach items="${bookings}" var="booking">
                                      	<tr>
	                                        <th scope="row">${booking.userName}</th>							
								
	                                        <td>
		                                        <c:set var="bookingCheckInDate" value="${booking.checkInDate}" />
												<fmt:parseDate var="checkInDate" value="${bookingCheckInDate}" pattern="yyyy-MM-dd" />
												<small class="d-block text-muted"><fmt:formatDate value="${checkInDate}" pattern="dd-MM-yyyy" /></small>
											</td>
	                                        <td>
	                                        	<c:set var="bookingCheckoutDate" value="${booking.checkoutDate}" />
												<fmt:parseDate var="checkoutDate" value="${bookingCheckoutDate}" pattern="yyyy-MM-dd" />
												<small class="d-block mb-2 text-muted" ><fmt:formatDate value="${checkoutDate}" pattern="dd-MM-yyyy" /></small>
	                                        </td>
	                                        <td>${booking.numberOfGuests}</td>
	                                        <td>
	                                        	<c:choose>
	                                        		<c:when test="${booking.paymentStatus == 0}">
	                                        			<small class="px-3 py-2 rounded-pill bg-warning text-light fw-bold">Pending</small>
	                                        		</c:when>
	                                        	</c:choose>
	                                        </td>
	                                        <td>
	                                            <c:choose>
												    <c:when test="${booking.status == 0}">
														<a href="#" class="btn btn-primary btn-sm btn-confirm" data-booking-id="${booking.id}">Confirm</a>
												        <a href="#" class="btn btn-danger btn-sm btn-cancel" data-booking-id="${booking.id}">Cancel</a>
												        <a href="${pageContext.request.contextPath}/BookingController?bookingid=${booking.id}&action=detail" class="btn btn-secondary btn-sm">
												            <i class="fas fa-circle-info"></i>
												        </a>
												    </c:when>
												    <c:when test="${booking.status == -1}">
												        <h6 class="text-danger">Booking canceled</h6>
												    </c:when>
												</c:choose>
	                                        </td>
	                                      </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                            </div>
                        </div>
                    
                    
                    </div>
                    <!-- end of booking list -->
                    
                    <!-- for room list  -->
                    <div class="accordion accordion-flush" id="accordionFlushExample">
					  <div class="accordion-item">
					    <h2 class="accordion-header">
					      <button class="accordion-button bg-light border fw-bold text-primary collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
					        See room lists & Region lists
					      </button>
					    </h2>
					    <div id="flush-collapseOne" class="accordion-collapse collapse mb-5" data-bs-parent="#accordionFlushExample">
					      
					      <div class="row">
                            <div class="col-6 px-5 py-3">  
                                <h4 class="text-muted fw-bold">Room Lists:</h4>
                                <div style="height: 300px !important; overflow: hidden !important; overflow-y: scroll !important;">
                                	<table class="table" id="room-table">
                                    <thead>
                                      <tr>
                                        <th scope="col">Room Type</th>
                                        <th scope="col">Action</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${rooms}" var="room">
                                      	<tr>
	                                        <td>${room.name}</td>
	                                        <td>
	                                            <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm">Edit</a>
	                                        </td>
	                                      </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                                  
                            </div>
                            <div class="col-6 px-5 py-3" >  
                                <h4 class="text-muted fw-bold">Region Lists:</h4>
                                <div style="height: 300px !important; overflow: hidden !important; overflow-y: scroll !important;">
                                	<table class="table" id="country-table"  >
                                    <thead>
                                      <tr>
                                        <th scope="col">Country Name</th>
                                        <th scope="col">Action</th>
                                      </tr>
                                    </thead>
                                    <tbody >
                                      <c:forEach items="${countries}" var="country">
                                      	<tr>
	                                        <td>${country.name}</td>
	                                        <td>
	                                            <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm">Edit</a>
	                                        </td>
	                                      </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
                                </div>
                                  
                            </div>
                        </div>
					      
					    </div>
					  </div>
					</div>
                    <!-- end of booking list -->


                </div>
                <!-- end of col-8 section  -->
            </div>

            <!-- for admin proflie  -->
            <div class="col-md-4 bg-light p-5">
                
                <div class="card p-3">
                    <div class="d-flex align-items-center justify-content-center">
                        <div id="avatarContainer"></div>
                    </div>
                    <div class="card-body">
                        <span class="d-block text-center fw-bold fs-5 text-primary">Admin</span>
                        <div class="d-flex align-items-center justify-content-between p-2">
                            <span class="fw-bold">Name</span> <span class="text-muted">${admin.name}</span>
                        </div>
                        <div class="d-flex align-items-center justify-content-between p-2">
                            <span class="fw-bold">Email</span> <span class="text-muted">${admin.email}</span>
                        </div>
                        <div class="d-flex align-items-center justify-content-between p-2">
                            <span class="fw-bold">Phone</span> <span class="text-muted">${admin.phone}</span>
                        </div>
                        <div class="d-flex align-items-center justify-content-center gap-3 p-2 mt-3">
                            <a href="${pageContext.request.contextPath}/LogoutController" class="btn btn-danger">Logout</a>
                        </div>
                    </div>
                </div>

            </div>
            <!-- end of admin profile    -->

        </div>
        <!-- end of body  -->

        

        <!-- ### aside bar  -->
        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

            <!-- offcanvas header  -->
            <div class="offcanvas-header">
              <h4 class="offcanvas-title text-primary" id="offcanvasExampleLabel">Menu</h4>
              <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>

            <!-- offancave body  -->
            <div class="offcanvas-body">
                <h6  class="fw-bold text-muted">Actions</h6>
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">
                        <i class="fas fa-chart-line"></i> Dashboard
                    </a>
                    <a class="list-group-item list-group-item-action " id="list-profile-list" data-bs-toggle="list" href="#list-profile" role="tab" aria-controls="list-profile">
                        <i class="fas fa-map-location"></i> Accommodation
                    </a>
                    <a class="list-group-item list-group-item-action " id="list-messages-list" data-bs-toggle="list" href="#list-messages" role="tab" aria-controls="list-messages">
                        <i class="fa-solid fa-earth-americas"></i> Destination
                    </a>
                    <a class="list-group-item list-group-item-action " id="list-settings-list" data-bs-toggle="list" href="#list-settings" role="tab" aria-controls="list-settings">
                        <i class="fas fa-folder"></i> Booking
                    </a>
                </div>

                <h6  class="fw-bold text-muted mt-5">Setting</h6>
                <a href="${pageContext.request.contextPath}/LogoutController" class="btn btn-danger mt-2"><i class="fas fa-arrow-left"></i> Logout</a>
            </div>
          </div>
        <!-- ### end of sidebar  -->

        <!-- ### modal  accommodation -->
          <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Create new accommodation</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="accommodationForm"  enctype="multipart/form-data">
                        	<input type="hidden" name="action" value="save">
                            <div class="mb-3">
                                <label for="name" class="form-label">Accommodation Name:</label>
                                <input type="text" class="form-control" id="name" name="name">
                            </div>
                            
                            <div class="mb-3">
                                <label for="description" class="form-label">Description:</label>
                                <textarea class="form-control" id="description" name="description"></textarea>
                            </div>
                            
                            <div class="mb-3">
                                <label for="price" class="form-label">Price:</label>
                                <input type="number" class="form-control" id="price" name="price">
                            </div>
                            
                            <div class="mb-3">
                                <label for="roomID" class="form-label">Room Type:</label>
                               <select name="roomID" id="" class="form-select">
                                <option value="">Choose room type...</option>
                                <c:forEach items="${rooms}" var="room">
                                	<option value="${room.id}">${room.name }</option>
                                </c:forEach>
                               </select>
                            </div>
                            
                            <div class="mb-3">
                                <label for="placeID" class="form-label">Destination:</label>
                                <select name="placeID" id="" class="form-select">
                                <option value="">Choose destination type...</option>
                                <c:forEach items="${places}" var="place">
                                	<option value="${place.id}">${place.name}</option>
                                </c:forEach>
                               </select>
                            </div>
                            
                            <button type="submit" class="btn btn-primary">Create Accommodation</button>
                        </form>
                    </div>
                </div>
                </div>
            </div>
        <!-- end of model  -->

        <!-- ### modal  destination -->
          <!-- Modal -->
          <div class="modal fade" id="destination" tabindex="-1" aria-labelledby="destinationLabel" aria-hidden="true">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <h1 class="modal-title fs-5" id="destinationLabel">Create new Destination</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="destinationForm" enctype="multipart/form-data">
                    	<input type="hidden" name="action" value="save" >
                        <div class="mb-3">
                            <label for="name" class="form-label">Destination Name:</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        
                        <div class="mb-3">
                            <label for="name" class="form-label">Price:</label>
                            <input type="number" class="form-control" id="price" name="price">
                        </div>
                        
                        <div class="mb-3">
                            <label for="description" class="form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description"></textarea>
                        </div>
                        
                        
                        
                        <div class="mb-3">
                            <label for="file" class="form-label">Image:</label>
                            <input type="file" name="file" class="form-control" id="file">
                        </div>
                        
                        <div class="mb-3">
                            <label for="roomID" class="form-label">Country:</label>
                           <select name="countryID" id="" class="form-select">
                            <option value="">Choose country...</option>
                            <c:forEach items="${countries}" var="country">
                            	<option value="${country.id}">${country.name}</option>
                            </c:forEach>
                           </select>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Create Destination</button>
                    </form>
                </div>
            </div>
            </div>
        </div>
        <!-- end of model  -->

		<!-- add room -->
		<!-- Modal -->
		<div class="modal fade" id="addRoomModal" tabindex="-1" aria-labelledby="addRoomModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="addRoomModalLabel">Add Room</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="addRoomForm" action="${pageContext.request.contextPath}/AdminController" method="POST">
		        	<input type="hidden" name="action" value="addRoom">
		          <div class="mb-3">
		            <label for="roomName" class="form-label">Room Type</label>
		            <input type="text" class="form-control" id="roomName" name="roomName" placeholder="Enter room name" required>
		          </div>
		          <button type="submit" class="btn btn-primary">Add</button>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		<!--  end of add room -->
		
		<!-- add state modal -->
		<!-- Modal -->
		<div class="modal fade" id="addStatesModal" tabindex="-1" aria-labelledby="addStatesModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="addStatesModalLabel">Add States/Regions</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="addStatesForm" method="POST" action="${pageContext.request.contextPath}/AdminController">
		        	<input type="hidden" name="action" value="addRegion">
		          <div class="mb-3">
		            <label for="stateName" class="form-label">State/Region Name</label>
		            <input type="text" class="form-control" id="stateName" name="stateName" placeholder="Enter state/region name" required>
		          </div>
		          <!-- Add more form fields as needed -->
		          <button type="submit" class="btn btn-primary">Add</button>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- end of state model -->

        <!-- modal for detail of booknig  -->
        <div class="modal fade" id="detailOfBooking" tabindex="-1" aria-labelledby="detailOfBookingLabel" aria-hidden="true">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <h1 class="modal-title fs-5" id="detailOfBookingLabel">Booking Detail</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <div class="row p-3"></div>

                </div>
            </div>
            </div>
        </div>
        <!-- end of detail of booknig  -->

    </div>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://cdn.jsdelivr.net/npm/@multiavatar/multiavatar/multiavatar.min.js"></script>
    <script>
        // Get a reference to the container
        var avatarContainer = document.getElementById('avatarContainer');

        // Generate a Multiavatar
        var avatarSvg = multiavatar('Binx Bond')

        // Create an SVG element
        var svgElement = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        svgElement.innerHTML = avatarSvg;

        // Append the SVG element to the container
        avatarContainer.appendChild(svgElement);
    </script>
    <!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function() {
    	
    	
    	
        // Add event listener to form submission
    	$("#destinationForm").submit(function(event) {
    	    // Prevent default form submission behavior
    	    event.preventDefault();

    	    // Create FormData object to handle multipart form data
    	    var formData = new FormData(this);
    	    
    	    if (formData.get("name") && formData.get("price") && formData.get("description") && formData.get("countryID")) {
    	        // Check if the file input field is not empty
    	        if (formData.get("file")) {
    	            // Send AJAX request
    	            $.ajax({
    	                type: "POST", // HTTP method
    	                url: "http://localhost:9095/testing_project/DestinationController", // URL to send the request
    	                data: formData, // FormData object containing form data
    	                processData: false, // Prevent jQuery from processing the data
    	                contentType: false, // Prevent jQuery from setting contentType
    	                success: function(response) {
    	                    // Handle successful response from the server
    	                    console.log("AJAX request successful!");
    	                    console.log("Response from server:", response);
    	                    if(response.status == 'true'){
    	                    	Swal.fire({
    	        	                title: "Success",
    	        	                text: "Create a new destination successfully.",
    	        	                icon: "success"
    	        	            }).then((result) => {
    	        	            	// Change the location href
    	        	                window.location.href = "http://localhost:9095/testing_project/AdminController";
    	        	            });
    	                    }else{
    	                    	Swal.fire({
    	        	                title: "Wrong",
    	        	                text: "Destination was already taken.",
    	        	                icon: "error"
    	        	            });
    	                    }
    	                },
    	                error: function(xhr, status, error) {
    	                    // Handle error response from the server
    	                    console.error("AJAX request error:", error);
    	                }
    	            });
    	        } else {
    	            Swal.fire({
    	                title: "Wrong",
    	                text: "Please select a file",
    	                icon: "error"
    	            });
    	        }
    	    } else {
    	        Swal.fire({
    	            title: "Wrong",
    	            text: "Please fill all the fields",
    	            icon: "error"
    	        });
    	    }
    	    
    	});
        
        
    	// Add event listener to form submission
    	$("#accommodationForm").submit(function(event) {
    	    // Prevent default form submission behavior
    	    event.preventDefault();

    	    // Create FormData object to handle multipart form data
    	    var formData = new FormData(this);
    	    formData.append("action2", "save");
    	    console.log(formData.get("action"));

    	    if (formData.get("name") && formData.get("description") && formData.get("roomID") && formData.get("price") && formData.get("placeID")) {
    	        // Send AJAX request
    	        $.ajax({
    	            type: "POST", // HTTP method
    	            url: "http://localhost:9095/testing_project/AccommodationController", // URL to send the request
    	            data: formData, // FormData object containing form data
    	            processData: false, // Prevent jQuery from processing the data
    	            contentType: false, // Let jQuery determine the content type automatically
    	            success: function(response) {
    	            	console.log("")
    	                // Handle successful response from the server
    	                console.log("AJAX request successful!");
    	                console.log("Response from server:", response.status);
    	                if (response.status == 'true') {
    	                    Swal.fire({
    	                        title: "Success",
    	                        text: "Create a new accommodation successfully.",
    	                        icon: "success"
    	                    }).then((result) => {
    	                        // Change the location href
    	                        window.location.href = "http://localhost:9095/testing_project/AdminController";
    	                    });
    	                } else {
    	                    Swal.fire({
    	                        title: "Wrong",
    	                        text: "Accommodation was already taken.",
    	                        icon: "error"
    	                    });
    	                }
    	            },
    	            error: function(xhr, status, error) {
    	                // Handle error response from the server
    	                console.error("AJAX request error:", error);
    	            }
    	        });
    	    } else {
    	        Swal.fire({
    	            title: "Wrong",
    	            text: "Please fill all the fields",
    	            icon: "error"
    	        });
    	    }
    	});
    	
    	// Event delegation for dynamically generated elements
        $(document).on('click', '.btn-confirm', function(event) {
            event.preventDefault();
            var bookingId = $(this).data('booking-id');
            confirmBooking(bookingId);
        });

        // Define the confirmBooking function
        function confirmBooking(bookingId) {
            // Show SweetAlert confirmation dialog
            Swal.fire({
                title: 'Are you sure?',
                text: 'Do you want to confirm this booking?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'OK'
            }).then((result) => {
                if (result.isConfirmed) {
                    // If user clicks "OK", redirect to BookingController with the specified parameters
                    window.location.href = "${pageContext.request.contextPath}/BookingController?bookingid=" + bookingId + "&action=confirm";
                }
            });
        }
        
     // Event delegation for dynamically generated elements
        $(document).on('click', '.btn-cancel', function(event) {
            event.preventDefault();
            var bookingId = $(this).data('booking-id');
            cancelBooking(bookingId);
        });

        // Define the confirmBooking function
        function cancelBooking(bookingId) {
            // Show SweetAlert confirmation dialog
            Swal.fire({
                title: 'Are you sure?',
                text: 'Do you want to call off this booking?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'OK'
            }).then((result) => {
                if (result.isConfirmed) {
                    // If user clicks "OK", redirect to BookingController with the specified parameters
                    window.location.href = "${pageContext.request.contextPath}/BookingController?bookingid=" + bookingId + "&action=calloff";
                }
            });
        }
        
        $('#addStatesForm').submit(function(event) {
            event.preventDefault(); // Prevent default form submission

            // Show SweetAlert confirmation dialog
            Swal.fire({
                title: 'Are you sure?',
                text: 'Do you want to add this state/region?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes'
            }).then((result) => {
                if (result.isConfirmed) {
                    // If user confirms, submit the form
                    $(this).off('submit').submit(); // Trigger form submission
                }
            });
        });
        
        $('#addRoomForm').submit(function(event) {
            event.preventDefault(); // Prevent default form submission

            // Show SweetAlert confirmation dialog
            Swal.fire({
                title: 'Are you sure?',
                text: 'Do you want to add this room type?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes'
            }).then((result) => {
                if (result.isConfirmed) {
                    // If user confirms, submit the form
                    $(this).off('submit').submit(); // Trigger form submission
                }
            });
        });



    });
</script>
    
    


</body>
</html>