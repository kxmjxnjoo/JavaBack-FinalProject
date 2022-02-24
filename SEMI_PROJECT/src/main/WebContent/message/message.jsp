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
				<c:forEach var="user" items="${ userList }">
					<div class="userSelect">
						<img class="userImg" src="/images/${ user.fromImg }">
						
						<div class="userText">
							<h3>${ user.messageFrom }</h3>
							<h3>${ user.content }</h3>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
		<div id="userMessageBox">
		
			<c:choose>
				<c:when test="${ messageWith == null }">
					<div id="messageNoSelectionMessage">
						<i class="material-icons">sms</i>
						<h1>팔로우한 친구들에게 메세지를 보내 보세요!</h1>
						<button>메세지 보내기</button>
					</div>
				</c:when>
				
				<c:otherwise>
					<div id="messageHistoryBox">
						<div id="messageTitle">
							<img src="/images/${ messageWith.img }">
							
							<h1>${ messageWith.userid }</h1>
							
							<i class="material-icons">more_horiz</i>
						</div>
					
						<div id="messageHistory">
							<c:forEach var="message" items="${ messageHistory }">
								<c:choose>
									<c:when test="${ message.messageFrom == loginUser.userid }">
										<div class="messageRight">
											<div class="messageRightBox">
												<h3>${ message.content }</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="messageLeft">
											<div class="messageLeftBox">
												<img src="/images/${ message.fromImg }">
												<h3>${ message.content }</h3>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						
						<div id="sendMessageBar">
							<form action="spring.do" method="post">
								<input type="hidden" name="command" value="sendmessage">
								<input type="hidden" name="messagewith" value="${ messageWith.userid }">
								
								<input type="text" name="content">
								
								<input type="submit" value="보내기">
							</form>
						</div>
						
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
	
	
	
	<%@ include file="/footer.jsp" %>
</body>
</html>