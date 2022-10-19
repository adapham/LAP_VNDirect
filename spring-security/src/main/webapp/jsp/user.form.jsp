<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Add User</h1>
        <form:form method="post" action="/user/save">
            <p>User: <form:input type="text" path="username" name="username"/></p>
            <p>Pass:<form:input path="password" type="text" name="password"/></p>
            <p>Email: <form:input path="email" type="text" name="email"/></p>
            <p>Age: <form:input path="age" type="number" name="age"/></p>
            <p>Group:<form:select path="groupId">
                <form:options items="${groups}"/>
            </form:select></p>
            <p class="submit">
                <input type="submit" name="add" value="Add">
            </p>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>