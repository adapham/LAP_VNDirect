<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/10/2022
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User</h1>
        <p>Username:${users.username}</p>
        <p>Password:${users.password}</p>
        <p>Email:${users.email}</p>
        <p>Age:${users.age}</p>
        <p>Group:${users.group.name}</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
