
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="models.UserProfile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Profile Page</title>
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
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>
<body>
	<%@include file="header.html"%>
	<%!UserProfile user;%>
	<%-- Make sure that the session is active. If session is not active redirect to login --%>
	<%
		if (session.getAttribute("ownProfile") == null) { // if session is not valid return to login
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else { // active session
			// retrieve profile from the session attribute
			session = request.getSession(true);
			user = (UserProfile) session.getAttribute("ownProfile");
			// show logout button for everyone
	%>
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<%
			// check user's role
				session = request.getSession(true);
				String role = (String) session.getAttribute("role");

				if (role.equals("admin")) {
					// show administrative options:
		%>
		<form method="post" action="listAll">
			<input type="submit" name="listAll" value="List All Users">
		</form>
		<a href="search.jsp"><button type="button">Search</button></a> <a
			href="insert.jsp"><button type="button">Create Account</button></a>
		<%
			} // end if user is admin
		%>
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
	<div id="home">
	<h2>Welcome <%=session.getAttribute("username") %>!</h2>
		<h4>Your Profile Information</h4>

		<table id="profile">
			<tr>
				<td><b>First Name</b></td>
				<td class="spaceCenter"><%=user.getFirstname()%></td>
			</tr>
			<tr>
				<td><b>Last Name</b></td>
				<td class="spaceCenter"><%=user.getLastname()%></td>
			</tr>

			<tr>
				<td><b>Email</b></td>
				<td class="spaceCenter"><%=user.getEmail()%></td>
			</tr>
			<tr>
				<td><b>Company</b></td>
				<td class="spaceCenter"><%=user.getCompany()%></td>
			</tr>
			<tr>
				<td><b>Department</b></td>
				<td class="spaceCenter"><%=user.getDepartment()%></td>
			</tr>
			<tr>
				<td><b>Title</b></td>
				<td class="spaceCenter"><%=user.getTitle()%></td>
			</tr>
			<tr>
				<td><b>Work Address</b></td>
				<td class="spaceCenter"><%=user.getWork_address()%></td>
			</tr>
			<tr>
				<td><b>Work City</b></td>
				<td class="spaceCenter"><%=user.getWork_city()%></td>
			</tr>
			<tr>
				<td><b>Work State</b></td>
				<td class="spaceCenter"><%=user.getWork_state()%></td>
			</tr>
			<tr>
				<td><b>Work Zip Code</b></td>
				<td class="spaceCenter"><%=user.getWork_zip()%></td>
			</tr>
			<tr>
				<td><b>Phone</b></td>
				<td class="spaceCenter"><%=user.getPhone()%></td>
			</tr>
		</table>
		<br />
		<table>
			<tr>
				<td></td>
				<td class="spaceCenter"><form method="post"
						action="editProfile">
						<input type="hidden" name="id" value="<%=user.getId()%>">
						<input type="submit" value="Update" name="edit">
					</form></td>
			</tr>
			<%
				} // end if session was active
			%>
		</table>
		<br /> <br />
	</div>
	<%@include file="footer.html"%>
</body>
</html>