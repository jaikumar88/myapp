<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Page</title>
		<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>

	<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<c:url var="homeUrl" value="/home" />
						<form action="${homeUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<div class="form-actions">
								<a href="<c:url value='/userList' />" class="btn btn-block btn-primary btn-default">Users List</a>
							</div>
							<div class="form-actions">
							<br>
							
							</div>
							<div class="form-actions">
								<a href="<c:url value='/storeList' />" class="btn btn-block btn-primary btn-default" >Stores List</a>
							</div>
							<div class="form-actions">
							   <br>
							</div>
							<div class="form-actions">
								<a href="<c:url value='/home' />"  class="btn btn-block btn-primary btn-default">Roles List</a>
							</div>
							
							
						</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>