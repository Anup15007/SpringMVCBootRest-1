<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<h2>Welcome ${associate.firstName}&nbsp${associate.lastName}, you are successfully registered.</h2>
		<h2>AssociateId : ${associate.associateId}</h2>
	</div>
</body>
</html>