<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
</head>
<body>
	    <h3>USERS</h3>
	<table>
		<tr>
			<td>ID</td>
			<th>Username</th>
			<th>Email</th>
			<th>Role</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.email}</td> 
				<td>${user.role}</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<br/>
	
<!-- below currently logged in user display-->
	<h3>Currently logged in as</h3>
	<table>
		<tr>
			<th> name:</th>
			<th> email:</th>
		</tr>
			<tr>
				<td>${loggedUser.username}</td>
				<td>${loggedUser.email}</td>  
			</tr>
	</table>

</body>
</html>