<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<link rel="stylesheet" href="/css/idcheckForm.css">
</head>
<body>
	<div id="idCheckBox">
		<form action="spring.do" method="post">
			<input type="hidden" name="command" value="idcheck">
		
			<input type="text" name="id" placeholder="중복확인 할 아이디" value="${ checkid }" id="idcheckid">
						
			<input type="submit" value="다시 확인">
			<input type="button" value="사용하기" onclick="useid()"> 
			
			<h2 id="message">${ message }</h2>
		</form>
		
	</div>
	
	<script type="text/javascript">
		function useid() {
			let message = document.getElementById("message").innerHTML
			let id = document.getElementById("idcheckid").value
			
			console.log(message)
			
			if(id == "") {
				alert("아이디를 입력해 주세요")
			} else if(message == "사용하실 수 있는 아이디에요") {
				opener.loginFrm.userid.value = id
				opener.loginFrm.idcheck.value = id
				self.close()
			} else {
				alert("사용할 수 없는 아이디에요")
			}
				
		}
	</script>
</body>
</html>