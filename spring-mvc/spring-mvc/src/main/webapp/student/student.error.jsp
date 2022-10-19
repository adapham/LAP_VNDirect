<%--
  Created by IntelliJ IDEA.
  User: minh0
  Date: 9/30/2022
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" import="java.io.*" %>
<html>
<head>
    <title>Student Error</title>
</head>
<body>
<h1>Have Error</h1>
<%
    Exception exp = (Exception) request.getAttribute("javax.servlet.error.exception");
    exp.printStackTrace(response.getWriter());
%>

</body>
</html>
