<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="telephone.js"></script>
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
<title>Create User Account</title>
</head>
<body background="s.jpg">
	<p id="demo"></p>
	<%@include file="header.html"%>
	<%-- If the session is active allow to logout or return to Home page --%>
	<%
		if (session.getAttribute("ownProfile") != null) {
	%>
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

	<%
		}
	%>


	<div id="createAccount">
	<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
		<div id="error">
		<!-- Print Error Message if any -->
		<%
			String e = (String) request.getAttribute("ErrorMessage");
			if (e != null) {
		%>
		<br /><%=e%>
		<%
			}
		%>
	</div>
	<div id="createForm">
		<h3>Create New Account</h3>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="insertUser">
			<table id="createAccount">
				<tr>
					<td>First Name:</td>
					<td class="spaceLeft"><input type="text" id="firstName"
						value="" name="firstName" size="30" maxlength="30"
						pattern="[A-Za-z ]*" title="Only letters allowed" required
						autofocus><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td class="spaceLeft"><input type="text" id="lastName"
						value="" name="lastName" size="30" maxlength="30"
						pattern="[A-Za-z' ]*" title="Only letters allowed" required><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td class="spaceLeft"><input type="email" id="email"
						name="email" value="" size="30" maxlength="30"
						title="myemail@domain.com" required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td class="spaceLeft"><input type="text" id="username"
						name="username" value="" size="30" maxlength="30"
						pattern="[A-Za-z-0-9]*" title="Only letters and numbers allowed"
						autocomplete='new-password' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Password:</td>
					<!-- At least 8 characters long! -->
					<td class="spaceLeft">
						<!-- This prevents auto-fill of username and password field in firefox -->
						<input type="text" style="display: none;"> <input
						oninput="this.setAttribute('type','password');" id="inputpassword"
						name="inputpassword" type="text" size="30" maxlength="30"
						pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font>
					</td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td class="spaceLeft"><input
						oninput="this.setAttribute('type','password');"
						id="inputpassword_confirm" name="inputpassword_confirm"
						type="text" size="30" maxlength="30"
						pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Company:</td>
					<td class="spaceLeft"><input type="text" id="company"
						name="company" value="" size="30" maxlength="30"
						pattern="[A-Za-z-0-9 ]*" title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td class="spaceLeft"><input type="text" id="department"
						name="department" value="" size="30" maxlength="30"
						pattern="[A-Za-z-0-9 ]*" title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Job Title:</td>
					<td class="spaceLeft"><input type="text" id="title"
						name="title" value="" size="30" maxlength="30"
						pattern="[A-Za-z-0-9 ]*" title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work Address:</td>
					<td class="spaceLeft"><input type="text" id="streetAddr"
						value="" name="streetAddr" size="50" maxlength="50"
						pattern="[A-Za-z-0-9.# ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work City:</td>
					<td class="spaceLeft"><input type="text" id="city" value=""
						name="city" size="30" maxlength="30" pattern="[A-Za-z ]*"
						title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work State:</td>
					<td class="spaceLeft"><input type="text" id="state" value=""
						name="state" size="2" maxlength="2" pattern="[A-Za-z]*"
						title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work Zip Code:</td>
					<td class="spaceLeft"><input type="text" id="zip" value=""
						name="zip" size="5" maxlength="5" pattern="[0-9]*"
						title="Only numbers allowed"></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td class="spaceLeft"><input type="text" id="phone" value=""
						name="phone" size="20" maxlength="12" pattern="[0-9-].{0,12}"
						title="Only 10 numbers allowed: 1011010000"
						onblur="addDashes(this);"></td>
				</tr>
				<tr>
					<td></td>
					<td class="spaceLeft"><input type="reset" value="Reset"
						name="Reset"> <input type="submit" value="Create Account"
						name="CreateAccount"></td>
				</tr>
			</table>
			<br />
		</form>
		<table>
			<tr>
				<td class="spaceCenter"><font color='red'>*</font> Required
					fields</td>
			</tr>
			<%
				if (session.getAttribute("ownProfile") == null) {
			%>
			<tr>
				<!--  return button -->
				<td class="spaceCenter"><a href="login.jsp"><button
							type="button">Return to log in page</button></a></td>
			</tr>
			<%
				}
			%>
		</table>
		<br /> <br />
		</div>
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