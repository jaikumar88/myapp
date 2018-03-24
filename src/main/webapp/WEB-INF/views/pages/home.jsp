<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:set var="letters" scope="session" value="${activities}"/>
<c:set var="totalCount" scope="session" value="${fn:length(activities)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
<li> Today's Transaction details</li>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Customer Name</th>
				        <th>Date</th>
				        <th>Activity Type </th>
				        <th>Details </th>
				        <th>Amount </th>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
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
					   
					</tr>
				</c:forEach>
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

