<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기0</title>
<link rel="stylesheet" href="/css/findidpwd.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="findidpwdBox">
	
		<div id="findidBox">
			<div id="findidBoxContent">
				<form action="spring.do" method="post">
					<h1>아이디 찾기</h1>
				
					<input type="hidden" value="findid">
					
					<input type="text" name="name" placeholder="이름">
					<input type="text" name="phone" placeholder="전화번호">
					
					<input type="submit">
				</form>
			</div>
		</div>
	
		<div id="findpwdBox">
			<div id="findpwdBoxContent">
				<form action="spring.do" method="post">
					<h1>비밀번호 찾기</h1>
					
					<input type="hidden" value="findpwd">
					
					<input type="text" name="userid" placeholder="아이디">
					<input type="text" name="name" placeholder="이름">
					<input type="text" name="phone" placeholder="전화번호">
					<input type="button" onclick="" value="인증번호 받기">
					<input type="text" name="certnum" placeholder="인증번호">
					
					<input type="submit"> 
				</form>
			</div>
		</div>
		
	</div>
	
</body>
</html>