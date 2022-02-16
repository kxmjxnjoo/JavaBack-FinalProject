<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="join.css">
</head>
<body>
<form  method="post" action="spring.do" name="loginFrm">
	<input type="hidden" name="command" value="login">
	<div id="box1">
		<h1>Springfeed</h1>
		<h3 color="gray">친구들의 사진을 보려면 가입하세요.</h3><br>
			<input type="hidden" name="command" value="adminLogin">
			<div class="text_box">
			<input type ="text" name="adminid" class="input"  placeholder=" 휴대폰 번호 또는 아메일 주소" ></div>
			<div class="text_box">
			<input type ="text" name="adminid" class="input"  placeholder=" 성명" ></div>
			<div class="text_box">
			<input type ="text" name="adminid" class="input"  placeholder=" 사용자 이름" ></div>
			<div class="text_box">
			<input type="password" name="password" class="input"  placeholder=" 비밀번호" ></div>
	<input type="submit" id="login" value="가입" onClick="location.href='spring.do?command=join">
	<br><br>
    <div color="red">&nbsp;&nbsp;&nbsp;${message}</div>
	</div>
	<br>
	<div id="box2">
		<br>계정이 있으신가요?<a href="location.href='spring.do?command=login'"> 로그인 </a>
	</div>
	앱을 다운로드하세요.<br><br>
    <span><a href=https://apps.apple.com/app/instagram/id389801252?vt=lo><img src="images/App.jpg" width="150"></a>
    &nbsp;<a href=https://play.google.com/store/apps/details?id=com.instagram.android&referrer=utm_source%3Dinstagramweb&utm_campaign=loginPage&ig_mid=1E9D160D-331E-4217-9569-05DF4FE4FEA7&utm_content=lo&utm_medium=badge>
    <img src="images/Google.jpg" width="150"></a></span>
    <br><br>
</form>
<%@ include file="footer.jsp" %>
</body>
</html>
