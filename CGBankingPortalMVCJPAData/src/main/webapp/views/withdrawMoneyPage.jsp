<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Deposit Money</title>
<style>
.error {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
    <table>
        <form:form action="withdrawMoney1" method="post" modelAttribute="account">
            <tr>
                <td>Enter your Account No.</td>
                <td><form:input path="accountNo" size="30" /></td>
                <td><form:errors path="accountNo" cssClass="error" />
            </tr>
            <tr>
                <td>Enter the Amount</td>
                <td><form:input path="accountBalance" size="30" /></td>
                <td><form:errors path="accountBalance" cssClass="error" />
            </tr>
			  <tr>
                <td>Enter the Pin Number</td>
                <td><form:input path="pinNumber" size="30" /></td>
                <td><form:errors path="pinNumber" cssClass="error" />
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
		<h2>Account Balance :- ${updatedBalance}</h2><br>
    </div>
</body>
</html>