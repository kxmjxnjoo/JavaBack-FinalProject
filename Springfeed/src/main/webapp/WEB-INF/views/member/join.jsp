<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 회원가입</title>
<link rel="stylesheet" href="/css/join.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var id = $('#joinId').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{joinId:id},
            success:function(){
                console.log("처리 성공");
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
</script>
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<form method="post" action="spring.do" name="loginFrm" id="joinform">
		<input type="hidden" name="command" value="join">
		<div id="box1">
			<h1>Springfeed</h1>
			<h3 color="gray">친구들의 사진을 보려면 가입하세요.</h3><br>
				<input type="hidden" name="command" value="join">
				<div class="text_box">
				<input type ="text" name="phone" class="input"  placeholder=" 휴대폰 번호" id="joinPhone" value="${dto.phone}"></div>
				<div class="text_box">
				<input type ="text" name="email" class="input"  placeholder=" 이메일 주소" id="joinEmail" value="${dto.email}"></div>
				<div class="text_box">
				<input type ="text" name="name" class="input"  placeholder=" 성명" id="joinName" value="${dto.name}"></div>
				<div class="text_box">
				<input type ="text" name="userid" class="input" oninput="checkId()" placeholder=" 아이디" id="joinId" value="${dto.userid}"></div>
				<input type="hidden" id="useridcheck" name="idcheck">
				<span class="material-icons" id="id_ok"> task_alt </span>
				<span class="material-icons" id="id_already"> cancel </span>
				<div class="text_box">
				<input type="password" name="userpwd" class="input"  placeholder=" 비밀번호" id="joinPwd"></div>
		<input type="submit" id="login" value="가입" disabled="disabled">
		
		<input type="button" id="idCheckButton" value="아이디 중복확인" onclick="idCheck()">
		
		<br><br>
	    <div>&nbsp;&nbsp;&nbsp;${message}</div>
		</div>
		<br>
		<div id="box2">
			<br>계정이 있으신가요?<a href="spring.do?command=loginform"> 로그인 </a>
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
	
		<%@ include file="/footer.jsp" %>
	
		<script type="text/javascript">
			function checkJoin() {
				let phone = document.getElementById("joinPhone").value
				let email = document.getElementById("joinEmail").value
				let name = document.getElementById("joinName").value
				let id = document.getElementById("joinId").value
				let idcheck = document.getElementById("useridcheck").value
				let pwd = document.getElementById("joinPwd").value

				if(phone == "") {
					alert("휴대폰 번호가 입력되지 않았어요")
					return false
				}
				if(email == "") {
					alert("이메일이 입력되지 않았어요")
					return false
				}
				if(name == "") {
					alert("이름이 입력되지 않았어요")
					return false
				}
				if(id == "") {
					alert("아이디가 입력되지 않았어요")
					return false
				}
				if(id != idcheck) {
					alert("아이디 중복확인을 해 주세요")
					return false
				}
				if(pwd == "") {
					alert("비밀번호가 입력되지 않았어요")
					return false
				}
				
				
				return true
			}
			
			function idCheck() {
				let id = document.getElementById("joinId")
				
				if(id.value == "") {
					alert("아이디 중복확인을 위해 아이디를 입력해 주세요")
					id.focus()
				} else {
					window.open("spring.do?command=idcheck&id=" + id.value)
				}
			} 
		
		</script>
	
</body>
</html>