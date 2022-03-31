
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 답변</title>
<link rel="stylesheet" href="/admin/css/qnadetail.css">
<link rel="stylesheet" href="../resources/static/css/admin/admin.css">
</head>
<body>

	<%@ include file="../admin/common/admin_submenu.jsp" %>

	<div id="wrap">
		<h1 class="mark">Q</h1>
		<h1 class="qnaTitle">${ qna.qna_subject }</h1>
		<p>${ qna.qna_content }</p>
		<h2 class="info">${ qna.qna_id }님의 질문이에요</h2>
		<h2 class="info"><fmt:formatDate value="${ qna.indate }" pattern="yy년 MM월 dd일"/>에 질문했어요</h2>
		
		<div id="qnaAnswerTitle">
			<h1 class="mark">A</h1>
			<h1 class="qnaTitle">답변</h1>
			
			<c:if test='${ qna.rep.equals("y") }'>
				<div id="qnaRepInfo">
					<i class="material-icons">check_circle</i>
					<h2>답변된 질문이에요</h2>
				</div>
			</c:if>
		</div>
		
		<form class ="table" action="spring.do" method="post">
			<input type="hidden" name="command" value="editqna">
			<input type="hidden" name="num" value="${ qna.qna_num }">
			
			<textarea name="reply" placeholder=
				<c:choose>
					<c:when test='${ qna.rep.equals("n") }'>
						"여기에 답변을 입력하세요"
					</c:when>
					<c:otherwise>
						"${ qna.qna_reply }"
					</c:otherwise> 
				</c:choose>
			
			></textarea>
			
			<input type="submit" value="답변 등록">
			<input type="button" value="QNA 삭제" onclick="deleteQna('${ qna.qna_num }')">
		</form>
	</div>
	
	<script type="text/javascript">
		function deleteQna(num) {
			if(confirm("QNA를 지우시겠어요?")) {
				location.href = "/deleteqna&num=" + num
			}
		}
	
	</script>

	<%@ include file="../admin/common/footer.jsp" %>

</body>
</html>