<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Add Group</h1>	
		<form:form method="post" action="/group/save">
			<p>
				<form:input type="text" placeholder="Group Name" name="name" path="name"/>
				<form:input  type="hidden" placeholder="id" name="id" path="id"/>
			</p>
			<p class="submit">
				<input type="submit" name="add" value="ADD">
			</p>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>