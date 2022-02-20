<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="admin_submenu.jsp" %>
<link rel="stylesheet" href="css/admin.css">

<h1>신고 회원리스트<!-- <img src="images/Report.png"> --></h1>
<span id="info">${adminLogin.name}(${adminLogin.adminid})님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="location.href='spring.do?command=logout'"></span>
	<br><br><br>
		
<form name="frm" method="post">		
	<%--<%-- c:when test="${Report.useyn.size()==0}">
		<h4 style ="color: red; text-align:center;">처리할 사항이 없습니다</h4>
	</c:when> --%>
		<table>
			<tr><th>번호</th><th>아이디</th><th>사유</th><th>신고자</th><th>신고날짜</th><th>처리</th></tr>
			<c:forEach items="${reportList}" var="rdto">
				<tr align="left">
					<td  align="left">&nbsp;&nbsp;
						<a href="spring.do?command=reportList=${reportList.num}">
						</a>
					</td>
					<td  width="50">${rdto.repote_num}</td>
					<td  width="100">${rdto.repoted_id}</td>
					<td  width="200">${rdto.reason}</td>
					<td  width="100">${rdto.repoter_id}</td>
					<td  width="100">${rdto.indate}</td>
					<td  width="100">${rdto.useyn}
					<td><input type="checkbox" name="banCheck" value="${member.useyn}"></td>
				</tr>
			</c:forEach>
	</table>
</form>
<form>
	<input type="submit" id="ban" value="정지 처리"  onClick="location.href='spring.do?command=reportUserCheck'">
</form>
<!-- <input type="submit" id="ban" value="정지 처리" onClick="location.href='spring.do?command=adminMain'"> -->
`
	<!-- 페이지 수 -->
<br /><br />
	<div id="paging" align="center" style="font-size:110%; font-weight:bold;">
	<!-- 페이지가 클릭될때마다 이동할 링크 기본경로를 변수에 저장 -->
	<c:url var="action" value="spring.do?command=report" />
	
	<c:if test="${paging.prev}">
		<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;.
		
		<!-- 링크되는 주소 -> board.do?command=main&page=?? -->
		<!-- 맨 왼쪽 페이지(beginPage 보다 1페이지 더 작은 페이지로 이동 -->
	</c:if>
	
	<!-- 실제 페이지들의 표시 - 반복문사용(beginPage 부터 endPage 까지) -->
	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
		<c:choose>
			<c:when test="${paging.page==index}">
				<span style="color:red">${index}&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="${action}&page=${index}">${index}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${paging.next}">
		<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
		<!-- 맨 왼쪽 페이지(endPage 보다 1페이지 더 큰 페이지로 이동 -->
	</c:if>
</div>
<%@ include file="/admin/footer.jsp"%>