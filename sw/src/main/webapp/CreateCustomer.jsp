<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Create Customer</title>
   </head>

   <body>
      	<h3>Create Customer:</h3>
		<hr>
		
		<form action = "http://localhost:8080/sw/webapi/webservice/createcustomer" method = "post">
			Id : <input type="text" name = "ID">
			Name : <input type="text" name = "Name">
			<input type="submit">
		</form>
		
		
		
		
   </body>
</html>