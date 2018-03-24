<nav>
	<ul id="menu">
		<li><a href="${pageContext.request.contextPath}/">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/newActivity">Add Activity</a></li>
		<li><a href="${pageContext.request.contextPath}/newCustomer">Add Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/listCustomer">List Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/listActivities">List Activities</a></li>
       	<li><a href="${pageContext.request.contextPath}/storeList">Store List</a></li>
	   	<li><a href="${pageContext.request.contextPath}/newstore">Add Store</a></li>
	   	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
      	 	<li><a href="${pageContext.request.contextPath}/userList">User List</a></li>
	   		<li><a href="${pageContext.request.contextPath}/newuser">Add User</a></li>
	   </sec:authorize>
	   
	   <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	</ul>
</nav>