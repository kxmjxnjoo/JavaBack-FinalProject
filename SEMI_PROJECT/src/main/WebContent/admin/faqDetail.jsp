<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ include file="/topnav/topnav.jsp" %> --%>
<%--  <c:if test="${empty loginUser}">
	<jsp:forward page='spring.do?command=loginUser'/> loginUser? 로그인하는 페이지 이름 아직 모름
</c:if> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet" href="css/faqDetail.css">
<script src="script/script.js"></script>
</head>
<body>
<div id="wrap" align="center">
<h2></h2>
<form class="table">
<table>
	<tr><th width="80px">제목</th><td colspan="3">${fdto.faq_subject}</td><%-- <th width="60px">작성일</th><td width="250px"><fmt:formatDate value="${fdto.indate}"/></td> --%></tr>
	<tr><th height="100px">내용</th><td colspan="3"><pre>${fdto.faq_content}</pre></td></tr>
</table><br><br>
<input type="button" class="btn" value="목록으로" onClick="close();">
</form>
</div>

<script type="text/javascript">
function close(){
	
}
</script>