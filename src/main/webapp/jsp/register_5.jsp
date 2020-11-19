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
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
    <% User user = (User) session.getAttribute("User"); %>

    <h1>Welcome   <% out.println(user.getFirstName()); %>!!!! You have logged in.</h1>
</center>

<div class="btn-group">
    <form action="userPage" method="get">
        <input type="submit" name="action" value="Balance" class="btn btn-info"/>
    </form>


    <button id="hider1" class="btn btn-info">Показать список оформленных ставок</button>


    <button id="hider2" class="btn btn-info">Показать список событий</button>
</div>
<script>
    document.getElementById('hider1').onclick = function () {
        document.getElementById('users_list').hidden = !document.getElementById('users_list').hidden;
        if (document.getElementById('users_list').hidden)
            document.getElementById('hider1').setAttribute("value", "Показать список пользователей");
        else {
            document.getElementById('hider1').setAttribute("value", "Скрыть список пользователей");
            document.getElementById('events_list').hidden = true;
        }
    }
</script>
<%--<input type="button" value="Показать список событий" id="hider2">--%>


<script>
    document.getElementById('hider2').onclick = function () {
        document.getElementById('events_list').hidden = !document.getElementById('events_list').hidden;
        if (document.getElementById('events_list').hidden)
            document.getElementById('hider2').setAttribute("value", "Показать список событий");
        else {
            document.getElementById('hider2').setAttribute("value", "Скрыть список событий");
            document.getElementById('users_list').hidden = true;
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
        </tr>
        <c:forEach var="event" items="${events}">
            <c:if test="${event.status == 'не завершен'}">
                <tr class="table-info">
                        <%--<td><c:out value="${event.id}" /></td>--%>
                    <td><c:out value="${event.team1}"/></td>
                    <td><c:out value="${event.team2}"/></td>
                    <td></td>
                </tr>

                <c:forEach var="bet" items="${bets}">
                    <tr>
                        <c:if test="${bet.event.id == event.id}">
                            <td><c:out value="${bet.type_of_bet}"/></td>
                            <td><c:out value="${bet.value}"/></td>
                            <%--<td width="100%" type="button" onclick="document.location='userPage?action=add_bet&id=<c:out
                                    value="${bet.id}" />'" class="btn btn-danger">
                                <c:out value="${bet.coefficient}"/>
                            </td>--%>
                            <td>
                                <button width="100%" type="button" class="btn btn-danger" data-toggle="modal"
                                        data-target="#exampleModal" data-whatever="@mdo">
                                    <c:out value="${bet.coefficient}"/>
                                </button>
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">${event.team1}
                                                    VS ${event.team2}</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="userPage" method="post">
                                                    <div class="form-group">
                                                        <label class="col-form-label">
                                                            <h5>${bet.type_of_bet}: ${bet.value}</h5></label>
                                                        <label class="col-form-label"><h5>
                                                            Coefficient: ${bet.coefficient}</h5></label>
                                                        <input type="hidden" name="user_id" value=${User.id}>
                                                        <input type="hidden" name="bet_id" value=${bet.id}>
                                                        <input type="hidden" name="action" value="add_bet">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Value of
                                                            bet:</label>
                                                        <input type="text" class="form-control" id="message-text"
                                                               name="value">
                                                        <span class="error" aria-live="polite"></span>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="submit" class="btn btn-primary" id="betting">Bet
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                                <%--<div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn btn-primary">Bet</button>
                                                </div>--%>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </c:forEach>
    </table>
</div>


<div>
    <table border="1" cellpadding="5" id="users_list" style="border-collapse: collapse" class="table table-striped">
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
<script>document.getElementById('events_list').hidden = true;</script>
<script>document.getElementById('users_list').hidden = true;</script>
<script>
    var form = document.getElementsByTagName('form')[1];
    var email = document.getElementById('message-text');
    var error = document.querySelector('.error');

    email.addEventListener("input", function (event) {
        // Каждый раз, когда пользователь вводит что-либо, мы проверяем,
        // является ли корректным поле электронной почты.
        if (document.getElementById('message-text').value <= ${User.balance}) {
            // В случае появления сообщения об ошибке, если поле
            // является корректным, мы удаляем сообщение об ошибке.
            error.innerHTML = ""; // Сбросить содержимое сообщения
            error.className = "error"; // Сбросить визуальное состояние сообщения
        }
    }, false);
    form.addEventListener("submit", function (event) {
        // Каждый раз, когда пользователь пытается отправить данные, мы проверяем
        // валидность поля электронной почты.
        if (document.getElementById('message-text').value > ${User.balance}) {

            // Если поле невалидно, отображается пользовательское
            // сообщение об ошибке.
            error.innerHTML = "Not enough money!";
            error.className = "error active";
            // И мы предотвращаем отправку формы путем отмены события
            event.preventDefault();
        }
    }, false);
</script>
<script>
    function ctrlButton() {
        document.getElementById('betting').disabled = this.value.trim().length === 0;
    }

    document.getElementById('message-text').addEventListener('input', ctrlButton, false);
    ctrlButton.call(document.getElementById('message-text'));
</script>
<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
