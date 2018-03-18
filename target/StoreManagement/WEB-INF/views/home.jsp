<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>anatine - a free css template</title>
<link rel="stylesheet" href="/static/css/app.css" type="text/css" />

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>

<body>

		<section id="body" class="width">
			<aside id="sidebar" class="column-left">

			<header>
				<h1><a href="#">Store Management</a></h1>

				<h2>Welcome ${loggedinuser}</h2>
				
			</header>

			<nav id="mainnav">
  				<ul>
                            	 <li class="selected-item"><a href="<c:url value='/home'">Home</a></li>
                           		 <li><a href="<c:url value='/userList'">List Users</a></li>
                           		 <li><a href="<c:url value='/storeList'">List Users</a></li>
                           		 <li><a href="<c:url value='/roleList'">List Users</a></li>
                        	</ul>
			</nav>

			
			
			</aside>
			<section id="content" class="column-right">
                		
	    <article>
				
			
			<h2>Introduction to anatine</h2>
			<div class="article-info">Posted on <time datetime="2013-05-14">14 May</time> by <a href="#" rel="author">Joe Bloggs</a></div>

            <p>Welcome to anatine, a free premium valid CSS3 &amp; HTML5 web template from <a href="http://zypopwebtemplates.com/" title="ZyPOP">ZyPOP</a>. This responsive template is completely <strong>free</strong> to use permitting a link remains back to  <a href="http://zypopwebtemplates.com/" title="ZyPOP">http://zypopwebtemplates.com/</a>.</p>

<p>Should you wish to use this template unbranded you can buy a template license from our website for 8.00 GBP, this will allow you remove all branding related to our site, for more information about this see below.</p>	
            
            <p>This template has been tested in:</p>


            <ul class="styledlist">
                <li>Firefox</li>
                <li>Opera</li>
                <li>IE</li>
 		<li>Safari</li>
                <li>Chrome</li>
            </ul>

		<a href="#" class="button">Read more</a>
		<a href="#" class="button button-reversed">Comments</a>


		
		</article>
	
		<article class="expanded">

            		<h2>Buy unbranded</h2>
			<div class="article-info">Posted on <time datetime="2013-05-14">14 May</time> by <a href="#" rel="author">Joe Bloggs</a></div>

			
            <p>Purchasing a template license for 8.00 GBP (at time of writing around 12 USD) gives you the right to remove any branding including links, logos and source tags relating to ZyPOP. As well as waiving the attribution requirement, your payment will also help us provide continued support for users as well as creating new web templates. Find out more about how to buy at the licensing page on our website which can be accessed at <a href="http://zypopwebtemplates.com/licensing" title="template license">http://zypopwebtemplates.com/licensing</a></p>

<h3>Lorem lipsum</h3>

<p>Morbi fermentum condimentum felis, commodo vestibulum sem mattis sed. Aliquam magna ante, mollis vitae tincidunt in, malesuada vitae turpis. Sed aliquam libero ut velit bibendum consectetur. Quisque sagittis, est in laoreet semper, enim dui consequat felis, faucibus ornare urna velit nec leo. Maecenas condimentum velit vitae est lobortis fermentum. In tristique sem vitae metus ornare luctus tempus nisl volutpat. Integer et est id nisi tempus pharetra sagittis et libero.</p>


		<a href="#" class="button">Read more</a>
		<a href="#" class="button button-reversed">Comments</a>
		</article>

			
			<footer class="clear">
				<p>&copy; 2015 sitename. <a href="http://zypopwebtemplates.com/">Free CSS Templates</a> by ZyPOP</p>
			</footer>

		</section>

		<div class="clear"></div>

	</section>
	

</body>
</html>
