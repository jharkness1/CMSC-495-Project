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
<%-- Use javascript to implement PRG pattern --%>
<%-- Post-Redirect-Get pattern prevents duplicate post submissions --%>
<script LANGUAGE="JavaScript">
	if (window.history.replaceState) {
		window.history.replaceState(null, null, window.location.href);
	}
</script>
<body background="s.jpg">
	<%@include file="header.html"%>
	<%
		// if session is valid and user's role is admin
		if (session.getAttribute("ownProfile") != null && session.getAttribute("role").equals("admin")) {
			// display buttons:
	%>

	<div id="search">
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
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
	
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
						name="searchValue" size="30" maxlength="30" pattern="[A-Za-z-0-9' ]*"
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
	</div>
	<%
		} else { // no session

			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);

		}
	%>
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