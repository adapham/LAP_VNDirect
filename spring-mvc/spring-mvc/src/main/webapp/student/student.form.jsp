<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head> <title>Add New Student</title> </head>
<body>
    <h2>Please Input Student Information</h2>
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
	</form:form>
</body> 
</html>

