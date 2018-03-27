<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:set var="letters" scope="session" value="${stores}"/>
<c:set var="totalCount" scope="session" value="${fn:length(stores)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Store Name</th>
				        <th>Store ID</th>
				        <th>Address </th>
				        <th>Country</th>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th ></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${stores}" var="store" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
						<td>${store.storeName}</td>
						<td>${store.storeId}</td>
						<td>${store.address}</td>
						<td>${store.country}</td>
					    <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
							<td><a href="<c:url value='/edit-store-${store.storeId}/${store.country}' />" >edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-store-${store.storeId}/${store.country}' />" >delete</a></td>
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
    		<a href="${pageContext.request.contextPath}/storeList?start=${pageStart - 10}">Previous</a>${pageStart + 1} - ${pageStart + 10} 
    		<a href="${pageContext.request.contextPath}/storeList?start=${pageStart + 10}">Next</a>  

