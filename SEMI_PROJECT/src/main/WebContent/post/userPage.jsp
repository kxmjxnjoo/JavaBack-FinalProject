<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ user.name }(${ user.userid })</title>
<link rel="stylesheet" href="css/userPage.css">
</head>
<body>

<%@ include file="/topnav/topnav.jsp" %>

<div id="userInfo">
	<div id="userImg">
		<img src="${ user.img }">
	</div>
	
	
	<div id="userDetail">
		<div id="userName">
			<h1>${ user.name }</h1>
			<c:choose>	
				<c:when test="${ isFollowing == 1 }">
					<input type="button" value="언팔로우" onclick="unfollow('${ user.userid }')">
				</c:when>
				<c:otherwise>
					<input type="button" value="팔로우" onclick="follow('${ user.userid }')">
				</c:otherwise>
			</c:choose>
			<input type="button" value="신고">
		</div>
		
		<div id="userStat">
			<h2>posts ${ user.postNum }</h2>
			<h2>followers ${ user.followerNum }</h2>
			<h2>following ${ user.followingNum }</h2>
		</div>
		
		<div id="userContent">
			<p>${ user.introduce }</p>
		</div>
	</div>
</div>

<hr>

<div id="postList">
	<c:forEach var="post" items="${ posts }">
		<div class="userPost">
			<img src="${ post.postImg }">
			
			<div class="userPostInfo">
				<i class="material-icons">favorite</i>
				<h3>${ post.likes }</h3>
				
				<i class="material-icons">chat_bubble</i>
				<h3>0</h3>
			</div>
		</div>

	</c:forEach>
</div>

<script src="/js/follow.js"></script>

</body>
</html>