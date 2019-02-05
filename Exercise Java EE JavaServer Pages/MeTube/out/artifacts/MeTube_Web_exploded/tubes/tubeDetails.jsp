<%@ page import="metube.domain.models.viewModels.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<c:import url="/templates/head.jsp"/>
</head>
<body>
<main>
	<div class="jumbotron">
		<% TubeDetailsViewModel tube = (TubeDetailsViewModel) application.getAttribute("tubes"); %>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<h1><%= tube.getName() %>
				</h1>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<p><%= tube.getDescription() %></p>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-6 d-flex justify-content-center">
				<a href="<%= tube.getYouTubeLink() %>">Link to Video.</a>
			</div>
			<div class="col col-md-6 d-flex justify-content-center">
				<p><%= tube.getUploader() %></p>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col col-md-12 d-flex justify-content-center">
				<a href="/">Back to Home.</a>
			</div>
		</div>
	</div
	<c:import url="/templates/footer.jsp"/>
</main>
</body>
</html>
