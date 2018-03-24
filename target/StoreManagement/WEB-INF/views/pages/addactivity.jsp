<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Store Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
 	<div class="generic-container">
		

		<div class="well lead">Add New Activity</div>
	 	<form:form method="POST" modelAttribute="activity" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="storeName">Customer Name</label>
					<div class="col-md-7">
						<form:input type="text" path="custName" id="custName" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="custName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
		<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="activityCreateDate">Creation Date(YYYY-MM-dd)</label>
					<div class="col-md-7">
						<form:input type="date" path="activityCreateDate" id="activityCreateDate" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="activityCreateDate" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="activityType">Activity Type</label>
					<div class="col-md-7">
						<form:input type="text" path="activityType" id="activityType" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="activityType" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="memo">Details </label>
					<div class="col-md-7">
						<form:input type="text" path="memo" id="memo" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="memo" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="amount">Amount(100.00) </label>
					<div class="col-md-7">
						<form:input type="text" path="amount" id="amount" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="amount" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/home' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>