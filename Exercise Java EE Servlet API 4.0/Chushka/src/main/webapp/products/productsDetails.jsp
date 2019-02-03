<%@ page import="chushka.domain.models.views.AllProductViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="chushka.domain.models.views.ProductDetailsViewModel" %>
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
			<% ProductDetailsViewModel product = (ProductDetailsViewModel) application.getAttribute("product"); %>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<h1><%=product.getName() %></h1>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<p><%=product.getDescription() %></p>
				</div>
			</div>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<p><%=product.getType() %></p>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<a href="/">Back to Home page.</a>
				</div>
			</div>
		</div>
	</main>
</div>
</body>
</html>
