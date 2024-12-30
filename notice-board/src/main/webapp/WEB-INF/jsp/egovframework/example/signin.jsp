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
<title>로그인</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/sign.css'/>" />
</head>
<body>
	<div class="login-container">
		<h2>로그인</h2>
		<form action="<c:url value='/signin.do?${_csrf.parameterName}=${_csrf.token}' />" 
			method="post"
			enctype="application/x-www-form-urlencoded"
		>
			<div class="form-group">
				<label for="userId">아이디</label> 
				<input type="text" id="userId" name="userId" required />
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> 
				<input type="password" id="password" name="password" required />
			</div>
			<div class="form-group">
				<button type="submit" class="login-button">로그인</button>
			</div>
			<div class="form-group">
				<a href="<c:url value='/signup.do'/>" class="signup-link">회원가입</a>
			</div>
		</form>
	</div>
</body>
</html>
