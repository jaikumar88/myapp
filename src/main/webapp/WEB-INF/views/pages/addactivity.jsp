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
					<label class="col-md-3 control-lable" for="custId">Customer Name</label>
					<div class="col-md-7">
					<select name="custId" id="custId">
						<c:forEach items="${customers}" var="cust" varStatus="letterCounter">
						<option value="${cust.id}">${cust.firstName} ${cust.lastName}</option>
						</c:forEach>
					</select>
						<div class="has-error">
							<form:errors path="custId" class="help-inline"/>
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
					 <select name="activityType" id="activityType">
					    <option value="Payment">Payment</option>
					    <option value="Advance">Advance</option>
					    <option value="Received">Received</option>
					  </select>
						<div class="has-error">
							<form:errors path="activityType" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="intrestrate">Intrest Rate(%)</label>
					<div class="col-md-7">
						<form:input type="text" path="intrestrate" id="intrestrate" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="intrestrate" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="status">Status</label>
					<div class="col-md-7">
						<form:input type="text" path="status" id="status" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="status" class="help-inline"/>
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