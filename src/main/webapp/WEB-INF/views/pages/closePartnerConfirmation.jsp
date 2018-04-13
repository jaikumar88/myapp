<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Check Close confirmation</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="generic-container">
	<form:form method="POST" modelAttribute="transaction" class="form-horizontal" >
			<form:input type="hidden" path="id" id="id"/>
		<div class="alert alert-success lead">
	    	Check can be closed please go ahead and close. If paid full amount then click on 
	    	Close button. If due then press pay latter.
		</div>
		
		<div class="row">
				<div class="form-actions floatRight">
				<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
							<td><a href="<c:url value='/close-partnerTrans-${transaction.id}' />" class="btn btn-success custom-width">Close Now</a></td>
				        </sec:authorize>
				</div>
		</div>
		</form:form>
	</div>
</body>

</html>