<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>FDMC</title>
</head>
<body>
<form action="/cats/create" method="post">
	<h1>Create a Cat!</h1>
	<label>
		Name:
		<input type="text" name="name">
	</label>
	<br/>
	<label>
		Breed:
		<input type="text" name="breed">
	</label>
	<br/>
	<label>
		Color:
		<input type="text" name="color">
	</label>
	<br/>
	<label>
		Number of legs:
		<input type="number" name="numberOfLegs">
	</label>
	<br/>
	<input type="submit" value="Create Cat">
	<br/>
	<a href="/">Back To Home</a>
</form>
</body>
</html>
