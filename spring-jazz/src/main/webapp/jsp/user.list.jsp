<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/10/2022
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User List</h1>
        <table style="width: 100%">
            <tr>
                <td>Name</td>
                <td>Age</td>
                <td>Delete</td>
            </tr>
            <c:forEach items="${users}" var="item" varStatus="loop">
                <tr>
                    <td><a href="/user/detail/${item.username}">${item.username}</a></td>
                    <td>${item.age}</td>
                    <td><a href="/user/delete/${item.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <h2>Average of Age: ${average}</h2>
    </tiles:putAttribute>
</tiles:insertDefinition>

