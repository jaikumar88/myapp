<nav>
	
	<ul id="menu">
		<li><a href="${pageContext.request.contextPath}/">Home</a></li>
       <li><a href="${pageContext.request.contextPath}/storeList">Store List</a></li>
	   <li><a href="${pageContext.request.contextPath}/newstore">Add Store</a></li>
	   <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
       <li><a href="${pageContext.request.contextPath}/userList">User List</a></li>
	   <li><a href="${pageContext.request.contextPath}/newuser">Add User</a></li>
	   </sec:authorize>
	   
	   <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	</ul>
</nav>