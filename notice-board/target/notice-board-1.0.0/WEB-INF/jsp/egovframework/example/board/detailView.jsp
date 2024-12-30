<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 페이지</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/post/index.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/comment/index.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/index.css'/>" />
</head>
<body>
<div class="container">
	<h2>자유 게시판</h2>
    <div class='post-wrap'>
        <h3 class="post-title">${post.getTitle()}</h3>
        <div class="post-info">
            <span class="post-date">${post.getAuthorId()}</span>
            <span class="post-date">${post.getCreatedAt()}</span>
        </div>
        <hr class="line" />
        <div class="post-content-wrap">
            <span class="post-content">${post.getContent()}</span>
        </div>
    </div>
    <hr class="line" />
    <div class="comments-section">
        <h4 class="comments-title">댓글</h4>
        <c:forEach var="comment" items="${comments}">
            <div class="comment-wrap">
                <div class="comment-info">
                    <span class="comment-author">${comment.getAuthorId()}</span>
                    <span class="comment-date">${comment.getCreatedAt()}</span>
                </div>
                <p class="comment-content">${comment.getContent()}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>