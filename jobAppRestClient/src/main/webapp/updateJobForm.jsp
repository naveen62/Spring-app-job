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
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />  
<div class="container" style="margin-top: 80px;">
<form action="updatejob" method="post">
	<input value="${job.jodId}" name="jodId" hidden="true">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 align-self-center">
				<div class="mb-3">
				  <label for="title" class="form-label">Job Title</label>
				  <input value="${job.title}" type="text" class="form-control" id="title" name="title" placeholder="Job title">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-6 align-self-center">
				<div class="mb-3">
				  <label for="description" class="form-label">Job Description</label>
				  <input value="${job.jobDesc}" type="text" class="form-control" id="description" name="jobDesc" placeholder="Job Description">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-6 align-self-center">
				<div class="mb-3">
				  <label for="experience" class="form-label">Experience Required</label>
				  <input value="${job.exp}" type="number" class="form-control" id="experience" name="exp" placeholder="Experience">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-6 align-self-center">
				<div class="mb-3">
				  <label for="role" class="form-label">Job Role</label>
				  <input value="${job.jobRole}" type="text" class="form-control" id="role" name="jobRole" placeholder="Job Role">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-6 align-self-center">
				<div>
					<button type="submit" class="btn btn-success">Update Job</button>
				</div>
			</div>
		</div>
	</div>
</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>