<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>아이디 비밀번호 찾기</title>
<link rel="stylesheet" href="/css/findidpwd.css">
<link rel="stylesheet" href="/css/join.css">
</head>
<body>	
	<%@ include file="../common/topnav.jsp" %>
	
	<form method="post" action="/find/id" name="loginFrm" id="findForm">
		<div class="box1" id="findId">
			<h1>Springfeed</h1>
			<h3 color="gray">아이디 찾기</h3><br>
			<div class="text_box" id="name_box">
				<input type ="text" name="name" class="input"  placeholder=" 성명" id="joinName" value="${dto.name}">
			</div>
			<div class="text_box" id="phone_box">
				<input type ="text" name="phone" class="input"  placeholder=" 전화번호(10~11자리 숫자)" id="joinPhone" value="${dto.phone}">
			</div>
			<input type="submit" value="아이디 찾기" id="login" onclick="return checkId();">
		</div>
	
		<div class="box1" id="findPw">
			<h1>Springfeed</h1>
			<h3 color="gray">비밀번호 찾기</h3><br>
			<div class="text_box" id="name_box">
				<input type ="text" name="name" class="input"  placeholder=" 성명" id="joinName" value="${dto.name}">
			</div>
			<div class="text_box" id="phone_box">
				<input type ="text" name="phone" class="input"  placeholder=" 전화번호(10~11자리 숫자)" id="joinPhone" value="${dto.phone}">
			</div>
			<div class="text_box" id="email_box">
				<input type ="text" name="email" class="input"  placeholder=" 이메일 (example@springfeed.com)" id="joinEmail" value="${dto.email}">
			</div>
			<input type="submit" value="비밀번호 찾기" id="login" onclick="return checkPwd();">
		</div>
	</form>
	
		<%@ include file="../common/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript">
		
	  	$(".getTempPwd").click(function () {
			let email = $("#email").val();
			let userid = $("#userid").val();
			
			$.ajax({
				type:"GET",
				url : "/find/pw",
				data : {
					"email" : email,
					"userid" : userid
				},
				success: function(res) {
					if(res['check']) {
						swal ("발송 완료", "임시 비밀번호를 이메일로 전송했어요.").then((OK) = > {
							if(OK) {
									
								$a.ajax({
									type: "POST",
									url: "/find/sendEmail",
									data: {
										"email" : email,
										"userid" : userid
									}
								})
							}
						})
					}
					}
				})
			})
	
	
	
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
		/* 
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
		} */
	</script>
	
</body>
</html>