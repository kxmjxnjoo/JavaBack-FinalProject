<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
<link rel="stylesheet" href="/css/search.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="resultBoxWrapper">
		<c:forEach var="result" items="${ results }">
			<div class="resultBox">
			
				<div class="resultBoxInfo" onclick="location.href='spring.do?command=userpage&userid=${ result.userid }'">
					<img src="/images/${ result.img == null ? "tmpUserIcon.png" : result.img }">
					
					<div class="userText">
						<h1>${ result.userid }</h1>
						<h1>${ result.name }</h1>
					</div>
				</div>
				
				<c:if test="${not empty loginUser}">
					<c:choose>
						<c:when test="${ result.isFollowing == 1 }">
							<input type="button" value="언팔로우" onclick="unfollow('${ result.userid }')">
						</c:when>
						
						<c:otherwise>
							<input type="button" value="팔로우" onclick="follow('${ result.userid }')">
						</c:otherwise>
					
					</c:choose>
				</c:if>
			</div>
		</c:forEach>
	</div>
	
		<%@ include file="/footer.jsp" %>
	
	
	<script src="/js/follow.js"></script>
</body>
</html>