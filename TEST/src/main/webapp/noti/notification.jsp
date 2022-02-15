<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ loginUser.name }님의 알림</title>
<link rel="stylesheet" href="/css/noti.css">
</head>
<body>

	<%@ include file="/topnav/topnav.jsp" %>
	
	<c:forEach var="noti" items="${ notiList }">
	<div class="notiBox">
		<div class="notiBoxContent">
			<img class="userImg" src="${ noti.memberImg }">
			
			<div class="notiTextBox">
				<h2 class="userName notiText">${ noti.userFrom }</h2>
				
				<c:choose>
					<c:when test="${ noti.notiType == 1 }">
						<p class="notiContent notiText">님이 ${ loginUser.name }님을 팔로우하셨습니다</p>
					</c:when>
					
					<c:when test="${ noti.notiType == 2 }">
						<p class="notiContent notiText">님이 포스트를 좋아요 했어요</p>
					</c:when>
					
					<c:when test="${ noti.notiType == 3 }">
						<p class="notiContent notiText">님이 댓글을 다셨어요 : ${ noti.replyContent }</p>
					</c:when>
					
				</c:choose>
								
				<h2 class="notiDate notiText">${ noti.datePassed }</h2>
			</div>
			
			<c:if test="${ noti.notiType != 1 }">
				<img class="postImg" src="${ noti.postImg }">
			</c:if>
		</div>
	</div>
	</c:forEach>
	
</body>
</html>