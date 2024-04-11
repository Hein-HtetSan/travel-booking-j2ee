<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		
		<div class="row p-4">
			<p class="d-inline-flex gap-1">
				<a href="${pageContext.request.contextPath}/AdminController" class="btn btn-secondary">Back</a>
			  <a class="btn btn-danger" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">
			  	Canceled Package
			  </a>
			  <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">
			  	Success Packages
			  </button>
			  
			</p>
			<span class="fw-bold text-muted">Review your history and think off best strategy to get more customers.</span>
		</div>
		
	
		<div class="row">
			  <div class="col">
			    <div class="collapse multi-collapse" id="multiCollapseExample1">
			      <div class="card card-body">
			        <!--  for the cancel -->
			        <c:forEach items="${canceleds}" var="c">
			        	<div class="w-100 rounded shadow border p-3">
			        		<h5 class="fw-bold text-muted">${c.placeName}</h5>
			        		<h6 class="text-muted">User: ${c.userName}</h6>
			        		<h6 class="text-muted">Accommodation: ${c.accommodationName}</h6>
			        		
			        		<c:set var="cindate" value="${c.checkInDate}" />
							<fmt:parseDate var="date" value="${cindate}" pattern="yyyy-MM-dd" />
							<h6 class="text-muted">Date: ${date}</h6>
							<h6 class="text-danger">Package Canceled</h6>
			        	</div>
			        </c:forEach>
			        <!--  end of for the cancel -->
			      </div>
			    </div>
			  </div>
			  <div class="col">
			    <div 	 class="collapse multi-collapse" id="multiCollapseExample2">
			      <div class="card card-body">
			      		
			      		<!--  for the cancel -->
			        <c:forEach items="${successes}" var="c">
			        	<div class="w-100 rounded shadow border p-3">
			        		<h5 class="fw-bold text-muted">${c.placeName}</h5>
			        		<h6 class="text-muted">User: ${c.userName}</h6>
			        		<h6 class="text-muted">Accommodation: ${c.accommodationName}</h6>
			        		
			        		<c:set var="cindate" value="${c.checkInDate}" />
							<fmt:parseDate var="date" value="${cindate}" pattern="YYYY-dd" />
							<h6 class="text-muted">Date: ${date}</h6>
							<h6 class="text-success">Package Complete</h6>
			        	</div>
			        </c:forEach>
			        <!--  end of for the cancel -->
			      	
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