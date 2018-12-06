<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Create Order</title>
   </head>

   <body>
      	<h3>Create Order:</h3>
      	<p>Enter Dates in the format of yyyy-mm-dd </p>
		<hr>
		
		<form action = "http://localhost:8080/sw/webapi/webservice/createorder" method = "post">
			CarId : <input type="text" name = "CarID">
			CustomerID : <input type="text" name = "CustomerID">
			StartDate : <input type="text" name = "Start">
			ReturnDate : <input type="text" name = "Return">
			<input type="submit">
		</form>

   </body>
</html>