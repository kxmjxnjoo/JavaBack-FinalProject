<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 추가</title>
<link rel="stylesheet" href="/css/addQna.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="addQnaBox">
	
		<h1>${ loginUser.name }님, 무엇이 궁금하세요?</h1>
		
		<form action="spring.do" method="post" id="addQnaForm">
			<input type="hidden" name="command" value="addqna">
			
			<input type="text" name="subject" placeholder="궁금한 것을 간단히 요약해 주세요" id="qnaTitle">
			<textarea name="content" placeholder="궁금한 것을 말씀해 주세요!" id="qnaContent"></textarea>
			
			<input type="submit" onclick="return checkQna()">	
		
		</form>
	
	</div>
	
	<script type="text/javascript">
		function checkQna() {
			let subject = document.getElementById("qnaTitle")
			let content = document.getElementById("qnaContent")
			
			if(subject.value == "") {
				alert("제목을 입력해 주세요!")
				subject.focus()
				return false
			}
			if(content.value == "") {
				alert("내용을 입력해 주세요!")
				content.focus()
				return false
			}
			
			if(confirm("QNA를 등록할까요?")) {
				return true
			}
			
			return false
		}
	</script>
	
	
	<%@ include file="/footer.jsp" %>
</body>
</html>