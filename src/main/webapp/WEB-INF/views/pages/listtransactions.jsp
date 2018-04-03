<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

function myFun(loc_id){
	 customerList = new Array();
	var i = 0;
	var j = 0;
	var myDiv = document.getElementById("selectDiv");
	var loc_id = document.getElementById("locId").value;
	document.getElementById("custId").innerHTML = "";
	var select = document.getElementById("custId");
	
	var option = document.createElement("option");
    option.value = "";
    option.text = "-----Select----";
    select.appendChild(option);
	//alert(loc_id)
	<c:forEach var="customer" items='${customers}'>
	//alert("${customer.location}")alert("${customer.location == loc_id}")
    if (("${customer.location}" == loc_id))
    	{
    	//customerList[i++] = "${customer}";
    	
    	var option = document.createElement("option");
        option.value = "${customer.id}";
        option.text = "${customer.firstName}" + " ${customer.lastName}";
        select.appendChild(option);
    	}
  </c:forEach>
  
  
}
function updateDate(date){
	alert(date.value);
	document.getElementById("transDate").value = date.value;
}
</script>



<c:set var="letters" scope="session" value="${transactions}"/>
<c:set var="totalCount" scope="session" value="${fn:length(transactions)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
<c:set var="customerList" scope="session" value="${customerList}"/>

<div  class="well">
  <form:form method="POST"  class="form-horizontal">
 
  
		<div id="selectDiv">  Select the Location name and customer name </div>
					Gaun Ka naam:
					<select onchange="myFun(this)" name="locId" id="locId">
						<option value="">----Select----</option>
						<c:forEach items="${locations}" var="loc" varStatus="letterCounter">
							<option value="${loc.location}" ${loc.location == locId ? 'selected="selected"' : ''}>${loc.location}</option>
						</c:forEach>
					</select>
					Grahak Name:
					<select name="custId" id="custId">
					    <option value="">Select</option>
						<c:forEach items="${customerList}" var="cust" varStatus="letterCounter">
							<option value="${cust.id}" ${cust.id == custId ? 'selected="selected"' : ''}>${cust.firstName} ${cust.lastName}</option>
						</c:forEach>
					</select>
					
					Start Date:<input type="date" id="startDate" name="startDate" class="input-sm" value='${startDate}' />
					End Date:<input type="date" id="endDate" name="endDate" class="input-sm" value='${endDate}' />
					
					<input type="submit" value="listTransaction" class="btn btn-primary btn-sm"/>
					
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
				        
					</tr>
		    	</thead>
	    		<tbody>
	    		 <c:set var="totals" value="${0}" />
	    		 <c:set var="totalExp" value="${0}" />
	    		  <c:set var="dueTotal" value="${0}" />
				<c:forEach items="${transactions}" var="transaction" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
					 <c:set var="totals" value="${totals + transaction.totalAmount}" />
					 <c:set var="totalExp" value="${totalExp + transaction.totalExpense}" />
					 <c:set var="dueTotal" value="${dueTotal + transaction.dueAmount}" />
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
					</tr>
				</c:forEach>
				  <tr>  <td> Total </td>
				  		<td></td>
						<td> </td>
						<td></td>
						<td></td>
					    <td></td>
					     <td></td>
					     <td>${totals}</td>
					     <td></td>
					     <td></td>
					     <td></td>
					     <td>${totalExp}</td>
					     <td>${dueTotal}</td>
					     <td>
					     <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/download/trans.pdf?custID=${custId}' />" class="btn btn-success custom-width">Print</a></td>
        				</sec:authorize>
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

