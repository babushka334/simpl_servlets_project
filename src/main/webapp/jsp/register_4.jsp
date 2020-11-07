<%@ page import="by.mops.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logged In</title>
</head>
<body>
<table style="with: 50%">
    <tr><td>
        <% User user = (User)session.getAttribute("User"); %>
        <a>Welcome   <%  out.println(user.getFirstName()); %> User!!!! You have logged in.</a></td></tr>
    <tr></tr><tr><td></td><td></td><td><a href="login"><b>Logout</b></a></td></tr>
</table>
</body>
</html>