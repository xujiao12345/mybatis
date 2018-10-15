<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2018/10/12
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>gender</th>
            <th>email</th>
            <th>lastName</th>
        </tr>
        <c:forEach items="${emps}" var="emp">

            <tr>
                <td>${emp.id}</td>
                <td>${emp.gender}</td>
                <td>${emp.email}</td>
                <td>${emp.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
