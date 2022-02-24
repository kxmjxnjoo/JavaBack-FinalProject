<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 피드 QnA</title>
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>

	<c:if test="${ loginUser != null }">
		<div id="userQnaBox">
			<h1>${ loginUser.userid }님이 남긴 질문이에요</h1>
		</div>
	</c:if>
	
	<div id="allQnaBox">
		<h1>다른 분들이 남긴 질문이에요</h1>
	
		<c:forEach var="qdto" items="${ qnaList }">
			<h3>Q</h3>
			<h3>${ qdto.qna_subject }</h3>
			
			<h3>${ qdto.indate }</h3>
		</c:forEach> 
	</div>
		

	<%@ include file="/footer.jsp" %>
</body>
</html>