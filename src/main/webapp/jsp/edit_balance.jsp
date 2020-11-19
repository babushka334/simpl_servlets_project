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
    <h1>Balance Management</h1>
    <h2>
        Your balance: ${User.balance}$  <a href="userHomePage">Back</a>
    </h2>
</center>
<div align="center">
    <form action="userPage" method="post">
        <table border="0" cellpadding="5">
            <caption>
                <h2>
                    Edit Balance
                </h2>
            </caption>
            <tr>
                <td colspan="2" align="center">
                    <strong>Entry sum:</strong><label><input id='elem1' type="text" name="value"/></label>
                </td>
            </tr>
            <tr>
                <td>
                    <input width="100%" type="submit" name="action" value="Add money to account" class="btn btn-danger"/>
                </td>
                <td <%--onclick="document.location='userPage?action=get_money&value='+document.getElementById('elem1').value"--%> >
                    <input width="100%" type="submit" name="action" value="Get money from account"  class="btn btn-success"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>