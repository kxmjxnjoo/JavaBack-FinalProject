<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 회원가입</title>
<link rel="stylesheet" href="/css/join.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<form method="post" action="spring.do" name="loginFrm">
		<input type="hidden" name="command" value="join">
		<div id="box1">
			<h1>Springfeed</h1>
			<h3 color="gray">친구들의 사진을 보려면 가입하세요.</h3><br>
				<input type="hidden" name="command" value="join">
				<div class="text_box">
				<input type ="text" name="phone" class="input"  placeholder=" 휴대폰 번호" ></div>
				<div class="text_box">
				<input type ="text" name="email" class="input"  placeholder=" 아메일 주소" ></div>
				<div class="text_box">
				<input type ="text" name="name" class="input"  placeholder=" 성명" ></div>
				<div class="text_box">
				<input type ="text" name="userid" class="input"  placeholder=" 아이디" ></div>
				<div class="text_box">
				<input type="password" name="pwd" class="input"  placeholder=" 비밀번호" ></div>
		<input type="submit" id="login" value="가입">
		<br><br>
	    <div>&nbsp;&nbsp;&nbsp;${message}</div>
		</div>
		<br>
		<div id="box2">
			<br>계정이 있으신가요?<a href="location.href='spring.do?command=login'"> 로그인 </a>
		</div>
		앱을 다운로드하세요.<br><br>
	    <span><a href="https://apps.apple.com/app/instagram/id389801252?vt=lo"><img src="images/App.jpg" width="150"></a>
	    &nbsp;<a href="https://play.google.com/store/apps/details?id=com.instagram.android&referrer=utm_source%3Dinstagramweb&utm_campaign=loginPage&ig_mid=1E9D160D-331E-4217-9569-05DF4FE4FEA7&utm_content=lo&utm_medium=badge">
	    <img src="images/Google.jpg" width="150"></a></span>
	    <br><br>
	</form>
</body>
</html>