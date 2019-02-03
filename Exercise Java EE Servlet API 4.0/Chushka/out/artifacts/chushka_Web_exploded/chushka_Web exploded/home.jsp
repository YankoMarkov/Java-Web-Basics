<%@ page import="chushka.domain.models.views.AllProductViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Chushka</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	      integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	        crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	        crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"
	        integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em"
	        crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
	<main>
		<div class="jumbotron">
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<h1>Welcome to Chushka Application</h1>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<p>Check our products below!</p>
				</div>
			</div>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<% List<AllProductViewModel> products = (List<AllProductViewModel>) application.getAttribute("products"); %>
					<% if (!products.isEmpty()) { %>
					<ul>
						<% for (AllProductViewModel product : products) { %>
						<li><a href="/products/details?name=<%=product.getName() %>"><%=product.getName() %></a></li>
						<% } %>
					</ul>
					<% } %>
				</div>
			</div>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<a href="/products/create" class="btn btn-primary">Create Product</a>
				</div>
			</div>
		</div>
	</main>
</div>
</body>
</html>
