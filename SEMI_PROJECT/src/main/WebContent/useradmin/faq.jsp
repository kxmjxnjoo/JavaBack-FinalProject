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
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="faqBox">
		<h1>자주 묻는 질문</h1>
		<h2>자주 묻는 질문을 모아봤어요. 만약 찾으시는 질문이 없으시면 직접 문의해 주세요.</h2>
		
		<c:forEach var="fdto" items="${ faqList }">
			<div class="faqTitle" onclick="showFaq('${faq_subject}', '${ fdto.faq_content }')">
				<h3 class="faqQ">Q</h3>
				<h3>${ fdto.faq_subject }</h3>
			</div>
		</c:forEach>
		
	</div>
	
	<div id="faqPopup">
		<div id="faqPopupBox">
			<h1 id="faqPopupTitle"></h1>
			<p id="faqPopupContent"></p>
		</div>
	</div>
	
	<script type="text/javascript">
		function showFaq(title, content) {
			
		}
	</script>
	
	<%@ include file="/footer.jsp" %>
</body>
</html>