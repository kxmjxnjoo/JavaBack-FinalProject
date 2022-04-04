<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ loginUser.NAME }님 프로필 수정</title>
<link rel="stylesheet" href="/css/editProfile.css">
</head>
<body>
	<%@ include file="../common/topnav.jsp" %>
	
	<div id="userInfoBox">
		<div id="editProfileBox">
			<div id="editProfileBoxContent">				
				<form name="frm" method="post" >
				<!-- enctype="multipart/form-data" -->
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
					
					<h1>${ loginUser.NAME }님 정보 수정</h1>
				
					<input type="hidden" name="command" value="editprofile">
					
					<input type="text" placeholder="${ loginUser.USERID } (아이디는 바꿀 수 없어요)" readonly>
					<input type="hidden" name="userid" value="${ loginUser.USERID }">
					<input type="password" name="password" placeholder="<c:forEach begin="1" end="${ loginUser.password.length() }">*</c:forEach>">
					<input type="text" name="name" value="${ loginUser.NAME }">
					<input type="text" name="email" value="${ loginUser.EMAIL }">
					<input type="text" name="phone" value="${ loginUser.PHONE }">
					<textarea name="introduce" placeholder="${ loginUser.INTRODUCE }">${ loginUser.INTRODUCE }</textarea>
					
					<div>${ message }</div>
					<input type="submit" value="수정완료" onclick="editCheck();">
				</form>
				
				<form id="imgForm" method="post" enctype="multipart/form-data">
					<input type="file" name="user_img" id="input-file" accept=".jpg, .jpeg, .png, .gif" onchange="setThumbnail(event);"/>
					<div id="filename"></div>
				</form>
			</div>
		</div>
		
		<div id="withdrawBox">
			<div id="withdrawBoxContent">
				<h3>Springfeed가 마음에 안 드시나요?</h3>
				<input type="button" value="계정 비활성화" onclick="withdraw()">
			</div>
		</div>
	</div>
	
		<%@ include file="../common/footer.jsp" %>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
		            	$("#filename").empty();
		            	$("#filename").append("<div>"+data.FILENAME+"</div>");
		            	$("#newImage").val(data.FILENAME);
		            	$("#filename").append(
		            			"<img src='/images/"+data.FILENAME+"'' height='150'/>");
		            	
		            }
		        },
		        error: function() {
					alert("실패");
				}
			});
		});
	});
	
	</script>
	<script type="text/javascript">
		function withdraw(userid) {
			if(confirm("정말로 회원탈퇴할까요?")) {
				location.href = "/deleteAcount"
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