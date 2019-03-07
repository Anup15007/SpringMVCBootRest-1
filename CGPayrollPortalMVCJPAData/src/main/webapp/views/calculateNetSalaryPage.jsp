<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<form action="calculateNetSalary" method="post">
			<table>
				<tr>
					<td>Associate Id:</td>
					<td><input type="text" name="associateId"></td>
					<td><input type="submit" value="Click"></td>
					<td><a href="index">Home</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center" class="error">${errorMessage}</div>
	<br>
	<div align="center">
		<table>
			<tr>
				<td>Associate Net Salary : </td>
			</tr>
			<tr>
				<td>${netSalary }</td>
			</tr>
		</table>
	</div>
</body>
</html>