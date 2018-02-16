<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="models.UserProfile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Delete User</title>
</head>
<body>
	<%@include file="header.html"%>
	<%!UserProfile user;%>
	<%-- <%
		session = request.getSession(true);
		user = (UserProfile) session.getAttribute("ownProfile");
	%> --%>
	<%-- <div id="error">
		<!-- Print Error Message if any -->
		<%
			String e = (String) request.getAttribute("ErrorMessage");
			if (e != null) {
		%>
		<br /><%=e%>
		<%
			} // end error
		%> --%>
	<%
		// if session is valid and user's role is admin
		if (session.getAttribute("ownProfile") != null && session.getAttribute("role").equals("admin")) {
			// retrieve from request attribute profile of the user that is about to be deleted
			user = (UserProfile) request.getAttribute("profileToDelete");
	%>
	<!-- 	</div> -->
	<!-- Show buttons -->
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<a href="home.jsp"><button type="button">Home</button></a>
	</div>
	<div id="delete">
		<h3>Profile for Deletion</h3>
		<h4>Are you sure you want to permanently Delete this Profile?</h4>
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
				<td class="spaceCenter"><form method="post" action="deleteUser">
						<input type="hidden" name="id" value="<%=user.getId()%>">
						<input type="submit" value="Confirm Delete" name="delete">
					</form></td>
			</tr>
			<tr>
				<td></td>
				<td class="spaceCenter"><a href="home.jsp"><button
							type="button">Cancel</button></a></td>
			</tr>
			<%
				} else { // no session, redirect to login
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
			%>
		</table>
		<br /> <br />
	</div>
	<%@include file="footer.html"%>
</body>
</html>