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
    
        <form action="fundTransfer1" method="post">
        <table>
            <tr>
                <td>Enter your Account No.</td>
               <td><input name="accountNoFrom" size="30" /></td>
            </tr>
            <tr>
                <td>Enter the Amount</td>
                <td><input name="accountBalance" size="30" /></td>
            </tr>
			  <tr>
                <td>Enter the Pin Number</td>
                <td><input name="pinNumber" size="30" /></td>
            </tr>
             <tr>
                <td>Enter the Account No you want to sent</td>
                <td><input name="accountNoTo" size="30" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit" />
            </tr>
                </table>
        </form>

	     <div>
	    	ERROR : ${errorMessage}<br>
	    </div>
	    <div>
			<h2>fundTransferStatus :- ${fundTransferStatus}</h2><br>
	    </div>
</body>
</html>