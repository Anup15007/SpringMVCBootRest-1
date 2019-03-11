<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Accounts</title>
<style type="text/css">
.details{
text-align:left;
table-layout: auto;
text-overflow: ellipsis;
background-color: aqua;
font-size:25px;
}
</style>
</head>
<body>
	<form action="allAccountDetailsPage1" method="post">
	<input type="submit" value="click">
	    <div align="left" class="details">
	        <s:forEach var="abc" items="${accounts}">
	            ${abc}<br>  <br>
	        </s:forEach>
	    </div>
	    <br><br><br><br><br>
	    <div align="center">
	    <a href="index">||BACK||</a>
	    </div>
    </form>
</body>
</html>

