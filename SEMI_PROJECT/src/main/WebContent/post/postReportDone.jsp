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
<link href="/css/report.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div id="reportDoneWrap">
	<h2>신고가 완료되었습니다!</h2>
	<label id="input-reset-button" for="input-submit" class="inputButton" onclick="postClose();"> 닫기 </label>
	<input type="button" value="닫기" id="input-submit" >
</div>

<script type="text/javascript" language="javascript" defer="defer">

function postClose(){
	self.close();
}
</script>
</body>
</html>