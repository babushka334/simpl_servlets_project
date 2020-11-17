<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Events</title>
</head>
<body>
<center>
    <h1>Bets Management</h1>
    <h2>
        <a href="userHomePage?action=add_bet&id=<c:out value='${event.id}'/>&team1=<c:out value='${event.team1}'/>&team2=<c:out value='${event.team2}'/>">Add New Bet</a>
        &nbsp;&nbsp;&nbsp;
        <a href="userHomePage">List All Event</a>

    </h2>
    <c:if test="${bet != null}">
        <h3><c:out value='${bet.event.team1}'/> VS <c:out value='${bet.event.team2}'/></h3>
    </c:if>
    <c:if test="${bet == null}">
        <h3><c:out value='${event.team1}'/> VS <c:out value='${event.team2}'/></h3>
    </c:if>
</center>
<div align="center">
    <form action="userHomePage" method="post">
        <c:if test="${bet != null}">
            <input type="hidden" name="action" value="edit_bet" />
        </c:if>
        <c:if test="${bet == null}">
            <input type="hidden" name="action" value="add_bet" />
            <input type="hidden" name="id" value=${event.id} />
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${bet != null}">
                        Edit Event
                    </c:if>
                    <c:if test="${bet == null}">
                        Add New Event
                    </c:if>
                </h2>
            </caption>
            <c:if test="${bet != null}">
                <input type="hidden" name="id" value="<c:out value='${bet.id}' />" />
            </c:if>
            <tr>
                <th>Ставка:</th>
                <td>
                    <c:if test="${bet == null}">
                        <select name=type_list>
                            <c:forEach var="type_of_bet" items="${types_of_bet}">
                                <c:if test="${type_of_bet.key != bet.type_of_bet_id}">
                                    <option value=${type_of_bet.key}>${type_of_bet.value}</option>
                                </c:if>
                                <c:if test="${type_of_bet.key == bet.type_of_bet_id}">
                                    <option selected value=${type_of_bet.key}>${type_of_bet.value}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${bet != null}">
                        <c:out value='${bet.type_of_bet}' />
                    </c:if>
                </td>
                <td>
                    <input type="text" name="value" size="45"
                           value="<c:out value='${bet.value}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Coefficient: </th>
                <td colspan="2" align="center">
                    <input type="text" name="coefficient" size="45"
                           value="<c:out value='${bet.coefficient}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
