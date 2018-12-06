<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri="http://java.sun.com/jsp/jstl/xml" %>
 
<html>
   <head>
      <title>Customer Table</title>
   </head>

   <body>
      	<h3>Customer Table:</h3>
		<hr>
		<c:import var="xml" url="http://localhost:8080/sw/webapi/webservice/getcustomers" />
		<x:parse xml ="${xml}" var = "output" />
		<c:import url = "/style2.xsl" var = "xslt"/>
		<x:transform xml = "${output}" xslt = "${xslt}"/>
		
		<hr>
		<form action="/sw/CreateCustomer.jsp">
    		<input type="submit" value="Create Customer" />
		</form>
		<form action="/sw/DeleteCustomer.jsp">
    		<input type="submit" value="Delete Customer" />
		</form>
		<form action="/sw/UpdateCustomer.jsp">
    		<input type="submit" value="Update Customer" />
		</form>
		<form action="/sw/index.jsp">
    		<input type="submit" value="Home Page" />
		</form>
		
		
   </body>
</html>