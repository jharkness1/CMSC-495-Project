
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


	<div id="home">
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
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
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>

		<!-- Add all page content inside this div if you want the side nav to push page content to the right (not used if you only want the sidenav to sit on top of the page -->
		<div id="main">
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
				<h2>
					Welcome
					<%=session.getAttribute("username")%>!
				</h2>
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
		
</body>
</div>
<%@include file="footer.html"%>

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