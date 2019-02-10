<%@ page import="metube.domain.models.viewModels.users.UserProfileViewModel" %>
<%@ page import="metube.domain.models.viewModels.tubes.TubeDetailsViewModel" %>
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
		<% UserProfileViewModel userProfileModel = (UserProfileViewModel) request.getAttribute("userProfileModel"); %>
		<hr class="my-2"/>
		<div class="text-center mt-3">
			<h4 class="text-info text-center"><%= userProfileModel.getUsername() %>
			</h4>
			<h4 class="text-info text-center"><%= userProfileModel.getEmail() %>
			</h4>
		</div>
		<hr>
		<div class="container-fluid">
			<div class="row d-flex flex-column">
				<table class="table table-hover">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Title</th>
						<th scope="col">Author</th>
						<th scope="col">Actions</th>
					</tr>
					</thead>
					<tbody>
					<% int num = 1; %>
					<% for (TubeDetailsViewModel tube : userProfileModel.getTubes()) { %>
					<tr>
						<td scope="col"><%= num++ %></td>
						<td scope="col"><%= tube.getTitle() %></td>
						<td scope="col"><%= tube.getAuthor() %></td>
						<td scope="col"><a href="/tube/details?tubeId=<%= tube.getId() %>">Details</a></td>
					</tr>
					<% } %>
					</tbody>
				</table>
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
