<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Search</title>
</head>
<body>
	<div id="error">
		<!-- Print Error Message if any -->
		<%
			String e = (String) request.getAttribute("ErrorMessage");
			if (e != null) {
		%>
		<center>
			<br />
			<font color="red"><%=e%></font><br /> <br />
		</center>
		<%
			}
		%>
	</div>
	<div id="search">
		<h2>Search</h2>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="searchUser">
			<table id="search">
				<tr>
					<td>Search by:</td>
					<td><select name="searchBy">
							<option value="lastName" selected>Last Name</option>
							<option value="department">Department</option>
					</select></td>
				</tr>
				<tr>
					<td>Value</td>
					<td><input type="text" id="searchValue" value=""
						name="searchValue" size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed" required autofocus><font
						color='red'>*</font></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="Search"
						name="searchUser"></td>
				</tr>
			</table>
			<font color='red'>*</font> Case sensitive!
		</form>
	</div>
</body>
</html>