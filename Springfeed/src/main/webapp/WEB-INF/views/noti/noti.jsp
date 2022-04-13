<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${ loginUser.NAME }님의 알림</title>
<link rel="stylesheet" href="/css/noti.css">
</head>
<body>
	<c:choose>
		<c:when test="${ not empty noNotification  }">
			<h1 id="notiNullMessage"> 아직 받은 알림이 없어요! </h1>
		</c:when>
		<c:otherwise>
			<c:forEach var="noti" items="${ notiList }">
			<div class="notiBox">
				<div class="notiBoxContent">
					<div class="userImg" style="background-image:url(/images/${ noti.IMG == null ? "tmpUserIcon.png" : noti.IMG })"
					onclick="location.href='/post?userid=${ noti.USERFRPM }'"> </div>
					
					<div class="notiTextBox">
						<h2 class="userName notiText">${ noti.NAME }</h2>
						
						<c:choose>
							<c:when test="${ noti.NOTITYPE == 1 }">
								<p class="notiContent notiText">님이 나를 팔로우합니다</p>
							</c:when>
							
							<c:when test="${ noti.NOTITYPE == 2 && loginUser.USERID != noti.USERFROM}">
								<p class="notiContent notiText">님이 포스트를 좋아요 했어요</p>
							</c:when>
						
							<c:when test="${ noti.NOTITYPE == 3 &&  loginUser.USERID != noti.USERFROM}">
								<p class="notiContent notiText">님이 포스트에 댓글을 다셨어요 : ${ noti.REPLYCONTENT }</p>
							</c:when>
							
							<c:when test="${ noti.NOTITYPE == 4 &&  loginUser.USERID != noti.USERFROM}">
								<p class="notiContent notiText">님이 내 댓글을 좋아요 했어요</p>
							</c:when>
							
							<c:when test="${ noti.NOTITYPE == 5 &&  loginUser.USERID != noti.USERFROM}">
								<p class="notiContent notiText">님이 스토리 를 좋아요 했어요</p>
							</c:when>
						</c:choose>
						
						<h2 class="notiDate notiText">
							<fmt:formatDate value="${ noti.CREATE_DATE }"/>
						</h2>
					</div>
					<%-- 
					<c:if test="${ noti.NOTITYPE != 1 && noti.NOTITYPE != 5 }">
						<img class="postImg" src="/images/${ noti.POSTIMG }">
					</c:if>
					<c:if test="${ noti.notiType == 5}">
						<img class="postImg" src="/images/${ noti.STORYIMG }">
					</c:if> --%>
				</div>
			</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	
	
</body>
		<%@ include file="../common/footer.jsp" %>


</html>