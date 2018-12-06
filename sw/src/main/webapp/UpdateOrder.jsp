<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Update Order</title>
   </head>

   <body>
      	<h3>Update Order:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getorders" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style3.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		<hr>
		<p>Please enter caustomerID and carID of order you wish to update</p>
		
		<form action = "http://localhost:8080/sw/webapi/webservice/updateorder" method = "post">
			CustomerId : <input type="text" name = "CustomerID">
			CarId : <input type="text" name = "CarID">
			Start Date : <input type="text" name = "StartDate">
			Return Date : <input type="text" name = "ReturnDate">
			<input type="submit">
		</form>
		
   </body>
</html>