<%--
  Created by IntelliJ IDEA.
  User: minh0
  Date: 9/30/2022
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function view(id) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET","json/"+id,true);
        xmlHttp.onload = function () {
            if (this.status != 200) return;
            console.log(this.responseText);
            var student = JSON.parse(this.responseText);
            document.getElementById('content').innerHTML = 'Name : '+ student.name;
            var dialog = document.getElementById('viewStudent');
            dialog.show();
        }
        xmlHttp.send();
    }
</script>
<body>
<h1>List of student</h1>

<form method="get" action="list">


<table border="1">
    <tr>
        <td colspan="3"><input type="text" name="q" size="30" value="${q}"></td>
        <td><input type="submit" value="Submit"></td>
    </tr>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Age</td>
        <td>Action</td>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>
                ${student.id}
            </td>
            <td><a href="javascript:view(${student.id})">${student.name}</a></td>
            <td>${student.age}</td>
            <td><a href="delete/${student.id}">delete</a>  <a href="edit/${student.id}">edit</a>  <a href="json/${student.id}">view</a></td>
        </tr>
    </c:forEach>
</table>
</form>
<dialog id="viewStudent" style="width: 50%;border: 1px dotted black;">
    <div id="content"> </div>
    <button id="hide">Close</button>
</dialog>
<script>
    (function (){
        var dialog = document.getElementById("viewStudent");
        document.getElementById('hide').onclick = function (){
            dialog.close();
        }
    })();
</script>
</body>
</html>
