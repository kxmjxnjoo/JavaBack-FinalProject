<!-- 메인페이지와 유저페이지에 include로 삽입할 페이지입니다.-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/spring.css" rel="stylesheet"> 
<script src="script/spring.js"></script>
</head>
<body>
<form>
<!-- 추후 c:forEach로 수정 -->
	<div id=story_wrap>
		<div class="slot">  
			<div class="story_img">
			<img src="../images/2.jpg" height=100px width=100px>
			</div>
			<h3>userid</h3>
		</div>
		<div class="slot">  
			<div class="story_img">
			<img src="../images/2.jpg" height=100px width=100px>
			</div>
			<h3>userid</h3>
		</div>
	</div>
</form>
</body>
</html>