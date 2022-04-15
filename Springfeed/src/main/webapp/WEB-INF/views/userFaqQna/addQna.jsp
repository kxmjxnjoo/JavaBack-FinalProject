<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>QNA 추가</title>
<link rel="stylesheet" href="/css/addQna.css">
</head>
<body>
	<%@ include file="../common/topnav.jsp" %>
	
	<div id="addQnaBox">
	
		<h1>${ loginUser.NAME }님, 무엇이 궁금하세요?</h1>
		
		<form action="/qna/add" method="post" id="addQnaForm">
			
			<input type="text" required name="qna_subject" placeholder="무엇이 궁금하신가요?" id="qnaTitle" value="${qdto.subject}"> 
			<textarea required name="qna_content" placeholder="자세히 듣고 싶어요" id="qnaContent">${qdto.content}</textarea>
			
			<input type="submit" onclick="return checkQna()">	
		
		</form>
	
	</div>
	
	<script type="text/javascript">
		function checkQna() {
			let subject = document.getElementById("qnaTitle")
			let content = document.getElementById("qnaContent")
			
			if(confirm("QNA를 등록할까요?")) {
				return true
			}
			
			return false
		}
	</script>
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>