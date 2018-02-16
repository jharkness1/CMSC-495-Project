<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.UserProfile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Results</title>
</head>
<body>
	<%@include file="header.html"%>
	<%!ArrayList<UserProfile> results;%>
	<%
		// if session is valid and user's role is admin
		if (session.getAttribute("ownProfile") != null && session.getAttribute("role").equals("admin")) {
			// check if the request contains results
			if (request.getAttribute("results") != null) {
				// retrieve results from the request
				results = (ArrayList<UserProfile>) request.getAttribute("results");
				// display buttons:
	%>
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<a href="home.jsp"><button type="button">Home</button></a>
	</div>
	<%
		// if any results were found, display them
				if (results.size() > 0) {
	%>
	<div id="results">
		<h3>Results</h3>
		<table id="results">
			<tr>
				<th>ID</th>
				<th>Last Name</th>
				<th>First Name</th>
				<th>Email</th>
				<th>Department</th>
			</tr>
			<%
				for (UserProfile u : results) {
			%>
			<tr>
				<form method="post" action="accessProfile">
					<input type="hidden" name="id" value="<%=u.getId()%>">
				</form>
				<td class="spaceCenter"><%=u.getId()%></td>
				<td class="spaceCenter"><%=u.getLastname()%></td>
				<td class="spaceCenter"><%=u.getFirstname()%></td>
				<td class="spaceCenter"><%=u.getEmail()%></td>
				<td class="spaceCenter"><%=u.getDepartment()%></td>
				<td class="spaceCenter"><a href="profile.jsp"><input type="submit" value="Access Profile"
					name="accessProfile"></a></td>
				<td class="spaceCenter"><form method="post" action="confirmDelete">
						<input type="hidden" name="id" value="<%=u.getId()%>"> <input
							type="submit" value="Delete" name="delete">
					</form></td>
			</tr>
			<%
				} // end for loop
			%>
		</table>
		<br /> <br />
		<%
			} // end if attribure searchResults was set
					else {
		%>
		<h2>No results were found.</h2>
		<%
			}
				} else { // if results attribute was not set
					// try to redirect to home page
					RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
					dispatcher.forward(request, response);
				}

			} else { // no session, redirect to login

				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		%>
	</div>
	<%@include file="footer.html"%>
</body>
</html>