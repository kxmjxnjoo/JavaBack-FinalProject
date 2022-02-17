<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 로그인</title>
<link rel="stylesheet" href="/login/login.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>

<form  method="post" action="spring.do" name="loginFrm" id="loginForm">
	<input type="hidden" name="command" value="login">
	<div id="box1">
		<h1>Springfeed</h1><br>
			<input type="hidden" name="command" value="adminLogin">
			<div class="text_box">
			<input type ="text" name="userid" class="input"  placeholder="사용자 아이디를 입력하세요" ></div>
			<div class="text_box">
			<input type="password" name="pwd" class="input"  placeholder="비밀번호" ></div>
	<input type="submit" id="login" value="로그인" onClick="location.href='spring.do?command=login">
	<br><br>
        ----------------- 또는 -----------------<br><br>
    <div color="red">&nbsp;&nbsp;&nbsp;${message}</div>
		<a href="location.href='spring.do?command=findIdPw'">비밀번호를 잊으셨나요?</a>
	</div>
	<br>
	<div id="box2">
		<br>계정이 없으신가요?<a href="location.href='spring.do?command=joinform'"> 가입하기 </a>
	</div>
	앱을 다운로드하세요.<br><br>
    <span><a href=https://apps.apple.com/app/instagram/id389801252?vt=lo><img src="images/App.jpg" width="150"></a>
    &nbsp;<a href=https://play.google.com/store/apps/details?id=com.instagram.android&referrer=utm_source%3Dinstagramweb&utm_campaign=loginPage&ig_mid=1E9D160D-331E-4217-9569-05DF4FE4FEA7&utm_content=lo&utm_medium=badge>
    <img src="images/Google.jpg" width="150"></a></span>
    <br><br>
</form>

</body>
</html>