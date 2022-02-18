<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링피드</title>
<link rel="stylesheet" href="/info/noFollower.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="noFollowerBox">
		<h1 id="noFollowerMessage">${ loginUser.name }님, 팔로우해서 스프링 피드를 시작하세요</h1>
		
		<input type="button" value="지금 팔로우 하러 가기" id="followSuggestButton" onclick="location.href='spring.do?command=search'">
	</div>
	
</body>
</html>