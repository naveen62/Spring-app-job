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
<% String msg = (String) request.getAttribute("auth-err"); %> 

<div class="flex-container">
<% if(msg.length() > 0) { %>
			<div id="alert" class="alert alert-danger alert-dismissible fade show alert-container" role="alert">
 			 <%=msg %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>
	<h1>Sign In</h1>
	<form action="/login" method="post" class="form-container needs-validation" novalidate>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Username</label>
		    <input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide username
	  		</div>
	  	</div>
	  	<div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Password</label>
		    <input type="password" name="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required">
	  		<div class="invalid-feedback">
	  			Please provide password
	  		</div>
	  	</div>
	  	<div class="mb-3">
	  		<button class="btn btn-success">Login</button>
	  	</div>
	  	<p>Register user <a href="/register">here</a></p>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/candidateForm.js"></script>
</body>
</html>