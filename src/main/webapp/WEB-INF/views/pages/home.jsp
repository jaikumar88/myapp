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
<li> Today's Transaction details</li>
<div  class="well">
  <form:form method="POST"  class="form-horizontal">
  <input type="hidden" value="<%=new java.util.Date()%>" id="todayDate"/>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Customer Name</th>
				        <th>Date</th>
				        <th>Product Type </th>
				        <th>Weight </th>
				        <th>No Of Piece </th>
				        <th>Rate</th>
				        <th> Memo </th>
				        <th> Total Amount </th>
				        <th> Exp per piece </th>
				        <th> Other Exp </th>
				        <th> Deduction </th>
				        <th> Total Exp </th>
				        <th> Due Amt </th>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th >Edit </th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th >Delete</th>
				        </sec:authorize>
				        <th>Print</th>
				        <th>Close</th>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${transactions}" var="transaction" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
						<td>${transaction.customer.firstName} ${transaction.customer.lastName} </td>
						<td>${transaction.activityCreateDate}</td>
						<td>${transaction.productType}</td>
						<td>${transaction.weight}</td>
						<td>${transaction.quantity}</td>
					    <td>${transaction.rate}</td>
					     <td>${transaction.memo}</td>
					     <td>${transaction.totalAmount}</td>
					     <td>${transaction.expPerItem}</td>
					     <td>${transaction.otherExpense}</td>
					     <td>${transaction.deductionPercent}</td>
					     <td>${transaction.totalExpense}</td>
					     <td>${transaction.dueAmount}</td>
					     <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/edit-transaction-${transaction.id}' />" >edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-transaction-${transaction.id}' />" >delete</a></td>
        				</sec:authorize>
        				<td><a href="<c:url value='/download/trans.pdf?transId=${transaction.id}' />" class="btn btn-success custom-width">Print</a></td>
        				<td>
        				<c:if test="${transaction.status eq 'Open'}">
        						<a href="<c:url value='/close-check-${transaction.id}' />" class="btn btn-success custom-width">Close</a>
        				</c:if>
        				
        				</td>
					</tr>
				</c:forEach>
				  <tr>  <td> Total:</td>
				  		<td></td>
						<td></td>
						<td></td>
						<td></td>
					    <td></td>
					     <td></td>
					     <td>${totals}</td>
					     <td></td>
					     <td></td>
					     <td></td>
					     <td></td>
					     <td>${totalsDue}</td>
					     <td></td>
					     <td></td>
					     <td><a href="<c:url value='/download/trans.pdf?printAll=today' />" class="btn btn-success">All</a></td>
					     <td></td>
        				</tr>
	    		</tbody>
	    	</table>
			<c:if test="${empty pageStart or pageStart < 0}">
       <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
       <c:set var="pageStart" value="${pageStart - 10}"/>
</c:if>
    <a href="${pageContext.request.contextPath}/listTransaction?start=${pageStart - 10}">Previous</a>${pageStart + 1} - ${pageStart + 10} 
    <a href="${pageContext.request.contextPath}/listTransaction?start=${pageStart + 10}">Next</a>  
    </form:form>
</div>