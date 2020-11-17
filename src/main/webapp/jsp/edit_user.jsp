<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.11.2020
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
</head>
<body>
<center>
    <h1>Users Management</h1>
    <h2>
        <a href="userHomePage">List All Users</a>

    </h2>
</center>
<div align="center">
    <form action="userHomePage" method="post" >
    <c:if test="${user != null}">
        <input type="hidden" name="action" value="edit_user"/>
    </c:if>
            <table border="1" cellpadding="5" style="border-collapse: collapse">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                </c:if>
                <tr>
                    <th>isAdmin(T/F):</th>
                    <td>
                        <input type="text" name="isAdmin" size="45"
                               value="<c:out value='${user.isAdmin}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Role(-1-admin,0-user,1-worker):</th>
                    <td>
                        <input type="text" name="role" size="45"
                               value="<c:out value='${user.role}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
