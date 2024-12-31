<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="header-wrap">
		<h2>notice-board</h2>
		<div class="auth-actions">
			<!-- 로그아웃 버튼 (로그인 상태일 때만 표시) -->
			<sec:authorize access="isAuthenticated()">
				<form action="<c:url value='signout'/>" method="post">
					<button class="auth-btn logout">SignUp</button>
				</form>
			</sec:authorize>

			<!-- 로그인 버튼 (로그인되지 않은 상태일 때만 표시) -->
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value='/signin.do'/>" class="auth-btn login">SignIn</a>
			</sec:authorize>
		</div>
	</div>
</body>
</html>