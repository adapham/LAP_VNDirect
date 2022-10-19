<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Trang Đăng Nhập</h1>
		<c:if test="${not empty error}">
			<p>Sai tên đăng nhập hoặc mật khẩu</p>
		</c:if>
		<form name="googleSocialLoginForm" method="post"  action="/auth/google">
<%--			<input type="hidden" name="scope" value="public_profile,email,user_birthday">--%>
			<input type="hidden" name="scope" value="email">
<%--	<input type="hidden" name="scope" value="https://www.googleapis.com/auth/plus.me--%>
<%--	https://www.googleapis.com/auth/userinfo.email--%>
<%--	https://www.googleapis.com/auth/userinfo.profile" />--%>

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<p>
				<input type="text"name="username" id="username" placeholder="Username" >
			</p>
			<p>
				<input type="password" name="password" id="password" placeholder="Password" >
			</p>
			<p>
				Remember Me?
				<input type="checkbox" name="remember-me" />
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Login">
			</p>
			<button type="submit">
				<i>Sign in with FaceBook</i>
			</button>
			<button type="submit">
				<i>Sign In with Google</i>
			</button>
			<div class="clear"></div>
		</form>

	</tiles:putAttribute>
</tiles:insertDefinition>