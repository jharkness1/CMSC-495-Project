<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Create User Account</title>
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
	<%-- If the session is active allow to logout or return to Home page --%>
	<%
		if (session.getAttribute("ownProfile") != null) {
	%>
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<a href="home.jsp"><button type="button">Home</button></a>
	</div>
	<%
		}
	%>

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
	<div id="createAccount">
		<h3>Create New Account</h3>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="insertUser">
			<table id="createAccount">
				<tr>
					<td>First Name:</td>
					<td class="spaceLeft"><input type="text" id="firstName" value=""
						name="firstName" size="30" pattern="[A-Za-z ]*"
						title="Only letters allowed" required autofocus><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td class="spaceLeft"><input type="text" id="lastName" value="" name="lastName"
						size="30" pattern="[A-Za-z ]*" title="Only letters allowed"
						required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td class="spaceLeft"><input type="email" id="email" name="email" value=""
						size="30" title="myemail@domain.com" required><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td class="spaceLeft"><input type="text" id="username" name="username" value=""
						size="30" pattern="[A-Za-z-0-9]*"
						title="Only letters and numbers allowed" required><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Password:</td>
					<!-- At least 8 characters long! -->
					<td class="spaceLeft"><input type="password" id="password" name="password"
						value="" size="30" pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td class="spaceLeft"><input type="password" id="password_confirm"
						name="password_confirm" value="" size="30"
						pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Company:</td>
					<td class="spaceLeft"><input type="text" id="company" name="company" value=""
						size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td class="spaceLeft"><input type="text" id="department" name="department"
						value="" size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Job Title:</td>
					<td class="spaceLeft"><input type="text" id="title" name="title" value=""
						size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work Address:</td>
					<td class="spaceLeft"><input type="text" id="streetAddr" value=""
						name="streetAddr" size="50" pattern="[A-Za-z-0-9.# ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work City:</td>
					<td class="spaceLeft"><input type="text" id="city" value="" name="city"
						size="30" pattern="[A-Za-z ]*" title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work State:</td>
					<td class="spaceLeft"><input type="text" id="state" value="" name="state"
						size="2" pattern="[A-Za-z]*" title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work Zip Code:</td>
					<td class="spaceLeft"><input type="text" id="zip" value="" name="zip" size="5"
						pattern="[0-9]*" title="Only numbers allowed"></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td class="spaceLeft"><input type="text" id="phone" value="" name="phone"
						size="15" pattern="[0-9]*" title="Only numbers allowed"></td>
				</tr>
				<tr>
					<td></td>
					<td class="spaceLeft"><input type="reset" value="Reset" name="Reset"> <input
						type="submit" value="Create Account" name="CreateAccount"></td>
				</tr>
			</table>
			<br />
		</form>
		<table>
			<tr>
				<td class="spaceCenter"><font color='red'>*</font> Required fields</td>
			</tr>
			<tr>
				<!--  return button -->
				<td class="spaceCenter"><a href="login.jsp"><button type="button">Return
							to log in page</button></a></td>
			</tr>
		</table>
		<br /> <br />
	</div>
	<%@include file="footer.html"%>
</body>
</html>