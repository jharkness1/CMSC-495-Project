
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="db.DBConnector"%>
<%@page import="db.DBCredentials"%>
<%@page import="models.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Profile Page</title>
</head>

<body>

	<%-- <%
		String id = request.getParameter("userId");
		String driverName = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverName).newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	%>

	<%
		try {
			Connection connection = new DBConnector().getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT firstname,lastname,email,company,department,title,work_address,work_city,work_state,work_zip,phone FROM profiles WHERE id='1'";

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
	%> --%>


	<%-- Make sure that the session is active. If session is not active redirect to login --%>
	<%
		if (session.getAttribute("ownProfile") == null) { // if session is not valid return to login
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else { // active session

			// check user's role
			session = request.getSession(true);
			String role = (String) session.getAttribute("role");

			if (role.equals("admin")) {
				// show administrative options:
	%>
	<div align="right">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
		<form method="post" action="listAll">
			<input type="submit" name="listAll" value="List All Users">
		</form>
		<a href="search.jsp"><button type="button">Search</button></a></br> 
		<a href="insert.jsp"><button type="button">Create Account</button></a>
	</div>
	<%
		} // end if user is admin
			else { // if user is not admin, show only logout button
	%>
	<div align="right">
		<form method="post" action="logout">
			<input type="submit" name="logout" value="Logout">
		</form>
	</div>
	<%
		} // end if user is not an admin
				// if (request.getAttribute("profile") != null) {
			session = request.getSession(true);
			UserProfile user = (UserProfile) session.getAttribute("ownProfile");
	%>

	<h3 align="left">
		<font><strong>View Profile</strong></font>
		<!-- <button type="button" onclick=""
			style="float: right; height: 25px; width: 150px;">Logout</button> -->
	</h3>

	<table id="profile" align="left" cellpadding="6" cellspacing="15"
		border="0">

		<!-- <tr>
			<form method="post" action="updateProfile">
				<input type="hidden" name="id" value=1>
			</form> -->
		<tr>
			<td><b>First Name</b></td>
			<%-- 			<td><%=resultSet.getString("firstname")%></td> --%>
			<td><%=user.getFirstname()%></td>
		</tr>
		<tr>
			<td><b>Last Name</b></td>
			<%-- 			<td><%=resultSet.getString("lastname")%></td> --%>
			<td><%=user.getLastname()%></td>
		</tr>

		<tr>
			<td><b>Email</b></td>
			<%-- 			<td><%=resultSet.getString("email")%></td> --%>
			<td><%=user.getEmail()%></td>
		</tr>
		<tr>
			<td><b>Company</b></td>
			<%-- 			<td><%=resultSet.getString("company")%></td> --%>
			<td><%=user.getCompany()%></td>
		</tr>
		<tr>
			<td><b>Department</b></td>
			<%-- 			<td><%=resultSet.getString("department")%></td> --%>
			<td><%=user.getDepartment()%></td>
		</tr>
		<tr>
			<td><b>Title</b></td>
			<%-- 			<td><%=resultSet.getString("title")%></td> --%>
			<td><%=user.getTitle()%></td>
		</tr>
		<tr>
			<td><b>Work Address</b></td>
			<%-- 			<td><%=resultSet.getString("work_address")%></td> --%>
			<td><%=user.getWork_address()%></td>
		</tr>
		<tr>
			<td><b>Work City</b></td>
			<%-- 			<td><%=resultSet.getString("work_city")%></td> --%>
			<td><%=user.getWork_city()%></td>
		</tr>
		<tr>
			<td><b>Work State</b></td>
			<%-- 			<td><%=resultSet.getString("work_state")%></td> --%>
			<td><%=user.getWork_state()%></td>
		</tr>
		<tr>
			<td><b>Work Zip Code</b></td>
			<%-- 			<td><%=resultSet.getString("work_zip")%></td> --%>
			<td><%=user.getWork_zip()%></td>
		</tr>
		<tr>
			<td><b>Phone</b></td>
			<%-- 			<td><%=resultSet.getString("phone")%></td> --%>
			<td><%=user.getPhone()%></td>
		</tr>
		<tr>
			<td></td>
			<td><button type="button" onclick="openPage('edit.jsp') "
					style="height: 25px; width: 150px;">Update</button></td>
		</tr>
		<%
			} // end if session was active
		%>
		<%-- <%
		}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%> --%>


		<script type="text/javascript">
			function openPage(pageURL) {
				window.location.href = pageURL;
			}
		</script>
	</table>
</body>
</html>