<%@ page import="by.mops.models.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logged In</title>
</head>
<body>
<table style="with: 50%">
    <p>
        <% User user = (User) session.getAttribute("User"); %>
        <a>Welcome   <% out.println(user.getFirstName()); %>!!!! You have logged in.</a>
    </p>
    <%--<form action="${pageContext.request.contextPath}/userHomePage" method="get">--%>
    <input type="button" value="Показать список пользователей" id="hider1">

    <script>
        document.getElementById('hider1').onclick = function () {
            document.getElementById('users_list').hidden = !document.getElementById('users_list').hidden;
            if(document.getElementById('users_list').hidden)
                document.getElementById('hider1').setAttribute("value", "Показать список пользователей");
            else
                document.getElementById('hider1').setAttribute("value", "Скрыть список пользователей");

        }
    </script>
    <div>
        <table border="1" cellpadding="5"  id="users_list" style="border-collapse: collapse">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <%--<th>ID</th>--%>
                <th>login</th>
                <th>is admin</th>
                <th>role</th>
                <th>birthday</th>
                <th>first name</th>
                <th>last name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <%--<td><c:out value="${user.id}" /></td>--%>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.isAdmin}" /></td>
                    <td><c:out value="${user.role}" /></td>
                    <td><c:out value="${user.birthday}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td>
                        <a href="userHomePage?action=edit_user&id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="userHomePage?action=delete_user&id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <input type="button" value="Показать список событий" id="hider2">

    <script>
        document.getElementById('hider2').onclick = function () {
            document.getElementById('events_list').hidden = !document.getElementById('events_list').hidden;
            if(document.getElementById('events_list').hidden)
                document.getElementById('hider2').setAttribute("value", "Показать список событий");
            else
                document.getElementById('hider2').setAttribute("value", "Скрыть список событий");

        }
    </script>
    <div>
        <table border="1" cellpadding="5"  id="events_list" style="border-collapse: collapse">
            <caption><h2>List of Events</h2></caption>
            <tr>
                <%--<th>ID</th>--%>
                <th>Team1</th>
                <th>Team2</th>
                <th>Action</th>
            </tr>
            <c:forEach var="event" items="${events}">
                <tr>
                        <%--<td><c:out value="${event.id}" /></td>--%>
                    <td><c:out value="${event.team1}" /></td>
                    <td><c:out value="${event.team2}" /></td>
                    <td>
                        <a href="userHomePage?action=edit_event&id=<c:out value='${event.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="userHomePage?action=delete_event&id=<c:out value='${event.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3" align="center">
                    <a href="userHomePage?action=add_event">Add</a>
                </td>
            </tr>
        </table>
    </div>


   <%-- <pre><a href="userHomePage?action=edit"><b>Edit</b></a>    <a href="userHomePage?action=add"><b>Add</b></a>    <a
            href="userHomePage?action=delete"><b>Delete</b></a></pre>--%>

    <br>
    <a href="login"><b>Logout</b></a>
</table>
<script>document.getElementById('users_list').hidden = true;</script>
<script>document.getElementById('events_list').hidden = true;</script>
</body>
</html>