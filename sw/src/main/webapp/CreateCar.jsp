<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Create Car</title>
   </head>

   <body>
      	<h3>Create Car:</h3>
		<hr>
		
		<form action = "http://localhost:8080/sw/webapi/webservice/createcar" method = "post">
			CarId : <input type="text" name = "CarID">
			Make : <input type="text" name = "Make">
			Model : <input type="text" name = "Model">
			Price : <input type="text" name = "Price">
			<input type="submit">
		</form>
		
		
		
		
   </body>
</html>