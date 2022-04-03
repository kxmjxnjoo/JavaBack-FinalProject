<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 회원가입</title>
<link rel="stylesheet" href="/css/join.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var id = $('#joinId').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{userid:id},
            success:function(cnt){
                if(cnt != 1) {
                	$('#id_box #id_ok').css("display", "inline-block");
                	$('#id_box #id_already').css("display", "none");
                } else {
                	$('#id_box #id_already').css("display", "inline-block");
                	$('#id_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    
    function checkPhone(){
        var phone = $('#joinPhone').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{phone:phone},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#phone_box #id_ok').css("display", "inline-block");
                	$('#phone_box #id_already').css("display", "none");
                } else {
                	$('#phone_box #id_already').css("display", "inline-block");
                	$('#phone_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkEmail(){
        var email = $('#joinEmail').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{email:email},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#email_box #id_ok').css("display", "inline-block");
                	$('#email_box #id_already').css("display", "none");
                } else {
                	$('#email_box #id_already').css("display", "inline-block");
                	$('#email_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkPwd(){
        var pwd = $('#joinPwd').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{password:pwd},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#pwd_box #id_ok').css("display", "inline-block");
                	$('#pwd_box #id_already').css("display", "none");
                	$('#pwd_box #message').css("display", "none");
                } else {
                	$('#pwd_box #id_already').css("display", "inline-block");
                	$('#pwd_box #message').css("display", "inline-block");
                	$('#pwd_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkName(){
        var name = $('#joinName').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{name:name},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#name_box #id_ok').css("display", "inline-block");
                	$('#name_box #id_already').css("display", "none");
                } else {
                	$('#name_box #id_already').css("display", "inline-block");
                	$('#name_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
</script>
</head>
<body>
	<%@ include file="../common/topnav.jsp" %>
	
	<form method="post" action="/join" name="loginFrm" id="joinform">
		<div id="box1">
			<h1>Springfeed</h1>
			<h3 color="gray">친구들의 사진을 보려면 가입하세요.</h3><br>
				<input type="hidden" name="command" value="join">
				<div class="text_box" id="phone_box">
					<input type ="text" name="phone" class="input"  placeholder=" 휴대폰 번호 (숫자만 입력해주세요)" id="joinPhone" value="${dto.phone}"
					oninput="checkPhone();">
					<span class="material-icons" id="id_ok" style="color:#00de27" style="float:right"> task_alt </span>
					<span class="material-icons" id="id_already" style="color:red"  style="float:right"> cancel </span>
				</div>
				<div class="text_box" id="email_box">
					<input type ="text" name="email" class="input"  placeholder=" 이메일 (example@springfeed.com)" 
					id="joinEmail" value="${dto.email}" oninput="checkEmail();">
					<span class="material-icons" id="id_ok" style="color:#00de27" style="float:right"> task_alt </span>
					<span class="material-icons" id="id_already" style="color:red"  style="float:right"> cancel </span>
				</div>
				<div class="text_box" id="name_box">
					<input type ="text" name="name" class="input"  placeholder=" 성명" id="joinName" 
					value="${dto.name}" oninput="checkName();">
					<span class="material-icons" id="id_ok" style="color:#00de27"> task_alt </span>
					<span class="material-icons" id="id_already" style="color:red"> cancel </span>
				</div>
				<div class="text_box" id="id_box">
					<input type ="text" name="userid" class="input" oninput="checkId()" placeholder=" 아이디" id="joinId" 
					value="${dto.userid}">
					<span class="material-icons" id="id_ok" style="color:#00de27"> task_alt </span>
					<span class="material-icons" id="id_already" style="color:red"> cancel </span>
				</div>
				<div class="text_box" id="pwd_box">
					<input type="password" name="password" class="input" oninput="checkPwd();" 
					placeholder=" 비밀번호(영문,숫자,특수문자 포함 8~16자리)"  id="joinPwd">
					<div>
						<span class="material-icons" id="id_ok" style="color:#00de27"> task_alt </span>
						<!-- <span id="message" style="display:none; float:left; color:red" >영문,숫자,특수문자 포함 8~16자리</span> -->
						<span class="material-icons" id="id_already" style="float:left; color:red "> cancel </span>
					</div>
				</div>
		<input type="submit" id="login" value="가입">
		
		
		<br><br>
	    <div>&nbsp;&nbsp;&nbsp;${message}</div>
		</div>
		<br>
		<div id="box2">
			<br>계정이 있으신가요?<a href="/login/form"> 로그인 </a>
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
	
</body>
</html>