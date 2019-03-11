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
        <form:form action="accountDetailsPage1" method="post" modelAttribute="account">
            <tr>
                <td>Enter your Account No.</td>
                <td><form:input path="accountNo" size="30" /></td>
                <td><form:errors path="accountNo" cssClass="error" />
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
		<h4>Account No :- ${account.accountNo}</h4>
		<h4>Account Balance :- ${account.accountBalance}</h4>
		<h4>Account Type :- ${account.accountType}</h4>
		<h4>Account Status :- ${account.accountStatus}</h4>
		<h4>Account Pin Number :- ${account.pinNumber}</h4>
		
		
		
    </div>
</body>
</html>