<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 로그인</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/spring.css">
<script type="text/javascript" src="/script/member.js"></script>

<script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
<script src="/react-component/Topnav.js"></script>
</head>
<body>
<%@ include file="../common/topnav.jsp" %>

    <div class="container">
        <div class="row">
			<div class="col-md-5 mx-auto ">
				<div id="first">
					<form  method="post" action="/login" name="loginFrm" id="loginForm">
						<div id="box1">
						<h1>Springfeed</h1><br>
						<!-- <input type="hidden" name="command" value="login"> -->
						<div class="text_box">
						<input required type ="text" name="userid" class="input"  placeholder="아이디" id="userid" value="${dto.userid}">
						</div>
						
						<div class="text_box">
						<input required type="password" name="password" class="input"  placeholder="비밀번호" id="userpwd">
						</div>
						<input type="submit" id="login" value="로그인">
						
						<br><br>
						        ----------------- 또는 -----------------<br><br>
						    <%-- <div>&nbsp;&nbsp;&nbsp;${message}</div> --%>
						<a href="/find/form">아이디 혹은 비밀번호를 잊으셨나요?</a>
						</div>
						<br>
						<div id="box2">
						<br>계정이 없으신가요?<a href="/join/form"> 가입하기 </a>
						</div>
						<div id="box2">
						<br>관리자이신가요?<a href="/admin"> 관리자 페이지 </a>
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
				</div>
			</div>
		</div>
      </div>   
<%@ include file="../common/footer.jsp" %>  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>