<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head> <title>Add New Student</title> </head>
<body>
<h2>Please Input Student Information1</h2>
<form:form method="post" action="save">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" type="text"/>  </td>
            <td> <form:errors path="name"/> </td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><form:input path="age" type="number"/></td>
            <td><form:errors path="age"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="submit">
            </td>
        </tr>
    </table>
    <p>${id}</p>
</form:form>

<c:if test="${id != null}">
    <h1>Please upload a image</h1>
    <form method="post" action="../avatar/save" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${id}">
        <input type="file" name="file">
        <input type="submit" value="Upload">
    </form>
</c:if>
</body>
</html>
