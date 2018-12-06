<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Order Info</title>
   </head>

   <body>
      	<h3>Order Info:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getorders" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style3.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		
		<hr>
		<form action="/sw/CreateOrder.jsp">
    		<input type="submit" value="Create Order" />
		</form>
		<form action="/sw/DeleteOrder.jsp">
    		<input type="submit" value="Delete Order" />
		</form>
		<form action="/sw/UpdateOrder.jsp">
    		<input type="submit" value="Update Order" />
		</form>
		<form action="/sw/index.jsp">
    		<input type="submit" value="Home Page" />
		</form>
		
		
   </body>
</html>