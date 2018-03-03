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
<%-- Prevent secure pages from caching by the browser by setting some HTTP headers --%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.addHeader("Cache-Control", "no-store");
	response.addHeader("Cache-Control", "private");
	response.addHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%-- Use javascript to implement PRG pattern --%>
<%-- Post-Redirect-Get pattern prevents duplicate post submissions --%>
<script LANGUAGE="JavaScript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);
	}
</script>
<body background="s.jpg">
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
	
	<%
		// if any results were found, display them
				if (results.size() > 0) {
	%>

	<div id="results">
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="home.jsp">Home</a>
			<form name="myform" method="post" action="logout">
				<span aria-hidden="true" data-icon="&#xe000;"
					onclick="myform.submit()"><a href="#">Logout</a></span>
			</form>

			<%
				// check user's role
							session = request.getSession(true);
							String role = (String) session.getAttribute("role");

							if (role.equals("admin")) {
								// show administrative options:
			%>
			<form name="myform1" method="post" action="listAll">
				<span aria-hidden="true" data-icon="&#xe000;"
					onclick="myform1.submit()"><a href="#">List All Users</a></span>
			</form>
			<a href="search.jsp">Search</a> <a href="insert.jsp">Create
				Account</a>
			<%
				} // end if user is admin
			%>

		</div>

		<!-- Use any element to open the sidenav -->
		<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;
			Menu</span>
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

		<h3>Results</h3>
		<table id="results">
			<tr>
				<th>Last Name</th>
				<th>First Name</th>
				<th>Email</th>
				<th>Department</th>
				<th>Username</th>
			</tr>
			<%
				for (UserProfile u : results) {
			%>
			<tr>
				<form method="post" action="accessProfile">
					<input type="hidden" name="id" value="<%=u.getId()%>">

					<td class="spaceCenter"><%=u.getLastname()%></td>
					<td class="spaceCenter"><%=u.getFirstname()%></td>
					<td class="spaceCenter"><%=u.getEmail()%></td>
					<td class="spaceCenter"><%=u.getDepartment()%></td>
					<td class="spaceCenter"><%=u.getUsername()%></td>
					<td class="spaceCenter"><input type="submit"
						value="Access Profile" name="accessProfile"></td>
				</form>
				<td class="spaceCenter"><form method="post"
						action="confirmDelete">
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
<script>
	/* Set the width of the side navigation to 250px */
	function openNav() {
		document.getElementById("mySidenav").style.width = "250px";
	}

	/* Set the width of the side navigation to 0 */
	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
	}
</script>
</html>