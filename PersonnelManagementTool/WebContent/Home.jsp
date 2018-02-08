
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

	<%
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
			String sql = "SELECT firstname,lastname,email,company,department,title,work_address,work_city,work_state,work_zip FROM profiles WHERE id='1'";

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
	%>
	<% if (request.getAttribute("profile") != null) {
		UserProfile user= (UserProfile)request.getAttribute("profile");
		}
	 %>

		<h3 align="left">
		<font><strong>View Profile</strong></font>
		<button type="button" onclick=""
			style="float: right; height: 25px; width: 150px;">Logout</button>
	</h3>

	<table id="profile" align="left" cellpadding="6" cellspacing="15" border="0">
		<tr>
			<form method="post" action="accessProfile">
					<input type="hidden" name="id" value="<%=%>">
				</form>
		<tr>
			<td><b>First Name</b></td>
			<td><%=resultSet.getString("firstname")%></td>
		</tr>
		<tr>
			<td><b>Last Name</b></td>
			<td><%=resultSet.getString("lastname")%></td>
		</tr>

		<tr>
			<td><b>Email</b></td>
			<td><%=resultSet.getString("email")%></td>
		</tr>
		<tr>
			<td><b>Company</b></td>
			<td><%=resultSet.getString("company")%></td>
		</tr>
		<tr>
			<td><b>Department</b></td>
			<td><%=resultSet.getString("department")%></td>
		</tr>
		<tr>
			<td><b>Title</b></td>
			<td><%=resultSet.getString("title")%></td>
		</tr>
		<tr>
			<td><b>Work Address</b></td>
			<td><%=resultSet.getString("work_address")%></td>
		</tr>
		<tr>
			<td><b>Work City</b></td>
			<td><%=resultSet.getString("work_city")%></td>
		</tr>
		<tr>
			<td><b>Work State</b></td>
			<td><%=resultSet.getString("work_state")%></td>
		</tr>
		<tr>
			<td><b>Work Zip Code</b></td>
			<td><%=resultSet.getString("work_zip")%></td>
		</tr>
		<tr>
			<td></td>
			<td><button type="button" onclick="openPage('edit.jsp') "
					style="height: 25px; width: 150px;">Update</button></td>
		</tr>
		</tr>
	<%
		}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>


		<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>
	</table>
</body>
</html>