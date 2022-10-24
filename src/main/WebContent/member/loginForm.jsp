<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 로그인</title>
<link rel="stylesheet" href="/css/login.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>

	<div id="loginFormContent">
	
	<form  method="post" action="spring.do" name="loginFrm" id="loginForm">
		<input type="hidden" name="command" value="login">
		<div id="box1">
			<h1>Springfeed</h1><br>
				<input type="hidden" name="command" value="login">
				<div class="text_box">
					<input type ="text" name="userid" class="input"  placeholder="아이디" id="userid">
				</div>
				
				<div class="text_box">
					<input type="password" name="pwd" class="input"  placeholder="비밀번호" id="userpwd">
				</div>
		<input type="submit" id="login" value="로그인" onclick="return loginCheck()">
		
		<br><br>
	        ----------------- 또는 -----------------<br><br>
	    <div>&nbsp;&nbsp;&nbsp;${message}</div>
			<a href="spring.do?command=findidpwdform">아이디 혹은 비밀번호를 잊으셨나요?</a>
		</div>
		<br>
		<div id="box2">
			<br>계정이 없으신가요?<a href="spring.do?command=joinform"> 가입하기 </a>
		</div>
		<div id="box2">
			<br>관리자이신가요?<a href="spring.do?command=admin"> 관리자 페이지 </a>
		</div>
		앱을 다운로드하세요.<br><br>
	    <span>
		    <a href=https://apps.apple.com/app/instagram/id389801252?vt=lo>
		    	<img src="images/appleIcon.png" style="width: 50px;">
		    </a>&nbsp;
		    <a href="https://play.google.com/store/apps/details?id=com.instagram.android">
		    	<img src="images/playstoreIcon.png" style="width: 50px;">
		    </a>
	    </span>
	    
	    <br><br>
	</form>
	
	</div>

	<%@ include file="/footer.jsp" %>
	
	<script type="text/javascript">
		function loginCheck() {
			let idbox = document.getElementById("userid").value
			let pwdbox = document.getElementById("userpwd").value
						
			if(idbox == "") {
				alert("아이디를 입력해 주세요")
				return false
			}
			if(pwdbox == "") {
				alert("비밀번호를 입력해 주세요")
				return false
			}
			
			return true
			
		}	

 	</script>
	
</body>
</html>