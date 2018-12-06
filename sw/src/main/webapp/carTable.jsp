<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Car Info</title>
   </head>

   <body>
      	<h3>Car Info:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getcars" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		
		<hr>
		<form action="/sw/CreateCar.jsp">
    		<input type="submit" value="Create Car" />
		</form>
		<form action="/sw/DeleteCar.jsp">
    		<input type="submit" value="Delete Car" />
		</form>
		<form action="/sw/UpdateCar.jsp">
    		<input type="submit" value="Update Car" />
		</form>
		<form action="/sw/index.jsp">
    		<input type="submit" value="Home Page" />
		</form>
		
		
   </body>
</html>