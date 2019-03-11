<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="i" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="form">
<form action="accountTransactions1" method="post">
Account Number:
<input type="text" name="accountNo"><br>
<input type="submit" value="click">
</form>
</div>
<i:forEach var="abc" items="${transactions}">
${abc}<br>
</i:forEach>
</body>
</html>