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
<%-- Prevent secure pages from caching by the browser by setting some HTTP headers --%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.addHeader("Cache-Control","no-store");
response.addHeader("Cache-Control", "private");
response.addHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%-- Use javascript to implement PRG pattern --%>
<%-- Post-Redirect-Get pattern prevents duplicate post submissions --%>
<script LANGUAGE="JavaScript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);
	}
</script>
<body>
	<header id="header1">
		<h1>Company ABC.inc</h1>
	</header>
	<%
		if (session.getAttribute("ownProfile") != null) { // if session is valid redirect to home.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} else {
	%>

	<%-- Login Form --%>
	<div id="login">
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
		<h2>Please Login</h2>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="authenticate">
			<table id="login">
				<tr>
				<td class="spaceLeft" align="left">Username: </td>
					<td class="spaceLeft"><input type="text" id="usernameLogin"
						placeholder="Username" name="usernameLogin" size="30" maxlength="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed" required autofocus></td>
				</tr>
				<tr>
					<td class="spaceLeft" align="left">Password: </td>
					<td class="spaceLeft"><input type="password" id="passwordLogin"
						name="passwordLogin" placeholder="Password" size="30" maxlength="30" pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						required autocomplete='off'></td>

				<tr>
					<td class="spaceLeft"><input type="submit" id="loginbtn" value="Login"
						name="login"></td>


					<td class="spaceLeft"><a href="insert.jsp"><button  id="createbtn"
								type="button" align="right">Create Account</button></a></td>
			</tr>
			</table>
		</form>
		
		<div id="disclaimer">
		<h5>
			Privacy Disclaimer:<br /> This system is for the use of authorized
			users only.<br /> Individuals using this computer system without<br />
			authority, or in excess of their authority, are<br /> subject to
			having all of their activities on this<br /> system monitored and
			recorded by system personnel.
		</h5>

	</div>
	</div>
	

	<footer id="footer1">
		Copyright &#169; 2018 Company ABC.inc; Team Alpha
	</footer>
	<%
		}
	%>
</body>
</html>