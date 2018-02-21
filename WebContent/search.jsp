<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Search</title>
</head>
<%-- Prevent secure pages from caching by the browser by setting some HTTP headers --%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.addHeader("Cache-Control","no-store");
response.addHeader("Cache-Control", "private");
response.addHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<body>
	<%@include file="header.html"%>
	<%
		// if session is valid and user's role is admin
		if (session.getAttribute("ownProfile") != null && session.getAttribute("role").equals("admin")) {
			// display buttons:
	%>
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<a href="home.jsp"><button type="button">Home</button></a>
		<form method="post" action="listAll">
			<input type="submit" name="listAll" value="List All Users">
		</form>
		<a href="search.jsp"><button type="button">Search</button></a> <a
			href="insert.jsp"><button type="button">Create Account</button></a>
	</div>
	<div id="error">
		<!-- Print Error Message if any -->
		<%
			String e = (String) request.getAttribute("ErrorMessage");
				if (e != null) {
		%>
		<br /><%=e%>
		<%
			} // end error
		%>
	</div>
	<div id="search">
		<h3>Search</h3>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="searchUser">
			<table id="search">
				<tr>
					<td>Search by:</td>
					<td class="spaceLeft"><select name="searchBy">
							<option value="lastName" selected>Last Name</option>
							<option value="department">Department</option>
					</select></td>
				</tr>
				<tr>
					<td>Value</td>
					<td class="spaceLeft"><input type="text" id="searchValue" value=""
						name="searchValue" size="30" maxlength="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed" required autofocus></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="Search"
						name="searchUser"></td>
				</tr>
			</table>
			<br />
			<br />

		</form>
	</div>
	<%
		} else { // no session

			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);

		}
	%>
	<%@include file="footer.html"%>
</body>
</html>