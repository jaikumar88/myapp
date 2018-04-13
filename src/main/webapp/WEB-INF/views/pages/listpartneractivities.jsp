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
</script>



<c:set var="letters" scope="session" value="${activities}"/>
<c:set var="totalCount" scope="session" value="${fn:length(activities)}"/>
<c:set var="perPage" scope="session"  value="10"/>
<c:set var="pageStart" value="${param.start}"/>
<c:set var="customerList" scope="session" value="${customerList}"/>

<div  class="well">
  <form:form method="POST"  class="form-horizontal">
		<div id="selectDiv">  Select the Location name and customer name </div>
		
					
					<select name="partnerId" id="partnerId">
					    <option value="">Select</option>
						<c:forEach items="${partnerList}" var="partner" varStatus="letterCounter">
							<option value="${partner.id}" ${partner.id == partnerId ? 'selected="selected"' : ''}>${partner.firstName} ${partner.lastName}</option>
						</c:forEach>
					</select>
					
					<input type="date" id="startDate" name="startDate" class="input-sm" value='<%=(new SimpleDateFormat("YYYY-MM-dd")).format(new java.util.Date())%>' />
					
					<input type="date" id="endDate" name="endDate" class="input-sm" value='<%=(new SimpleDateFormat("YYYY-MM-dd")).format(new java.util.Date())%>' onchange="validateDate(this)"/>
					
					
					<c:if test="${not empty transaction}">
					
					<a href="<c:url value='/addPartnerActivity-${transaction.id}' />" class="btn btn-success custom-width">Add Activity</a>
					<a href="<c:url value='/home' />" class="btn btn-success custom-width">Cancel</a>
					</c:if>
					<c:if test="${empty transaction}">
					<input type="submit" value="List Activity" class="btn btn-primary btn-sm"/>
					</c:if>
					
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Partner Name</th>
				        <th>Date</th>
				        <th>Activity Type </th>
				        <th>Details </th>
				        <th>Amount </th>
				        <th>Intrest</th>
				        <th> Status </th>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th >Close </th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th >Edit </th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th >Delete</th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
	    		<c:set var="amountTotals" value="${0}" />
	    		 <c:set var="totalIntrest" value="${0}" />

				<c:forEach items="${activities}" var="activity" varStatus="letterCounter"
                        begin="${pageStart}" end="${pageStart + perPage - 1}">
					<tr>
					  <c:if test="${activity.status eq 'Open' }">
					  <c:set var="amountTotals" value="${amountTotals + activity.amount}" />
					  <c:set var="totalIntrest" value="${totalIntrest + activity.intrest}" />
					  </c:if>
						<td>${activity.owner.firstName} ${activity.owner.lastName} </td>
						<td>${activity.activityCreateDate}</td>
						<td>${activity.activityType}</td>
						<td>${activity.memo}</td>
						<td>${activity.amount}</td>
					    <td>${activity.intrest}</td>
					     <td>${activity.status}</td>
					     <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
							<td><a href="<c:url value='/closeActivity-${activity.id}' />" class="btn btn-success custom-width">Close</a></td>
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
				       <c:if test="${not empty transaction}">
				       <td>${transaction.totalAmount - amountTotals}</td>
				       <td> </td>
						<td></td>
				       </c:if>
				       <c:if test="${empty transaction}">
				  		<td>${amountTotals + totalIntrest}</td>
				  		<td>Total Intrest </td>
						<td>${totalIntrest}</td>
				  		</c:if>
						
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

