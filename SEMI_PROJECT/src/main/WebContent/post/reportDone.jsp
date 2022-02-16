<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 완료</title>
<link href="css/spring.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript"> 
function close(){
	opener.location.href='spring.do?command=postDetail&post_num=' + ${PostDto.post_num};
	self.close();
}
</script>
</head>
<body>
<div id="setting">
<div id="setting_menu">
	<div> ${reason} 신고가 완료되었습니다!</div>
	<input type="button" value ="닫기" onclick="close();"> 
	<div class="setting_layer"></div>
</div>
</div>
</body>
</html>