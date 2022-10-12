<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link rel="stylesheet" href="/css/candidateForm.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<% String msg = (String) request.getAttribute("error"); %> 
<div class="flex-container">
	<% if(msg.length() > 0) { %>
			<div id="alert" class="alert alert-danger alert-dismissible fade show alert-container" role="alert">
 			 <%=msg %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>
	<form action="/add-candidate-${jobId}" method="post" class="form-container needs-validation" novalidate enctype="multipart/form-data">
		<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Name</label>
		    <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Email</label>
		    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate email
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Contact No</label>
		    <input type="number" value="0" name="contactNo" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate contact number
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Education</label>
		    <input type="text" name="education" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate education
	  		</div>
	  	</div>
		<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Profile picture</label>
		    <input required="required" type="file" accept="image/*" name="profile" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	  		<div class="invalid-feedback">
	  			Please provide candidate profile picture
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Resume</label>
		    <input required="required" type="file" accept="application/pdf" name="file" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	  		<div class="invalid-feedback">
	  			Please provide candidate resume
	  		</div>
	  	</div>
	  	<div class="mb-3">
	  		<button class="btn btn-success">Add Candidate</button>
	  	</div>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/candidateForm.js"></script>
</body>
</html>