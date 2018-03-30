<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="letters" scope="session" value="${activities}"/>
<c:set var="totalCount" scope="session" value="${fn:length(activities)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
<c:set var="customerList" scope="session" value="${customerList}"/>

<div  class="well">
  <form:form method="POST"  class="form-horizontal">
			<div class="well lead">Please close all previous Due Transactions then you can close this check.</div>		
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Customer Name</th>
				        <th>Date</th>
				        <th>Activity Type </th>
				        <th>Details </th>
				        <th>Amount </th>
				        <th>Intrest</th>
				        <th> Status </th>
				        <sec:authorize access="hasRole('USER')  or hasRole('ADMIN')">
				        	<th >Close </th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
				        	<th >Edit </th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')  or hasRole('ADMIN')">
				        	<th >Delete</th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${activities}" var="activity" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
						<td>${activity.owner.firstName} ${activity.owner.lastName} </td>
						<td>${activity.activityCreateDate}</td>
						<td>${activity.activityType}</td>
						<td>${activity.memo}</td>
						<td>${activity.amount}</td>
					    <td>${activity.intrestAmount}</td>
					     <td>${activity.status}</td>
					     <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
							<td><a href="<c:url value='/close-activity-${activity.id}' />" class="btn btn-success custom-width">Close</a></td>
				        </sec:authorize>
					     <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/edit-activity-${activity.id}' />" >edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-activity-${activity.id}' />" >delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
				  <tr>  <td> Total Due </td>
				  		<td>${total}</td>
						<td>Total Intrest </td>
						<td>${totalIntrest}</td>
						<td></td>
					    <td></td>
					     <td></td>
					     <td></td>
					     <td></td>
	    		</tbody>
	    	</table>
			<c:if test="${empty pageStart or pageStart < 0}">
       <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
       <c:set var="pageStart" value="${pageStart - 10}"/>
</c:if>
    <a href="${pageContext.request.contextPath}/home?start=${pageStart - 10}">Previous</a>${pageStart + 1} - ${pageStart + 10} 
    <a href="${pageContext.request.contextPath}/home?start=${pageStart + 10}">Next</a>  
    </form:form>
</div>

