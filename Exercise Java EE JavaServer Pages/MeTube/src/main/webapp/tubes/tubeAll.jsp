<%@ page import="metube.domain.models.viewModels.TubeAllViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<c:import url="/templates/head.jsp"/>
</head>
<body>
<main>
	<div class="jumbotron">
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<h1>All Tubes</h1>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<p>Check our tubes below!</p>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<% List<TubeAllViewModel> tubes = (List<TubeAllViewModel>) application.getAttribute("tubes"); %>
				<% if (!tubes.isEmpty()) { %>
				<ul>
					<% for (TubeAllViewModel tube : tubes) { %>
					<li><a href="/tubes/details?name=<%= tube.getName() %>"><%= tube.getName() %>
					</a></li>
					<% } %>
				</ul>
				<% } %>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<a href="/">Back to Home.</a>
			</div>
		</div>
	</div>
	<c:import url="/templates/footer.jsp"/>
</main>
</body>
</html>
