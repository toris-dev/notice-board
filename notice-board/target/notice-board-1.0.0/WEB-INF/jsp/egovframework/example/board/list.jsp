<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="title.sample" /></title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/index.css'/>" />
</head>

<body>
	<script>
		function fn_egov_link_page(pageIndex) {
			window.location.href = "<c:url value='/list.do'/>?pageIndex="
					+ pageIndex;
		}

		function handleNavigation(postId) {
			// 여기서 postId를 활용하여 게시글 상세 페이지로 이동
			window.location.href = "<c:url value='/detailView.do'/>?postId="
					+ postId;
		}
	</script>
	<div class='container'>

		<%@ include file="/WEB-INF/jsp/egovframework/example/cmmn/Header.jsp"%>
		<table class='posts'>
			<caption class="text-center">자유롭게 사용하는 게시판입니다.</caption>
			<thead>
				<tr class='posts-title'>
					<th class='posts-number'>번호</th>
					<th class='posts-title'>제목</th>
					<th class='posts-author'>작성자</th>
					<th class='posts-created'>생성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${resultList}" varStatus="status">
					<tr onclick="handleNavigation('${post.getPostId()}')">
						<td>${post.getPostId()}</td>
						<td>${post.getTitle()}</td>
						<td>${post.getAuthorId()}</td>
						<td><fmt:formatDate value="${post.getCreatedAt()}"
								pattern="yyyy.MM.dd HH:mm" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
			<ui:pagination paginationInfo="${paginationInfo}" type="text"
				jsFunction="fn_egov_link_page" />
		</div>


	</div>

</body>
</html>
