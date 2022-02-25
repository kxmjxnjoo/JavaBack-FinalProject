<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="script/script.js"></script>
<link rel="stylesheet" href="/admin/css/qnadetail.css">
</head>
<body>

	<%@ include file="/admin/common/admin_submenu.jsp" %>

	<div id="wrap">
		<h1 class="mark">Q</h1>
		<h1 class="qnaTitle">${ qna.qna_subject }</h1>
		<p>${ qna.qna_content }</p>
		<h2 class="info">${ qna.qna_id }님의 질문이에요</h2>
		<h2 class="info"><fmt:formatDate value="${ qna.indate }" pattern="yy년 MM월 dd일"/>에 질문했어요</h2>
		
		<h1 class="mark">A</h1>
		<h1 class="qnaTitle">답변</h1>
		
		<form class ="table" action="spring.do" method="post">
			<input type="hidden" name="command" value="editqna">
			<input type="hidden" name="num" value="${ qna.qna_num }">
			
			<textarea name="reply" placeholder="여기에 답변을 입력하세요"></textarea>
			
			<input type="submit" value="답변 등록">
		</form>
	</div>

	<%@ include file="/admin/common/footer.jsp" %>

</body>
</html>