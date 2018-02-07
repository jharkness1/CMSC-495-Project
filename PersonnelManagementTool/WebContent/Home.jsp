
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
</head>


<body>
<%
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "umuc";
String userId = "root";
String password = "Hark116620!";

try {
	Class.forName(driverName).newInstance();
	
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="left"><font><strong>View Profile</strong></font>
<button type="button" onclick="" style="float: right; height:25px;width:150px;">Logout</button>  
</h2>
<table align="left" cellpadding="6" cellspacing="15" border="0">

<tr>
<td><b>First Name</b></td>
<td> Jason</td>
</tr>
<tr>
<td><b>Last Name</b></td>
<td> Harkness</td>
</tr>
<tr>
<td><b>Email</b></td>
<td> jason_harkness@hotmail.com</td>
</tr>
<tr>
<td><b>Company</b></td>
<td> UMUC</td>
</tr>
<tr>
<td><b>Department</b></td>
<td> Computer Science</td>
</tr>
<tr>
<td><b>Title</b></td>
<td> Student</td>
</tr>
<tr>
<td><b>Work Address</b></td>
</tr>
<tr>
<td><b>Work City</b></td>
</tr>
<tr>
<td><b>Work State</b></td>
</tr>
<tr>
<td><b>Work Zip Code</b></td>
</tr>
<tr>
<td></td>
<td><button type="button" onclick=" "style="height:25px;width:150px;">Update</button>   </td>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM profiles";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>

<td><%=resultSet.getString("firstname") %></td>
<td><%=resultSet.getString("email") %></td>

</tr>

<% 
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>