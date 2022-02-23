<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지</title>
<link rel="stylesheet" href="/css/message.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="messageBox">
		<div id="userSelectBox">
			<div id="loginUserInfo">
				<h1>${ loginUser.userid }</h1>
				
				<i class="material-icons">add_box</i>
			</div>
			
			<div id="userBox">
				<c:forEach begin="1" end="10">
					<div class="userSelect">
						<img class="userImg" src="/images/tmpUserIcon.png">
						
						<div class="userText">
							<h3>USERNAME</h3>
							<h3>LATEST MESSAGE</h3>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
		<div id="userMessageBox">
		
		</div>
	
	</div>
	
	
		<%@ include file="/footer.jsp" %>
	

</body>
</html>