<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>스프링 피드 QnA</title>
<link rel="stylesheet" href="/css/qna.css">
<link rel="stylesheet" href="/css/common.css">
</head> 
<body>
	<%@ include file="../common/topnav.jsp" %>

	<div id="qnaBox">
	
		<c:if test="${ loginUser != null }">
			<div id="userQnaBox">
				<h1>${ loginUser.USERID }님이 남긴 질문이에요</h1>
				
				<c:choose>
					<c:when test="${ userQnaList == null || userQnaList.size() == 0 }">
						<div id="noUserQnaBox">
							<i class="material-icons">sentiment_very_satisfied</i>
							<h2>${ loginUser.USERID }님은 질문을 남기지 않으셨어요. 질문이 있으시면 언제든 남겨 주세요!</h2>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="qdto" items="${ userQnaList }">
							<div class="qnaTitle" onclick='showQna("${ qdto.QNA_SUBJECT}", "${ qdto.QNA_CONTENT }", "${ qdto.QNA_REPLY }")'>
								<div class="qnaTitleContent">
									<h3 style="margin-right: 10px;">Q</h3>
									<h3> ${ qdto.QNA_SUBJECT } </h3>
									<h3><fmt:formatDate value="${ qdto.INDATE }" pattern="yy-MM-dd"></fmt:formatDate></h3>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>	
				
				</c:choose>
				
				<button id="addQnaButton" onclick="location.href='/qna/add/form'">질문하기</button>
				
			</div>
		</c:if>
		
		<%-- <div id="allQnaBox">
			<h1 id="allQnaTitle">다른 분들이 남긴 질문이에요</h1>
		
			<c:choose>
				<c:when test="${ qnaList == null || qnaList.size() == 0 }">
					<div id="noQnaBox">
						<i class="material-icons">sick</i>
						<h1>아무도 질문을 남겨 주시지 않았어요... 저 잠시 혼자 있고 싶어요...</h1>
					</div>
				</c:when>
				<c:otherwise>
				
					<c:forEach var="qdto" items="${ qnaList }">
						<div class="qnaTitle" onclick='showQna("${ qdto.QNA_SUBJECT}", "${ qdto.QNA_CONTENT }", "${ qdto.QNA_REPLY }")'>
							<div class="qnaTitleContent">
								<h3>Q</h3>
								<h3>${ qdto.QNA_SUBJECT }</h3>
								<h3><fmt:formatDate value="${ qdto.INDATE }" pattern="yy년 MM월 dd일"></fmt:formatDate></h3>
							</div>
						</div>
					</c:forEach> 
				
				</c:otherwise>
			</c:choose>
			
			
		</div> --%>
		
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
				if(reply == null || reply == '') {
					answerContentEle.innerHTML = "아직 답변이 오지 않았어요"
					answerContentEle.style.color = "silver"
				} else 
					answerContentEle.innerHTML = reply
					answerContentEle.style.color = "black"
			} else {
				popup.style.display = "none"
			}
			
		}
	</script>
	
		
	<%@ include file="../common/footer.jsp" %>
</body>
</html>