<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 신고 처리</title>
<link rel="stylesheet" href="../css/userPage.css">
<link rel="stylesheet" href="admin/css/admin.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div id="userInfo">
	<div id="userImg">
		<img src="images/${ user.img }">
	</div>
	
	
	<div id="userDetail">
		<input type="button" value="신고 처리 하기" onclick="handleReport('${user.userid}',${report_num});"> 
		<input type="button" value="유저페이지로 가기" onclick="location.href='spring.do?command=userpage&userid=${user.userid}'">
		<div id="userName">
			<h1>${ user.name }</h1>
		
		
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
		<div id="postList" style="margin-bottom:50px;">
			<c:forEach var="post" items="${ posts }">
				<div class="userPost" style="width:26%;"onclick="location.href='spring.do?command=postDetail&post_num=${ post.postNum }'">
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
	<c:otherwise>
		<div id="postList">
			<h1>${ user.userid }님은 아직 포스트를 올리지 않으셨어요.</h1>
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript" language="javascript" defer="defer"> 
	
function handleReport(userid, report_num){
	let result = confirm("신고 받은 유저를 차단하시겠습니까?");
	if(result) {
		location.href = "spring.do?command=handleReport&userid="+userid+"&report_num="+report_num;
	}
}

</script>
</body>
</html>