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
		<%=Validator.validatePhone("2101122233")%></h3>
</body>
</html>