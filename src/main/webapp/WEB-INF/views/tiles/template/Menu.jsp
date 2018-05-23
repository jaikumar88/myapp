<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav>
	<ul id="menu">
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
		<li><a href="${pageContext.request.contextPath}/">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/newTransaction">Add Transaction</a></li>
		<li><a href="${pageContext.request.contextPath}/newActivity">Add Activity</a></li>
		<li><a href="${pageContext.request.contextPath}/newCustomer">Add Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/customerList">List Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/activityList">List Activities</a></li>
		<li><a href="${pageContext.request.contextPath}/transactionList">List Transactions</a></li>
       	<li><a href="${pageContext.request.contextPath}/partnerList">Adtiya List</a></li>
	   	<li><a href="${pageContext.request.contextPath}/newpartner">Add Adtiya</a></li>
	   	<li><a href="${pageContext.request.contextPath}/partnerTransList">List Partners Trans</a></li>
	   	<li><a href="${pageContext.request.contextPath}/newPartnerTrans">Add Partner Trans</a></li>
	   	<li><a href="${pageContext.request.contextPath}/locationList">List Location</a></li>
	   	<li><a href="${pageContext.request.contextPath}/newLocation">Add Location</a></li>
	   	<li><a href="${pageContext.request.contextPath}/salesReport">Sales Report</a></li>
	   	</sec:authorize>
	   	<sec:authorize access="hasRole('ADMIN')">
      	 	<li><a href="${pageContext.request.contextPath}/userList">User List</a></li>
	   		<li><a href="${pageContext.request.contextPath}/newuser">Add User</a></li>
	   </sec:authorize>
	   
	   <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	</ul>
</nav>