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
<div class="flex-container">
	<form action="/candidate/update/${candidate.candidateId}/${jobId}" method="post" class="form-container needs-validation" novalidate>
		<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Name</label>
		    <input value="${candidate.name}" type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Email</label>
		    <input value="${candidate.email}" type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate email
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Contact No</label>
		    <input type="number" value="${candidate.contactNo}" name="contactNo" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate contact number
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Candidate Education</label>
		    <input value="${candidate.education}" type="text" name="education" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide candidate education
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