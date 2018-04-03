
<%@page import="java.text.SimpleDateFormat"%>
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
	
	<script type="text/javascript">
	
	function calculateTotalAmount(){
		var weight = document.getElementById("weight").value;
		var rate = document.getElementById("rate").value;
		document.getElementById("totalAmount").value = parseFloat(weight * rate);
		
	}

</script>
</head>

<body>
 		<div  class="well">
		<div class="none"> </div>
	 	<form:form method="POST" modelAttribute="partnertrans" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			<div class="row">
				<div class="form-group col-md-12" id="selectDiv">
					<label class="col-md-3 control-lable" for="locId">Partner : </label>
					<div class="col-md-7">
						<select name="partnerId" id="partnerId">
					    <option value="">Select</option>
						<c:forEach items="${partnerList}" var="partner" varStatus="letterCounter">
							<option value="${partner.id}" ${partner.id == partnerId ? 'selected="selected"' : ''}>${partner.firstName} ${partner.lastName}</option>
						</c:forEach>
					</select>
					
						
					</div>
				</div>	
		 
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="activityCreateDate">Creation Date(YYYY-MM-dd)</label>
					<div class="col-md-7">
						<form:input type="date" path="activityCreateDate" id="activityCreateDate" class="input-group-sm input-sm"  value='<%=(new SimpleDateFormat("YYYY-MM-dd")).format(new java.util.Date())%>' />
						<div class="has-error">
							<form:errors path="activityCreateDate" class="help-inline"/>
						</div>
					</div>
				</div>
			
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="productType">Product Type</label>
					<div class="col-md-7">
					 <select name="productType" id="productType">
					    <c:forEach items="${productList}" var="product" varStatus="letterCounter">
							<option value="${product.name}" ${product.name == productname ? 'selected="selected"' : ''}>${product.description}</option>
						</c:forEach>
					  </select>
						<div class="has-error">
							<form:errors path="productType" class="help-inline"/>
						</div>
					</div>
				</div>
			
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="weight">Weight(Kg Only)</label>
					<div class="col-md-7">
						<form:input type="text" path="weight" id="weight" class="input-group-sm input-sm" onchange="calculateTotalAmount()" value="0" required="true"/>
						<div class="has-error">
							<form:errors path="weight" class="help-inline"/>
						</div>
					</div>
					<label class="col-md-3 control-lable" for="rate">Rate per Kg </label>
					<div class="col-md-7">
						<form:input type="text" path="rate" id="rate" class="input-group-sm input-sm"  onchange="calculateTotalAmount()" value="0" required="true"/>
						<div class="has-error">
							<form:errors path="rate" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="quantity">No of Piece(10.5 bori)</label>
					<div class="col-md-7">
						<form:input type="text" path="quantity" id="quantity" class="input-group-sm input-sm" value="0"/>
						<div class="has-error">
							<form:errors path="quantity" class="help-inline"/>
						</div>
					</div>
					<label class="col-md-3 control-lable" for="totalAmount">Total Amount </label>
					<div class="col-md-7">
						<form:input type="text" path="totalAmount" id="totalAmount" class="input-group-sm input-sm" readonly="true"/>
						<div class="has-error">
							<form:errors path="totalAmount" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="status">Status</label>
					<div class="col-md-7">
						<select name="status" id="status">
					    <option value="Open">Open</option>
					    <option value="Close">Close</option>
					  </select>
						<div class="has-error">
							<form:errors path="status" class="help-inline"/>
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
							<input type="submit" value="Create" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
</div>
</body>
</html>