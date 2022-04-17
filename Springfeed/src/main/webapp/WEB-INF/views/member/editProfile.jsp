<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${ loginUser.NAME }님 프로필 수정</title>
<link rel="stylesheet" href="/css/join.css">
<link rel="stylesheet" href="/css/editProfile.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		$('#input-file').change(function(){
			var formselect = $("#imgForm")[0];   // 지목된 폼을 변수에 저장
			var formdata = new FormData(formselect);   // 전송용 폼객에 다시 저장
			
			$.ajax({
				url:"/uploadImg",
				type:"POST",
				enctype:"multipart/form-data",
				async: false,
				data: formdata,
		    	timeout: 10000,
		    	contentType : false,
		        processData : false,
		        success : function(data){
		            if(data.STATUS == 1){
		            	
		            	$("#newImage").val(data.FILENAME);
		            
		            }
		        },
		        error: function() {
					alert("파일 이름이 너무 길거나 용량을 초과했습니다. 다시 선택해주세요.");
				}
			});
		});
	});
</script>
</head>
<body>
<%@ include file="../common/message.jsp" %>

		<form name="frm" method="post" id="joinform">
			<div class="box1">
				<h1 style="margin-bottom: 20px; font-size: 3rem">Springfeed</h1>
				<h3 style="color:gray;">회원 정보 수정</h3><br>
				<div>
					<div id="thumbnail">
						<div id ="image_container" >
							<img id="userprofile" style="width:250px" src="/images/${ loginUser.IMG == null || loginUser.IMG.equals("") ? "tmpUserIcon.png" : loginUser.IMG }">
						</div>
						<input type="hidden" name="oldPicture" value="${ loginUser.IMG }"/>
						<input type="hidden" name="img" id="newImage" >
						<label class="input-file-button" for="input-file" id="input-file-button" onclick="resetImg();">
						  사진 수정
						</label>
					
					</div>
				</div>
				<div class="text_box" id="id_box">
					<input type="text" class="input" id="joinId"  placeholder="${ loginUser.USERID } (아이디는 바꿀 수 없어요)" readonly>
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
				
				<textarea class="text_box" name="introduce" id="introduce_box" placeholder="${ loginUser.INTRODUCE }">${ loginUser.INTRODUCE }</textarea>
					
				
				<input type="submit" id="login" value="회원 정보 수정" onclick="editCheck();">
			</div>
		</form>
			
	
			<form id="imgForm" method="post" enctype="multipart/form-data">
				<input type="file" name="fileName" id="input-file" accept=".jpg, .jpeg, .png, .gif" onchange="setThumbnail(event); uploadImg();"/>
			</form>
		
		<div id="withdrawBox">
			<div id="withdrawBoxContent">
				<c:choose>
					<c:when test="${ dto.useyn ne 'p' }">
						<h3>친구들에게만 내 사진을 공유하고 싶어요!</h3>
						<input type="button" id="login" value="계정 비공개" onclick="privateAccount();">				
					</c:when>
					<c:otherwise>
						<h3>Springfeed의 모두와 친구가 되고 싶어요!</h3>
						<input type="button" id="login" value="계정 공개" onclick="publicAccount();">		
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		
		<div id="withdrawBox">
			<div id="withdrawBoxContent">
				<h3>Springfeed가 마음에 안 드시나요?</h3>
				<input type="button" id="login" value="계정 비활성화" onclick="withdraw();">
			</div>
		</div>
	
		
	
		
		
	

	<script type="text/javascript">
		function withdraw() {
			if(confirm("정말로 계정을 비활성화 할까요??")) {
				location.href = "/deleteAcount"
			}
		}
		
		function privateAccount() {
			if(confirm("계정을 비공개로 전환 할까요?")) {
				location.href = "/user/private"
			}
		}
		
		function publicAccount() {
			if(confirm("계정을 공개로 전환 할까요?")) {
				location.href = "/user/public"
			}
		}
		
		
		function setThumbnail(event) { 
			var reader = new FileReader(); 
			reader.onload = function(event) { 
				var img = document.createElement("img"); 
				img.setAttribute("src", event.target.result); 
				document.querySelector("div#image_container").appendChild(img); 
			}; 
			
			reader.readAsDataURL(event.target.files[0]); 
		}
		

		function resetImg(){
			let elem = document.querySelector("div#image_container img");
			elem.parentNode.removeChild(elem);
		}
		
		function editCheck(){
			let theForm = document.frm;
			if()
			
			let result = confirm('회원 정보를 수정할까요?');
			if(result) {
				theForm.action="/user/edit";
				theForm.submit();
			} else {
				return;
			}
		}

	</script>
</body>
</html>