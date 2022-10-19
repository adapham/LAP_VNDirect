<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/10/2022
  Time: 8:52 AM
  To change this template use File | Settings | File Templates.
--%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Group List</h1>

        <form method="get" action="/group/list">
            <div class="input-group rounded">
                <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
                       aria-describedby="search-addon" name="q"/>
                <span class="input-group-text border-0" id="search-addon">
    <i class="fas fa-search"></i>
  </span>
            </div>
        </form>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Delete</td>
                <td>Update</td>
            </tr>
            <c:forEach items="${groups}" var="item" varStatus="loop">
                <tr>
                    <td><a href="/user/list?group=${item.id}"> ${item.name}</a>
                    <ul>
                        <c:forEach items="${item.users}" var="user" varStatus="loop">
                            <li>${user.username} - ${user.age}</li>
                        </c:forEach>
                    </ul>
                    </td>
                    <td><a href="delete/${item.id}">Delete</a></td>
                    <td><a href="update?id=${item.id}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>
