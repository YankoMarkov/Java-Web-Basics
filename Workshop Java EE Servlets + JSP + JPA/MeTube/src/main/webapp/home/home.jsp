<%@ page import="metube.domain.models.viewModels.tubes.TubeDetailsViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<c:import url="/templates/head.jsp"/>
</head>
<body>
<div class="container-fluid">
	<header>
		<c:import url="/templates/navbar.jsp"/>
	</header>
	<main>
		<% String username = (String) session.getAttribute("user"); %>
		<% Set<TubeDetailsViewModel> tubeModels = (Set<TubeDetailsViewModel>) request.getAttribute("tubeModel"); %>
		<hr class="my-2"/>
		<div class="text-center mt-3">
			<h4 class="h4 text-info">Welcome, <%= username %>
			</h4>
		</div>
		<hr class="my-4">
		<div class="container">
			<div class="row">
				<% for (TubeDetailsViewModel tubeModel : tubeModels) { %>
				<div class="col col-md-3 d-flex justify-content-center">
					<img src="https://img.youtube.com/vi/<%= tubeModel.getYouTubeLink() %>/default.jpg" alt="Image">
				</div>
				<% } %>
			</div>
		</div>
		<hr class="my-3"/>
	</main>
	<footer class="footer py-3">
		<c:import url="/templates/footer.jsp"/>
	</footer>
</div>
</body>
</html>
