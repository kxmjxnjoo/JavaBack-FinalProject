<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="/css/admin/admin.css">

<h1>Q&amp;A리스트</h1>
<span id="info">${loginAdmin.NAME}(${loginAdmin.ADMINID})님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="/logout'"></span>
	<br><br><br>
		 
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>답변</th>
		<tr>
		
		<c:forEach items="${qdto}" var="qdto">
			<tr onclick="/qnaview&num=${ qdto.QNA_NUM }" class="qnaItems">
				<td>${qdto.QNA_NUM}</td>
				<td>${qdto.QNA_SUBJECT}</td>
				<td><fmt:formatDate value="${qdto.INDATE}"/></td>
				<td>
					<c:choose>
						<c:when test='${ qdto.REP.equals("y") }'>
							<i class="material-icons" style="color: green;">check_circle</i>
						</c:when>
						<c:otherwise>
							<i class="material-icons" style="color: red;">cancel</i>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../../admin/common/footer.jsp"%>