<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:set var="letters" scope="session" value="${partners}"/>
<c:set var="totalCount" scope="session" value="${fn:length(partners)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Partner Name</th>
				        <th>Shop No</th>
				        <th>Phone No </th>
				        <th>Email</th>
				        <th>Amount Due</th>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${partners}" var="partner" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
						<td>${partner.firstName} ${partner.lastName}</td>
						<td>${partner.shopNo}</td>
						<td>${partner.phone}</td>
						<td>${partner.email}</td>
						<td>${partner.dueAmount}</td>
					    <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/edit-partner-${cust.id}' />" >edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-partner-${cust.id}' />" >delete</a></td>
        				</sec:authorize>
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
    <a href="${pageContext.request.contextPath}/partnerList?start=${pageStart - 10}">Previous</a>${pageStart + 1} - ${pageStart + 10} 
    <a href="${pageContext.request.contextPath}/partnerList?start=${pageStart + 10}">Next</a>  

