
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

<body>
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
	<div align="right">
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
		<a href="search.jsp"><button type="button">Search</button></a><br>
		<a href="insert.jsp"><button type="button">Create Account</button></a>
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
		<center>
			<br /> <font color="red"><%=e%></font><br /> <br />
		</center>
		<%
			} // end error
		%>
	</div>
	<h3 align="left">
		<font><strong>View Profile</strong></font>
		<!-- <button type="button" onclick=""
			style="float: right; height: 25px; width: 150px;">Logout</button> -->
	</h3>

	<table id="profile" align="left" cellpadding="6" cellspacing="15"
		border="0">
		<tr>
			<td><b>First Name</b></td>
			<td><%=user.getFirstname()%></td>
		</tr>
		<tr>
			<td><b>Last Name</b></td>
			<td><%=user.getLastname()%></td>
		</tr>

		<tr>
			<td><b>Email</b></td>
			<td><%=user.getEmail()%></td>
		</tr>
		<tr>
			<td><b>Company</b></td>
			<td><%=user.getCompany()%></td>
		</tr>
		<tr>
			<td><b>Department</b></td>
			<td><%=user.getDepartment()%></td>
		</tr>
		<tr>
			<td><b>Title</b></td>
			<td><%=user.getTitle()%></td>
		</tr>
		<tr>
			<td><b>Work Address</b></td>
			<td><%=user.getWork_address()%></td>
		</tr>
		<tr>
			<td><b>Work City</b></td>
			<td><%=user.getWork_city()%></td>
		</tr>
		<tr>
			<td><b>Work State</b></td>
			<td><%=user.getWork_state()%></td>
		</tr>
		<tr>
			<td><b>Work Zip Code</b></td>
			<td><%=user.getWork_zip()%></td>
		</tr>
		<tr>
			<td><b>Phone</b></td>
			<td><%=user.getPhone()%></td>
		</tr>
		<tr>
			<td></td>
			<!-- 			<td><button type="button" onclick="openPage('edit.jsp') " -->
			<!-- 					style="height: 25px; width: 150px;">Update</button></td> -->
			<td><form method="post" action="editProfile">
					<input type="hidden" name="id" value="<%=user.getId()%>"> <input
						type="submit" value="Update" name="edit">
				</form></td>
		</tr>
		<%
			} // end if session was active
		%>
	</table>
</body>
</html>