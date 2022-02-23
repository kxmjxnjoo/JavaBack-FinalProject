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
					
					<input type="text" name="name" placeholder="이름" id="findidName">
					<input type="text" name="phone" placeholder="전화번호" id="findidPhone">
					
					<input type="submit" value="아이디 찾기" onclick="return checkId()">
				</form>
			</div>
		</div>
	
		<div id="findpwdBox">
			<div id="findpwdBoxContent">
				<form action="spring.do" method="post">
					<h1>비밀번호 찾기</h1>
					
					<input type="hidden" name="command" value="findpwd">
					
					<input type="text" name="userid" placeholder="아이디" id="findpwdUserid">
					<input type="text" name="name" placeholder="이름" id="findpwdName">
					<input type="text" name="phone" placeholder="전화번호" id="findpwdPhone">
					<input type="button" onclick="getCertNum()" value="인증번호">
					<input type="text" name="certnum" placeholder="인증번호" id="findpwdCertnum">
					
					<input type="submit" value="비밀번호 찾기" onclick="return checkPwd()"> 
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
		
		function checkId() {
			let name = document.getElementById("findidName")
			let phone = document.getElementById("findidPhone")
		
			if(name.value == "") {
				alert("이름이 입력되지 않았어요")
				return false
			}
			if(phone.value == "") {
				alert("전화번호가 입력되지 않았어요")
				return false
			}
			
			return true
		}
		
		function checkPwd() {
			let id = document.getElementById("findpwdUserid")
			let name = document.getElementById("findpwdName")
			let phone = document.getElementById("findpwdPhone")
			let certnum = document.getElementById("findpwdCertnum")
			
			if(id.value == "") {
				alert("아이디가 입력되지 않았어요")	
				return false
			}
			if(name.value == "") {
				alert("이름이 입력되지 않았어요")
				return false
			}
			if(phone.value == "") {
				alert("전화번호가 입력되지 않았어요")
				return false
			}
			if(certnum.value == "") {
				alert("인증번호가 입력되지 않았어요")
				return false
			}
			
			return true
		}
	</script>
	
</body>
</html>