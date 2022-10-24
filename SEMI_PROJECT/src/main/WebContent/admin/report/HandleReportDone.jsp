<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 처리 완료</title>
<link href="../css/spring.css" rel="stylesheet"> 
<link href="../css/report.css" rel="stylesheet"> 
<link rel="stylesheet" href="admin/css/admin.css">
</head>
<body>
<div id="reportDoneWrap">
	<h2>신고가 완료되었습니다!</h2>
	<label id="input-reset-button" for="input-submit" class="inputButton" onclick="Reportclose();"> 닫기 </label>
	<input type="button" value="닫기" id="input-submit" >
</div>
<script type="text/javascript" language="javascript" defer="defer">
function Reportclose(){
	self.close();
}
</script>
</body>
</html>