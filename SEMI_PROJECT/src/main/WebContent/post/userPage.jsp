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
		<img src="images/${ user.img }">
	</div>
	
	
	<div id="userDetail">
		<div id="userName">
			<h1>${ user.name }</h1>
			<c:choose>	
				<c:when test="${ loginUser.userid == user.userid }">
					<input type="button" value="프로필 수정" onclick="location.href='spring.do?command=editprofileform'"> 
					<input type="button" value="로그아웃" onclick="location.href='spring.do?command=logout'">
				</c:when>
				<c:when test="${ isFollowing == 1 }">
					<input type="button" value="언팔로우" onclick="unfollow('${ user.userid }')">
					<input type="button" onclick="userReport('${user.userid}');" value="신고">
				</c:when>
				<c:otherwise>
					<input type="button" value="팔로우" onclick="follow('${ user.userid }')">
					<input type="button" onclick="userReport('${user.userid}');" value="신고">
				</c:otherwise>
			</c:choose>
		</div>
		
		<div id="userStat">
			<h2>posts ${ user.postNum }</h2>
			<h2>followers ${ user.followerNum - 1}</h2>
			<h2>following ${ user.followingNum - 1 }</h2>
		</div>
		
		<div id="userContent">
			<p>${ user.introduce }</p>
		</div>
	</div>
</div>

<hr>

<c:choose>
	<c:when test="${ posts != null }">
		<div id="postList">
			<c:forEach var="post" items="${ posts }">
				<div class="userPost" onclick="location.href='spring.do?command=postDetail&post_num=${ post.postNum }'">
					<img src="../images/${ post.postImg }">
					
					<div class="userPostInfo">
						<i class="material-icons">favorite</i>
						<h3>${ post.likeCount }</h3>
						
						<i class="material-icons">chat_bubble</i>
						<h3>${ post.replyCount }</h3>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	
	<c:when test="${ loginUser.userid == user.userid }">
		<div id="postList">
			<h1>${ loginUser.name }님! 아직 포스트를 올리지 않으셨어요. 어서 올려보세요!</h1>
		</div>
	</c:when>
	
	<c:otherwise>
		<div id="postList">
			<h1>${ user.userid }님은 아직 포스트를 올리지 않으셨어요.</h1>
		</div>
	</c:otherwise>
</c:choose>

<script src="/js/follow.js"></script>
<script type="text/javascript">
	function userReport(userid){
	var url="spring.do?command=reportForm&userid=" + userid;
	var _width = '400';
	var _height = '300';
	var _left = Math.ceil((window.screen.width - _width)/2); 
	var _top = Math.ceil((window.screen.width - _height)/2); 
	var opt = "toolbar=no, menubar=no, resizable=no, fullscreen=yes, location=no, " +
		"width="+_width+", height="+_height+", left="+_left;
	window.open(url, "reportPost", opt);
}
</script>
</body>
</html>