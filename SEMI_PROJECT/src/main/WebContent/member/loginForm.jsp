<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 로그인</title>
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>

	<form>
		<input type="hidden" name="command" value="login">
		
		<input type="text" placeholder="아이디" name="userid">
		<input type="password" placeholder="비밀번호" name="pwd">
		
		<input type="submit" value="로그인">
		<input type="button" value="회원가입" onclick="location.href='spring.do?command=joinform'">
	</form>
</body>
</html>