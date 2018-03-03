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
	<%
		// if session is valid and user's role is admin
		if (session.getAttribute("ownProfile") != null && session.getAttribute("role").equals("admin")) {
			if (request.getAttribute("profileToDelete") != null) {
				// retrieve from request attribute profile of the user that is about to be deleted
				user = (UserProfile) request.getAttribute("profileToDelete");
	%>
	<!-- 	</div> -->
	<!-- Show buttons -->

	<div id="delete">
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
				<td class="spaceCenter"><a href="home.jsp"><button id="deletebtn"
							type="button">Cancel</button></a></td>
			</tr>
			<%
				} else { // if no request value was posted
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
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