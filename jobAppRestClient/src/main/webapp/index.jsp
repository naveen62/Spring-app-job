<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="com.spring.jobappclient.model.Job"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link rel="stylesheet" href="/css/index.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />  
<% String msg = (String) request.getAttribute("success"); %>

<div class="page-container">
<% if(msg.length() > 0) { %>
	<div class="alert alert-success alert-dismissible fade show alert-container" role="alert">
  <%=msg %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>
<form action="/search-job" method="post" class="search-form needs-validation" novalidate>
	<div class="input-group search-input">
  		<input value="${search}" type="text" name="search" class="form-control" aria-label="Text input with checkbox" placeholder="Search Job/role" required="required">
		<div class="invalid-tooltip">
  			Please enter keyword for search
	  	</div>
		<a data-bs-toggle="tooltip" data-bs-title="Default tooltip" href="/" id="search-clear" class="btn btn-outline-secondary">X</a>
	</div>
	<div class="input-group search-btn">
		<button id="search-btn" type="submit" class="btn btn-primary">Search</button>
	</div>
</form>
<% List<Job> jobList = (List<Job>) request.getAttribute("jobs"); %>
<% if(jobList.size() == 0) { %>
	<h3 class="alert-header">No jobs found</h3>
<% } %>
<% for(int i=0;i<jobList.size();i++) {%>
		<div class="card-container">
				<div class="card-flex">
					 <div>
					    <h5 class="card-title"><%= jobList.get(i).getTitle() %> </h5>
					    <h6 class="card-subtitle mb-2 text-muted"><%= jobList.get(i).getjobRole() %></h6>
					    <a href="/<%=jobList.get(i).getJodId() %>" class="card-link">View Details</a>
	 				 </div>
	 				 <div>
	 				 	<a href="/delete-job/<%=jobList.get(i).getJodId()%>" class="link-danger card-links">Delete</a>  <a href="/<%=jobList.get(i).getJodId()%>-update-job" class="link-warning card-links">Edit</a>
	 				 </div>
				</div>
		</div>
<%  } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/index.js"></script>
</body>
</html>