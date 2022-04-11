<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 신고하기</title>
<link href="css/spring.css" rel="stylesheet"> 
<link href="/css/report.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript">
function do_report(reportedUserid){
	document.reportFrm.action="/report?userid=" + reportedUserid;
	document.reportFrm.submit();
}
</script>
</head>
<body>
<form name="reportFrm" method="post">
	<div id="wrap">
		<div class="user_report_wrap"> 
			<div class="reportTitle"> <h3>신고</h3> </div>
			<div class="reportContentTitle"> <b>이 사용자를 신고하는 이유</b> </div>
			<div class="reportContent">
				<label><input type="radio" name="reportReson" class="checkbox-style" value="1" checked/> 적합하지 않은 콘텐츠 게시 </label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="2"/> 타인을 사칭하는 계정 </label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="3"/> 만 14세 미만 계정 </label>
			</div>
			<input type="button" id="input-submit" onclick="do_report('${reported}');">
			<label id="input-reset-button" class="inputButton" for="input-submit" > 신고하기 </label>
	</div>
	</div>
</form>
</body>
</html>