<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 로그인</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/css/loginTest.css">
<link rel="stylesheet" href="/css/spring.css">
<script type="text/javascript" src="/script/member.js"></script>

<script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
<script src="/react-component/Topnav.js"></script>


<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>


<body>

<%@ include file="../common/topnav.jsp" %>
    <div class="container">
        <div class="row">
			<div class="col-md-5 mx-auto">
			<div id="first">
				<div class="myform form ">
					 <div class="logo mb-3">
						 <div class="col-md-12 text-center">
							<h1 style="margin:20px; font-size:3rem">Springfeed</h1>
							<h2 style="margin:20px;"> 로그인</h3>
						 </div>
					</div>
                   <form method="post" name="login" action="/login">
                           <div class="form-group">
                              <input type="text" name="userid"  required class="loginInput" id="userid" aria-describedby="emailHelp" placeholder="아이디">
                           </div>
                           <div class="form-group">
                              <input type="password" required  name="password" id="password"  class="loginInput" aria-describedby="emailHelp" placeholder="비밀번호">
                           </div>
                           <div class="form-group">
                              
                           </div>
                           <div class="form-group">
                              <button type="submit" class="loginButton">로그인</button>
                           </div>
                           <div class="col-md-12 ">
                              <div class="login-or">
                                 <hr class="hr-or">
                                 <span class="span-or">or</span>
                              </div>
                           </div>
                           <div class="form-group">
                             <p class="text-center"> 아직 계정이 없으신가요? <a href="/join/form" id="signup">가입하기</a></p>
                             <p class="text-center"> 관리자이신가요? <a href="/admin" id="signup">관리자 로그인</a></p>
                           </div>
                        </form>
                 
				</div>
			</div>
			</div>
		</div>
      </div>   
<%@ include file="../common/footer.jsp" %>  
</body>