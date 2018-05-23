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



<c:set var="letters" scope="session" value="${salesreport}"/>
<c:set var="totalCount" scope="session" value="${fn:length(salesreport)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>


<div  class="well">
  <form:form method="POST"  class="form-horizontal">
 
  
		<div id="selectDiv">  Select start and end date </div>
					Start Date:<input type="date" id="startDate" name="startDate" class="input-sm" value='${startDate}' />
					End Date:<input type="date" id="endDate" name="endDate" class="input-sm" value='${endDate}' />
					
					<input type="submit" value="Submit" class="btn btn-primary btn-sm"/>
					
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Date</th>
				        <th>Sale Amount </th>
				        <th>Expenses </th>
				        <th>Due Amount </th>
				        <th>Total Products</th>
				        <th> Total Weight </th>
				        <th> Profit/Loss </th>
				</tr>
		    	</thead>
	    		<tbody>
	    		 <c:set var="totalSales" value="${0}" />
	    		 <c:set var="totalExp" value="${0}" />
	    		  <c:set var="totalDue" value="${0}" />
	    		  <c:set var="totalProduct" value="${0}" />
	    		  <c:set var="totalWeight" value="${0}" />
	    		  <c:set var="totalProfit" value="${0}" />
				<c:forEach items="${salesreport}" var="sales" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
					 <c:set var="totalSales" value="${totalSales + sales.saleAmount}" />
					 <c:set var="totalExp" value="${totalExp + sales.expenses}" />
					 <c:set var="totalDue" value="${totalDue + sales.dueAmount}" />
					 <c:set var="totalProduct" value="${totalProduct + sales.totalProducts}" />
					 <c:set var="totalWeight" value="${totalWeight + sales.totalWeight}" />
					  <c:set var="totalProfit" value="${totalProfit + sales.profitLoss}" />
						<td>${sales.reportDate}</td>
						<td>${sales.saleAmount}</td>
						<td>${sales.expenses}</td>
						<td>${sales.dueAmount}</td>
						<td>${sales.totalProducts}</td>
					    <td>${sales.totalWeight}</td>
					    <td>${sales.profitLoss}</td>
        				<td><a href="<c:url value='/sales-detail-${sales.reportDate}' />" class="btn btn-success custom-width">Details</a></td>
        				
					</tr>
				</c:forEach>
				  <tr>  <td> Totals </td>
				  		<td> ${totalSales}</td>
						<td>${totalExp} </td>
						<td>${totalDue}</td>
						<td>${totalProduct}</td>
					    <td>${totalWeight}</td>
					     <td>${totalProfit}</td>
					  </tr>
	    		</tbody>
	    	</table>
			<c:if test="${empty pageStart or pageStart < 0}">
       <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
       <c:set var="pageStart" value="${pageStart - 10}"/>
</c:if>
    <a href="${pageContext.request.contextPath}/salesReport?start=${pageStart - 10}">Previous</a>${pageStart + 1} - ${pageStart + 10} 
    <a href="${pageContext.request.contextPath}/salesReport?start=${pageStart + 10}">Next</a>  
    </form:form>
</div>

