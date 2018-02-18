<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="models.UserProfile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Edit Profile</title>
</head>
<body>
	<%@include file="header.html"%>
	<%!UserProfile user;%>
	<%-- Make sure that the session is active. If session is not active redirect to login --%>
	<%
		if (session.getAttribute("ownProfile") == null) { // if session is not valid return to login
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else { // active session
			if (request.getAttribute("userInfo") != null) {
				// retrieve the request attribute with user info
				user = (UserProfile) request.getAttribute("userInfo");
				// show logout button and Home button
	%>
	<div id="buttons" class="buttons">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<a href="home.jsp"><button type="button">Home</button></a>
	</div>
	<div id="updateProfile">
		<h3>Your Current Information</h3>
		<form method="post" action="updateProfile">
			<!-- Remember old username and email and post it as hidden values to compare them with new ones later -->
			<!-- Post also the id of user that is about to be updated -->
			<input type="hidden" name="oldUsername"
				value="<%=user.getUsername()%>"> <input type="hidden"
				name="oldEmail" value="<%=user.getEmail()%>"> <input
				type="hidden" name="idOfUpdatedUser" value="<%=user.getId()%>">
			<table id="editProfile">
				<tr>
					<td>First Name:</td>
					<td class="spaceLeft"><input type="text" id="firstName"
						value="<%=user.getFirstname()%>" name="firstName" size="30"
						pattern="[A-Za-z ]*" title="Only letters allowed" required
						autofocus><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td class="spaceLeft"><input type="text" id="lastName"
						value="<%=user.getLastname()%>" name="lastName" size="30"
						pattern="[A-Za-z ]*" title="Only letters allowed" required><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td class="spaceLeft"><input type="email" id="email" name="email"
						value="<%=user.getEmail()%>" size="30" title="myemail@domain.com"
						required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td class="spaceLeft"><input type="text" id="username" name="username"
						value="<%=user.getUsername()%>" size="30" pattern="[A-Za-z-0-9]*"
						title="Only letters and numbers allowed" required><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td>Password:</td>
					<!-- At least 8 characters long! -->
					<td class="spaceLeft"><input type="password" id="password" name="password"
						value="<%=user.getPassword()%>" size="30"
						pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td class="spaceLeft"><input type="password" id="password_confirm"
						name="password_confirm" value="<%=user.getPassword()%>" size="30"
						pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off' required><font color='red'>*</font></td>
				</tr>
				<tr>
					<td>Company:</td>
					<td class="spaceLeft"><input type="text" id="company" name="company"
						value="<%=user.getCompany()%>" size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td class="spaceLeft"><input type="text" id="department" name="department"
						value="<%=user.getDepartment()%>" size="30"
						pattern="[A-Za-z-0-9 ]*" title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Job Title:</td>
					<td class="spaceLeft"><input type="text" id="title" name="title"
						value="<%=user.getTitle()%>" size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work Address:</td>
					<td class="spaceLeft"><input type="text" id="streetAddr"
						value="<%=user.getWork_address()%>" name="streetAddr" size="50"
						pattern="[A-Za-z-0-9.# ]*"
						title="Only letters and numbers allowed"></td>
				</tr>
				<tr>
					<td>Work City:</td>
					<td class="spaceLeft"><input type="text" id="city"
						value="<%=user.getWork_city()%>" name="city" size="30"
						pattern="[A-Za-z ]*" title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work State:</td>
					<td class="spaceLeft"><input type="text" id="state"
						value="<%=user.getWork_state()%>" name="state" size="2"
						pattern="[A-Za-z]*" title="Only letters allowed"></td>
				</tr>
				<tr>
					<td>Work Zip Code:</td>
					<td class="spaceLeft"><input type="text" id="zip"
						value="<%=user.getWork_zip()%>" name="zip" size="5"
						pattern="[0-9]*" title="Only numbers allowed"></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td class="spaceLeft"><input type="text" id="phone" value="<%=user.getPhone()%>"
						name="phone" size="15" pattern="[0-9]*"
						title="Only numbers allowed"></td>
				</tr>

			</table>
			<br>
			<table>
				<tr>
					<td class="spaceCenter"><font color='red'>*</font> Required fields</td>
				</tr>
				<tr>
					<td class="spaceCenter"><a href="home.jsp"><button type="button">Cancel</button></a><input
						type="submit" value="Update" name="updateProfile"></td>
				</tr>
			</table>
			<br /> <br />

		</form>
	</div>
	<%
		} // end if userInfoForUpdate request attribute was not set
			else {
				// try to redirect to home page
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
		} // end if session was active
	%>
	<%@include file="footer.html"%>
</body>
</html>