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
<div id="wrap" onclick="close(${PostDto.post_num});">
		${message} <br>
		${post_num}
		<label id="input-reset-button" for="input-submit" > 닫기 </label>
		<input type="button" value="닫기" id="input-submit" >
		<div id="clear"></div>
</div>

<script type="text/javascript" language="javascript" defer="defer">
function close(post_num){
	opener.location.href='spring.do?command=postDetail&post_num='+post_num;
	console.log(111111);
	self.close();
}
</script>
</body>
</html>