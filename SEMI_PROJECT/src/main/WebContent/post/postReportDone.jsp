<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 완료</title>
<link href="/css/spring.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div id="reportDoneWrap">
	<h2>신고가 완료되었습니다!</h2>
	<label id="input-reset-button" for="input-submit" class="inputButton" onclick="postClose();"> 닫기 </label>
	<input type="button" value="닫기" id="input-submit" >
</div>

<%-- 
<c:choose>

		<c:when test="${not empty post_num}">
			<div id="wrap" onclick="postClose(${post_num});">
				${message} <br>
				${post_num}
				<label id="input-reset-button" for="input-submit" > 닫기 </label>
				<input type="button" value="닫기" id="input-submit" >
			<div id="clear"></div>
		</div>
		</c:when>
		<c:when test="${not empty story_num}">
			<div id="wrap" onclick="storyClose(${story_num});">
				${message} <br>
				${story_num}
				<label id="input-reset-button" for="input-submit" > 닫기 </label>
				<input type="button" value="닫기" id="input-submit" >
				<div id="clear"></div>
			</div>
		</c:when>
		<c:when test="${not empty reported}">
			<div id="wrap" onclick="userClose(${reported});">
				${message} <br>
				${reported}
				<label id="input-reset-button" for="input-submit" > 닫기 </label>
				<input type="button" value="닫기" id="input-submit" >
				<div id="clear"></div>
			</div>
	</c:when>
</c:choose> --%>
<script type="text/javascript" language="javascript" defer="defer">

function postClose(){
/* 	opener.location.href='spring.do?command=postDetail&post_num='+post_num; */
	console.log(111111);
	self.close();
}
/* 
function storyClose(story_num){
	opener.location.href='spring.do?command=postDetail&story_num='+story_num;
	console.log(111111);
	self.close();
}

function userClose(userid){
	opener.location.href='spring.do?command=postDetail&post_num='+userid;
	console.log(111111);
	opner.opener.location.href='spring.do?command=postDetail&post_num=' + userid;
	self.close();
} */


</script>
</body>
</html>