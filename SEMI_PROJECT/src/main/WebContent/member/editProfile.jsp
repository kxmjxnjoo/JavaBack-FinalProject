<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ loginUser.name }님 프로필 수정</title>
<link rel="stylesheet" href="css/editProfile.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="userInfoBox">
		<div id="editProfileBox">
			<div id="editProfileBoxContent">				
				<form>
					<h1>${ loginUser.name }님 정보 수정</h1>
				
					<input type="hidden" name="command" value="editprofile">
								
					<input type="text" name="userid" placeholder="${ loginUser.userid } (아이디는 바꿀 수 없어요)" readonly>
					<input type="text" name="pwd" placeholder="***">
					<input type="text" name="name" placeholder="${ loginUser.name }">
					<input type="text" name="email" placeholder="${ loginUser.email }">
					<input type="text" name="phone" placeholder="${ loginUser.phone }">
					<textarea name="introduce" placeholder=""></textarea>
					
					<input type="submit" value="수정완료">
				</form>
			</div>
		</div>
		
		<div id="withdrawBox">
			<div id="withdrawBoxContent">
				<h3>Springfeed가 마음에 안 드시나요?</h3>
				<input type="button" value="회원탈퇴" onclick="withdraw()">
			</div>
		</div>
	</div>
	
		<%@ include file="/footer.jsp" %>
	
	
	<script type="text/javascript">
		function withdraw(userid) {
			if(confirm("정말로 회원탈퇴할까요?")) {
				location.href = "spring.do?command=withdraw"
			}
		}
	</script>
</body>
</html>