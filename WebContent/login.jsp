<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.Validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
	<%-- Simulate Login Form --%>
	<div id="login">
		<h2>Login</h2>
		<!-- Display a Form, validate input within the browser, by defining field types, accepted patterns -->
		<form method="post" action="authenticate">
			<table id="login">
				<tr>
					<td>Username:</td>
					<td><input type="text" id="username" value="" name="username"
						size="30" pattern="[A-Za-z-0-9 ]*"
						title="Only letters and numbers allowed" autofocus></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password"
						value="" size="30" pattern="[A-Za-z0-9._!@$].{7,}"
						title="At least 8 characters. Allowed special characters are: ._!@$"
						autocomplete='off'></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="Login"
						name="login"></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%-- <form method="post" action="listAll">
		<input type="submit" name="listAll" value="List All Users">
	</form>
	<h3>
		Password: 11111111
		<%=Validator.validatePassword("11111111")%></h3>
	<h3>
		Email: abc@!!$qqq.com
		<%=Validator.validateEmail("abc@!!$qqq.com")%></h3>
	<h3>
		Last Name: John Doe21
		<%=Validator.validateOnlyLetters("John Doe21")%></h3>
	<h3>
		username: John Doe21
		<%=Validator.validateOnlyLettersAndNumbers("John Doe21")%></h3>
	<h3>
		street: 23 Nice Road apt#!!!222
		<%=Validator.validateAddress("23 Nice Road apt<<!#222")%></h3>
	<h3>
		state: va
		<%=Validator.validateState("va")%></h3>
	<h3>
		zip: 2120a
		<%=Validator.validateZip("2120a")%></h3>
	<h3>
		phone: 2101122233
		<%=Validator.validatePhone("2101122233")%></h3> --%>
</body>
</html>