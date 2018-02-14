<%-- 
    Document   : login
    Created on : Feb 6, 2018, 12:22:08 PM
    Author     : Kyle Post
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.Validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
	<%@include file="header.html"%>
	<%-- Simulate Login Form --%>
	<div id="login">
		<h3>Login</h3>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="authenticate">
			<table id="login">
				<tr>
					<td>Username:</td>
					<td class="spaceLeft"><input type="text" id="username" value="" name="username"
						size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed" required autofocus></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td class="spaceLeft"><input type="password" id="password" name="password"
						value="" size="30" pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						required autocomplete='off'></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="Login"
						name="login"></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><a href="insert.jsp"><button
								type="button">Create User Account</button></a></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- Print Error Message if any -->
	<div id="error">
		<%
			String e = (String) request.getAttribute("ErrorMessage");
			if (e != null) {
		%>
		<br />
		<%=e%><br />
		<%
			}
		%>
	</div>
	<div>
		<h5>
			Privacy Disclaimer:<br /> This system is for the use of authorized
			users only.<br /> Individuals using this computer system without<br />
			authority, or in excess of their authority, are<br /> subject to
			having all of their activities on this<br /> system monitored and
			recorded by system personnel.
		</h5>

	</div>
	<%@include file="footer.html"%>
</body>
</html>