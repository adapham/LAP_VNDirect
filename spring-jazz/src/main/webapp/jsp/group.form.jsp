<%--
  Created by IntelliJ IDEA.
  User: Snail
  Date: 10/9/2022
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">

        <h1>ADD Group</h1>
        <form:form method="post" action="/nhom/luu">
            <form:input type="text" name="name" path="name"/>
            <form:input type="hidden" name="id" path="id"/>
            <input type="submit" value="Add">
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>
