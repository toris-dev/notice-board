<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/sign.css'/>" />
</head>
<body>
	<div class="signup-container">
		<h2>회원가입</h2>
		<form action="<c:url value='/signup' />" method="post">
			<div class="form-group">
				<label for="username">아이디</label> <input type="text" id="username"
					name="username" required />
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required />
			</div>
			<div class="form-group">
				<label for="confirmPassword">비밀번호 확인</label> <input type="password"
					id="confirmPassword" name="confirmPassword" required />
			</div>
			<div class="form-group">
				<button type="submit" class="signup-button">회원가입</button>
			</div>
			<div class="form-group">
				<a href="<c:url value='/signin.do'/>" class="signin-link">이미 회원이신가요? 로그인</a>
			</div>
			<c:if test="${param.error == 'passwordMismatch'}">
				<p style="color: red;">비밀번호가 일치하지 않습니다.</p>
			</c:if>
		</form>
	</div>
</body>
</html>
