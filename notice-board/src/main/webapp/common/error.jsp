<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>에러가 발생했습니다.</h1>
    <p>에러 메시지: ${requestScope['javax.servlet.error.message']}</p>
    <p>에러 타입: ${requestScope['javax.servlet.error.exception_type']}</p>
    <c:if test="${not empty requestScope['javax.servlet.error.exception']}">
        <h2>Exception Details:</h2>
        <pre>
            <c:out value="${requestScope['javax.servlet.error.exception']}"/>
        </pre>
    </c:if>
</body>
</html>