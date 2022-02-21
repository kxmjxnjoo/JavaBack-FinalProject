<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기0</title>
<link rel="stylesheet" href="/css/findidpwd.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="findidpwdBox">
	
		<div id="findidBox">
			<div id="findidBoxContent">
				<form action="spring.do" method="post">
					<h1>아이디 찾기</h1>
				
					<input type="hidden" name="command" value="findid">
					
					<input type="text" name="name" placeholder="이름">
					<input type="text" name="phone" placeholder="전화번호">
					
					<input type="submit">
				</form>
			</div>
		</div>
	
		<div id="findpwdBox">
			<div id="findpwdBoxContent">
				<form action="spring.do" method="post">
					<h1>비밀번호 찾기</h1>
					
					<input type="hidden" value="findpwd">
					
					<input type="text" name="userid" placeholder="아이디">
					<input type="text" name="name" placeholder="이름">
					<input type="text" name="phone" placeholder="전화번호">
					<input type="button" onclick="getCertNum()" value="인증번호">
					<input type="text" name="certnum" placeholder="인증번호">
					
					<input type="submit"> 
				</form>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
		function getCertNum() {
			let certButton = document.querySelector("#findpwdBox:nth-child(5)")
			
			if(certButton.value != "") {
				alert("인증번호는 1111에요")
			} else {
				alert("전화번호를 입력해 주세요")
			}
		}
	</script>
	
</body>
</html>