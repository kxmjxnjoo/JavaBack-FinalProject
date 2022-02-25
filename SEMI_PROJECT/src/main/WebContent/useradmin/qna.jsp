<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 QnA</title>
<link rel="stylesheet" href="/css/qna.css">
</head> 
<body>
	<%@ include file="/topnav/topnav.jsp" %>

	<div id="qnaBox">
	
		<c:if test="${ loginUser != null }">
			<div id="userQnaBox">
				<h1>${ loginUser.userid }님이 남긴 질문이에요</h1>
				
				<c:choose>
					<c:when test="${ userQnaList == null }">
						<h2>${ loginUser.userid }님은 질문을 남기지 않으셨어요</h2>
					</c:when>
					<c:otherwise>
						<c:forEach var="qdto" items="${ userQnaList }">
							<div class="qnaTitle" onclick='showQna("${ qdto.qna_subject}", "${ qdto.qna_content }", "${ qdto.qna_reply }")'>
								<div class="qnaTitleContent">
									<h3>Q</h3>
									<h3>${ qdto.qna_subject }</h3>
									<h3><fmt:formatDate value="${ qdto.indate }" pattern="yy년 MM월 dd일"></fmt:formatDate></h3>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>	
				
				</c:choose>
			</div>
		</c:if>
		
		<div id="allQnaBox">
			<h1 id="allQnaTitle">다른 분들이 남긴 질문이에요</h1>
		
			<c:forEach var="qdto" items="${ qnaList }">
			<div class="qnaTitle" onclick='showQna("${ qdto.qna_subject}", "${ qdto.qna_content }", "${ qdto.qna_reply }")'>
				<div class="qnaTitleContent">
					<h3>Q</h3>
					<h3>${ qdto.qna_subject }</h3>
					<h3><fmt:formatDate value="${ qdto.indate }" pattern="yy년 MM월 dd일"></fmt:formatDate></h3>
				</div>
			</div>
			</c:forEach> 
		</div>
		
	</div>
	
		<div id="popup" style="display: none;">
		<div id="popupBox">
			<div id="popupBoxContent">
				<h1 class="mark">Q</h1>
				<h1 id="popupTitle"></h1>
				<p id="popupContent"></p>
				
				<h1 class="mark">A</h1>
				<h1>답변</h1>
				<p id="answerPopupContent"></p>
				
				<button onclick="showQna()">돌아가기</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function showQna(title, content, reply) {
			let popup = document.getElementById("popup")
			let titleEle = document.getElementById("popupTitle")
			let contentEle = document.getElementById("popupContent")
			let answerContentEle = document.getElementById("answerPopupContent")
			
			if(popup.style.display == "none") {
				popup.style.display = ""
				titleEle.innerHTML = title
				contentEle.innerHTML = content
				answerContentEle.innerHTML = reply
			} else {
				popup.style.display = "none"
			}
			
		}
	</script>
	
		
	<%@ include file="/footer.jsp" %>
</body>
</html>