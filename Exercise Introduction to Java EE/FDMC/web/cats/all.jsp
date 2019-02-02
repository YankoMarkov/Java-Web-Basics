<%@ page import="java.util.List" %>
<%@ page import="org.softuni.fdmc.data.Cat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>FDMC</title>
</head>
<body>
<div>
	<h1>All Cats!</h1>
	<hr/>
	<% List<Cat> cats = (List<Cat>) application.getAttribute("cats"); %>
	<% if (cats.isEmpty()) { %>
	<h2>There are no cats. <a href="/cats/create">Create some!</a></h2>
	<% } else { %>
	<% for (Cat cat : cats) { %>
	<h3>
		<a href="/cats/profile?name=<%=cat.getName() %>"><%=cat.getName() %></a>
	</h3>
	<% } %>
	<% } %>
	<a href="/">Back To Home</a>
</div>
</body>
</html>
