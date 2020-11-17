<%@ page import="by.mops.models.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logged In</title>
</head>
<body>
<center>
    <% User user = (User) session.getAttribute("User"); %>

    <h1>Welcome   <% out.println(user.getFirstName()); %>!!!! You have logged in.</h1>
</center>

<%--<form action="${pageContext.request.contextPath}/userHomePage" method="get">--%>
<input type="button" value="Показать список пользователей" id="hider1">

<script>
    document.getElementById('hider1').onclick = function () {
        document.getElementById('users_list').hidden = !document.getElementById('users_list').hidden;
        if (document.getElementById('users_list').hidden)
            document.getElementById('hider1').setAttribute("value", "Показать список пользователей");
        else
            document.getElementById('hider1').setAttribute("value", "Скрыть список пользователей");

    }
</script>
<div>
    <table border="1" cellpadding="5" id="users_list" style="border-collapse: collapse" class="table table-striped">
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
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.isAdmin}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.birthday}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
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
        if (document.getElementById('events_list').hidden)
            document.getElementById('hider2').setAttribute("value", "Показать список событий");
        else
            document.getElementById('hider2').setAttribute("value", "Скрыть список событий");

    }
</script>
<div>
    <table border="1" cellpadding="5" id="events_list" style="border-collapse: collapse" class="table table-striped">
        <caption><h2>List of Events</h2></caption>
        <tr>
            <%--<th>ID</th>--%>
            <th>Team1</th>
            <th>Team2</th>
            <th>Action</th>
            <th>Bets Action</th>
        </tr>
        <c:forEach var="event" items="${events}">
            <tr class="table-info">
                    <%--<td><c:out value="${event.id}" /></td>--%>
                <td><c:out value="${event.team1}"/></td>
                <td><c:out value="${event.team2}"/></td>
                <td>
                    <a href="userHomePage?action=edit_event&id=<c:out value='${event.id}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="userHomePage?action=delete_event&id=<c:out value='${event.id}'/>">Delete</a>
                </td>
                <td><a href="userHomePage?action=add_bet&id=<c:out value='${event.id}'/>&team1=<c:out value='${event.team1}'/>&team2=<c:out value='${event.team2}'/>">
                        Add bet
                    </a>
                </td>
            </tr>

            <c:forEach var="bet" items="${bets}">
                <tr>

                    <c:if test="${bet.event.id == event.id}">
                        <td><c:out value="${bet.type_of_bet}"/></td>
                        <td><c:out value="${bet.value}"/></td>
                        <td><c:out value="${bet.coefficient}"/></td>
                        <td>
                            <a href="userHomePage?action=edit_bet&id=<c:out value='${bet.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="userHomePage?action=delete_bet&id=<c:out value='${bet.id}' />">Delete</a>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>


        </c:forEach>
        <tr>
            <td colspan="4" align="center">
                <a href="userHomePage?action=add_event">Add event</a>
            </td>
        </tr>
    </table>
</div>


<%-- <pre><a href="userHomePage?action=edit"><b>Edit</b></a>    <a href="userHomePage?action=add"><b>Add</b></a>    <a
         href="userHomePage?action=delete"><b>Delete</b></a></pre>--%>

<br>
<a href="login"><b>Logout</b></a>
<script>document.getElementById('users_list').hidden = true;</script>
<script>document.getElementById('events_list').hidden = true;</script>
</body>
</html>