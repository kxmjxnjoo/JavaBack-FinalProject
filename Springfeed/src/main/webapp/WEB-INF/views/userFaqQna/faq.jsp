<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 묻는 질문</title>
<link rel="stylesheet" href="/css/userfaq.css">
</head>
<body>
	<%@ include file="../common/topnav.jsp" %>
	
	<div id="faqBox">
		<h1>자주 묻는 질문</h1>
		<h2>자주 묻는 질문을 모아봤어요. 만약 찾으시는 질문이 없으시면 직접 문의해 주세요.</h2>
		
		
		<c:choose>
			<c:when test="${ faqList == null || faqList.size() == 0}">
				<div id="noFaqBox">
					<i class="material-icons">sentiment_very_dissatisfied</i>
					<h1>이런, FAQ가 없어요! 일해라 개발자들</h1>
				</div>
			</c:when>
			<c:otherwise>
			
				<c:forEach var="fdto" items="${ faqList }">
					<div class="faqTitle" onclick="showFaq( '${fdto.FAQ_SUBJECT}', `${ fdto.FAQ_CONTENT }` );">
						<h3 class="faqQ">Q</h3>
						<h3>${ fdto.FAQ_SUBJECT }</h3>
					</div>
				</c:forEach>
			
			</c:otherwise>
		</c:choose>
		
	</div>
	
	<div id="faqPopup" style="display: none;">
		<div id="faqPopupBox">
			<div id="faqPopupBoxContent">
				<h1 id="faqPopupTitle"></h1>
				<p id="faqPopupContent"></p>
				<button onclick="showFaq()">돌아가기</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function showFaq(title, content) {
			let popup = document.getElementById("faqPopup")
			let titleEle = document.getElementById("faqPopupTitle")
			let contentEle = document.getElementById("faqPopupContent")
			
			if(popup.style.display == "none") {
				popup.style.display = ""
				titleEle.innerHTML = title
				contentEle.innerHTML = content
			} else {
				popup.style.display = "none"
			}
			
		}
	</script>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>