<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Delete Customer</title>
   </head>

   <body>
      	<h3>Delete Customer:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getcustomers" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style2.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		<hr>
		<p>Please enter customerID of customer you wish to delete</p>
		<form action = "http://localhost:8080/sw/webapi/webservice/deletecustomer" method = "Post">
			customerId : <input type="text" name = "ID">
			<input type="submit">
		</form>
		
   </body>
</html>