<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탐색하기</title>
<link href="../css/spring.css" rel="stylesheet"> 
<link href="../css/explore.css" rel="stylesheet"> 
</head>
<body>
	<div id="exploreWrap">
		<%@ include file="/topnav/topnav.jsp" %>
		<div id ="highClear"></div>
		<div id="postList">
			<c:forEach var="post" items="${posts}">
				<div class="userPost" onclick="location.href='spring.do?command=postDetail&post_num=${post.postNum}'">
					<img src="../images/${post.post_img}" height= "300px">
	<!-- hover -->
					<div class="userPostInfo">
						<i class="material-icons">favorite</i> <h3>${post.likeCount}</h3>
						<i class="material-icons">chat_bubble</i> <h3>${post.replyCount}</h3>
					</div>
					<div class ="userPostInfoLayer"></div>
				</div>
			</c:forEach>
		</div>
		<div id ="highClear"></div>
		<%@ include file="/footer.jsp" %>
	</div>
	<footer>
	</footer>
</body>
</html>