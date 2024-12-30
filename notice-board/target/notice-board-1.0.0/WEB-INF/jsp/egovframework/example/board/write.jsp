<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/post/write.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/egovframework/index.css'/>" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container">
		<form:form class="write-form" action="/write.do" method="POST">
			<div class="form-header">
				<h2>글쓰기</h2>
			</div>

			<c:if test="${not empty message}">
				<div class="error-message">${message}</div>
			</c:if>

			<!-- 작성자 정보 섹션 -->
			<div class="author-section">
				<div class="form-group">
					<label for="userId">아이디</label> <input type="text"
						name="userVO.userId" id="userId" class="form-user-input"
						placeholder="아이디를 입력하세요" required maxlength="20" />
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						name="userVO.password" id="password" class="form-user-input"
						placeholder="비밀번호를 입력하세요" required maxlength="20" /> 
				</div>			
			<span class="help-text">※ 글 수정/삭제 시 필요하니 반드시 기억해주세요.</span>
			</div>

			<!-- 게시글 내용 섹션 -->
			<div class="form-group">
				<label for="title">제목</label> <input type="text" name="postVO.title"
					id="title" class="form-input" placeholder="제목을 입력하세요" required
					maxlength="100" /> <span class="char-count" id="titleCount">0
					/ 100</span>
			</div>

			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="postVO.content" id="content" class="form-textarea"
					placeholder="내용을 입력하세요" required maxlength="2000"></textarea>
				<span class="char-count" id="contentCount">0 / 2000</span>
			</div>

			<div class="form-footer">
				<button type="button" class="btn btn-cancel"
					onclick="confirmCancel()">취소</button>
				<button type="submit" class="btn btn-submit">등록</button>
			</div>
		</form:form>
	</div>

	<script>
		$(document).ready(function() {
			// 제목 글자수 카운트
			$('#title').on('input', function() {
				var length = $(this).val().length;
				$('#titleCount').text(length + ' / 100');
			});

			// 내용 글자수 카운트
			$('#content').on('input', function() {
				var length = $(this).val().length;
				$('#contentCount').text(length + ' / 2000');
			});

			// 페이지 이탈 시 경고
			var formChanged = false;
			$('.form-input, .form-textarea').on('input', function() {
				formChanged = true;
			});

			$(window).on('beforeunload', function(e) {
				if (formChanged) {
					return '작성 중인 내용이 있습니다. 페이지를 나가시겠습니까?';
				}
			});
		});

		// 폼 유효성 검사
		function validateForm() {
			var authorId = $('#authorId').val().trim();
			var password = $('#password').val().trim();
			var title = $('#title').val().trim();
			var content = $('#content').val().trim();
			var isValid = true;

			// ID 검사
			if (authorId.length < 3) {
				alert('아이디는 3자 이상 입력해주세요.');
				$('#authorId').focus();
				return false;
			}

			// 비밀번호 검사
			if (password.length < 4) {
				alert('비밀번호는 4자 이상 입력해주세요.');
				$('#password').focus();
				return false;
			}

			if (title.length < 1) {
				alert('제목을 입력해주세요.');
				$('#title').focus();
				isValid = false;
			} else if (title.length > 100) {
				alert('제목은 100자를 초과할 수 없습니다.');
				$('#title').focus();
				isValid = false;
			}

			if (content.length < 1) {
				alert('내용을 입력해주세요.');
				$('#content').focus();
				isValid = false;
			} else if (content.length > 2000) {
				alert('내용은 2000자를 초과할 수 없습니다.');
				$('#content').focus();
				isValid = false;
			}

			if (isValid) {
				formChanged = false;
			}

			return isValid;
		}

		// 취소 버튼 클릭 시 확인
		function confirmCancel() {
			if (formChanged) {
				if (confirm('작성 중인 내용이 있습니다. 페이지를 나가시겠습니까?')) {
					history.back();
				}
			} else {
				history.back();
			}
		}
	</script>
</body>
</html>