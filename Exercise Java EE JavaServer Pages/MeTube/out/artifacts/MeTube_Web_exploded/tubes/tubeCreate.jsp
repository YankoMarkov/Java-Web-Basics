<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<c:import url="/templates/head.jsp"/>
</head>
<body>
<main>
	<div class="jumbotron">
		<form action="/tubes/create" method="post">
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<h1>Create Tube!</h1>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12">
					<div class="row d-flex justify-content-center">
						<label for="name">Title:</label>
					</div>
					<div class="row d-flex justify-content-center">
						<input id="name" type="text" name="name">
					</div>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12">
					<div class="row d-flex justify-content-center">
						<label for="description">Description:</label>
					</div>
					<div class="row d-flex justify-content-center">
						<textarea id="description" name="description" cols="18" rows="4"></textarea>
					</div>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12">
					<div class="row d-flex justify-content-center">
						<label for="link">YouTube Link:</label>
					</div>
					<div class="row d-flex justify-content-center">
						<input id="link" type="url" name="link">
					</div>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12">
					<div class="row d-flex justify-content-center">
						<label for="uploader">Uploader:</label>
					</div>
					<div class="row d-flex justify-content-center">
						<input id="uploader" type="text" name="uploader">
					</div>
				</div>
			</div>
			<hr/>
			<div class="row">
				<div class="col col-md-12 d-flex justify-content-center">
					<button type="submit" class="btn btn-primary">Create Tube</button>
				</div>
			</div>
		</form>
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
