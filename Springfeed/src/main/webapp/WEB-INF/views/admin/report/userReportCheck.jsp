<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 신고 처리</title>
<link rel="stylesheet" href="/css/userPage.css">
<link rel="stylesheet" href="/css/admin/admin.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div id="userInfo">
	<div id="userImg">
		<img src="images/${ user.IMG }">
	</div>
	
	
	<div id="userDetail">
		<input type="button" value="신고처리 하기" onclick="handleReport('${user.USERID}',${report_num});"> 
		<input type="button" value="유저페이지로 가기" onclick="/userpage&userid=${user.USERID}'">
		<div id="userName">
			<h1>${ user.NAME }</h1>
		
		
		<div id="userStat">
			<h2>posts ${ user.POSTNUM }</h2>
			<h2>followers ${ user.FOLLOWERNUM - 1}</h2>
			<h2>following ${ user.FOLLOWINGNUM - 1 }</h2>
		</div>
		
		<div id="userContent">
			<p>${ user.INTRODUCE }</p>
		</div>
	</div>
</div>

<hr>

<c:choose>
	<c:when test="${ posts != null }">
		<div id="postList" style="margin-bottom:50px;">
			<c:forEach var="post" items="${ posts }">
				<div class="userPost" style="width:26%;"onclick="/postDetail&post_num=${ post.POSTNUM }'">
					<img src="../images/${ post.POSTIMG }">
					
					<div class="userPostInfo">
						<i class="material-icons">favorite</i>
						<h3>${ post.LIKECOUNT }</h3>
						
						<i class="material-icons">chat_bubble</i>
						<h3>${ post.REPLYCOUNT }</h3>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<div id="postList">
			<h1>${ user.USERID }님은 아직 포스트를 올리지 않으셨어요.</h1>
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript" language="javascript" defer="defer"> 
	
function handleReport(userid, report_num){
	let result = confirm("신고 받은 유저를 차단하시겠습니까?");
	if(result) {
		location.href = "/handleReport&userid="+userid+"&report_num="+report_num;
	}
}

</script>
</body>
</html>