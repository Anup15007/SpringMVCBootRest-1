<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
<style>
.error {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
    <table>
        <form:form action="openAccount" method="post" modelAttribute="account">
            <tr>
                <td>Savings or Current Account</td>
                <td><form:input path="accountType" size="30" /></td>
                <td><form:errors path="accountType" cssClass="error" />
            </tr>
            <tr>
                <td>accountBalance</td>
                <td><form:input path="accountBalance" size="30" /></td>
                <td><form:errors path="accountBalance" cssClass="error" />
            </tr>

            <tr>
                <td><input type="submit" value="Submit" />
            </tr>
        </form:form>
    </table>
     <div>
    	ERROR : ${errorMessage}<br>
    </div>
    <div>
    	<h2>Welcome <br>Ur Account has been opened </h2><br>
		<h2>accountNo :- ${account.accountNo}</h2><br>
		<h2>accountType :- ${account.accountType}</h2><br>
    </div>
</body>
</html>