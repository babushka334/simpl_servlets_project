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
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <title>Logged In</title>
</head>
<body>
<center>
    <% User user = (User) session.getAttribute("User"); %>

    <h1>Welcome   <% out.println(user.getFirstName()); %>!!!! You have logged in.</h1>
</center>

<%--<form action="${pageContext.request.contextPath}/userHomePage" method="get">--%>
<div class="btn-group">
    <button id="hider1" class="btn btn-info">Показать список пользователей</button>
    <button id="hider2" class="btn btn-info">Показать список событий</button>
    <button id="hider3" class="btn btn-info">Показать список оформленных ставок</button>
</div>
<script>
    document.getElementById('hider1').onclick = function () {
        document.getElementById('users_list').hidden = !document.getElementById('users_list').hidden;
        if (document.getElementById('users_list').hidden)
            document.getElementById('hider1').setAttribute("value", "Показать список пользователей");
        else {
            document.getElementById('hider1').setAttribute("value", "Скрыть список пользователей");
            document.getElementById('events_list').hidden = true;
            document.getElementById('users_bets_list').hidden = true;
        }

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


<script>
    document.getElementById('hider2').onclick = function () {
        document.getElementById('events_list').hidden = !document.getElementById('events_list').hidden;
        if (document.getElementById('events_list').hidden)
            document.getElementById('hider2').setAttribute("value", "Показать список событий");
        else {
            document.getElementById('hider2').setAttribute("value", "Скрыть список событий");
            document.getElementById('users_list').hidden = true;
            document.getElementById('users_bets_list').hidden = true;
        }
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
                <td><c:out value="${event.team2}"/>(<c:out value="${event.status}"/>)</td>
                <td>
                    <a href="userHomePage?action=edit_event&id=<c:out value='${event.id}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="userHomePage?action=delete_event&id=<c:out value='${event.id}'/>">Delete</a>
                </td>
                <td><a href="userHomePage?action=add_bet&id=<c:out value='${event.id}'/>&team1=<c:out value='${event.team1}'/>&team2=<c:out value='${event.team2}'/>&status=<c:out value='${event.status}'/>">
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

<script>
    document.getElementById('hider3').onclick = function () {
        document.getElementById('users_bets_list').hidden = !document.getElementById('users_list').hidden;
        if (document.getElementById('users_bets_list').hidden)
            document.getElementById('hider1').setAttribute("value", "Показать список пользователей");
        else {
            document.getElementById('hider1').setAttribute("value", "Скрыть список пользователей");
            document.getElementById('events_list').hidden = true;
            document.getElementById('users_list').hidden = true;
        }

    }
</script>

<div>
    <table border="1" cellpadding="5" id="users_bets_list" style="border-collapse: collapse" class="table table-striped">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <%--<th>ID</th>--%>
            <th>Username</th>
            <th>Team1</th>
            <th>Team2</th>
            <th>Bet</th>
            <th>Sum</th>
            <th>Status</th>
            <%--<th>Action</th>--%>
        </tr>
        <c:forEach var="user_bet" items="${user_bets}">
            <c:forEach var="bet" items="${bets}">
                <c:if test="${bet.id == user_bet.bet_id}">
                    <tr>
                            <%--<td><c:out value="${user.id}" /></td>--%>
                        <td><c:out value="${User.username}"/></td>
                        <td><c:out value="${bet.event.team1}"/></td>
                        <td><c:out value="${bet.event.team2}"/></td>
                        <td><c:out value="${bet.type_of_bet}"/>: <c:out value="${bet.value}"/></td>
                        <td><c:out value="${user_bet.value}"/></td>
                        <td><c:out value="${user_bet.status}"/></td>
                            <%--<td>
                                <a href="userHomePage?action=edit_user&id=<c:out value='${user.id}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="userHomePage?action=delete_user&id=<c:out value='${user.id}' />">Delete</a>
                            </td>--%>
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
</div>



<br>
<a href="<%=request.getContextPath()%>/LogoutUser"><b>Logout</b></a>
<script>document.getElementById('users_list').hidden = true;</script>
<script>document.getElementById('events_list').hidden = true;</script>
<script>document.getElementById('users_bets_list').hidden = true;</script>
</body>
</html>