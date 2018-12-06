<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Delete Car</title>
   </head>

   <body>
      	<h3>Delete Car:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getcars" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		<hr>
		<p>Please enter carID of car you wish to delete</p>
		<form action = "http://localhost:8080/sw/webapi/webservice/deletecar" method = "Post">
			CarId : <input type="text" name = "CarID">
			<input type="submit">
		</form>
		
   </body>
</html>