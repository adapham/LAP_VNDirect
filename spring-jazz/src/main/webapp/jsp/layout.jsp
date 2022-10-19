<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot Application</title>
</head>
<body>
<h1>Welcome to Snail</h1>
<a href="">Trang Chu</a>

<sec:authorize access="!hasAnyRole('ROLE_USE')">
	<a href="/Dang nhap"> Dang nhap</a>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_USER')">
		<a href="/nguoi-dung"> Nguoi Dung</a>

		<a href="javascript:document.getElementById('logout').submit();"> Logout</a>
	<form action="/j_spring_security_logout" id="logout" method="post">
		<input type="" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	</form>
</sec:authorize>


	<table border="1" cellpadding="2" cellspacing="2" align="center">
		<tr>
			<td height="30" >Welcome layout
			</td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>

	</table>
</body>
</html>