<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="/css/admin/admin.css">

<h1>Q&amp;A리스트</h1>
<span id="info">${loginAdmin.NAME}(${loginAdmin.ADMINID})님 로그인
	<button type="button" class="btn btn-outline-secondary" onclick="location.href/logoutAdmin'">로그아웃</button></span>
	<br><br><br>
		 
	<table class="table table-hover">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>제목</th>
			<th>작성일</th>
			<th>답변</th>
		<tr>
		
		<c:forEach items="${qdto}" var="qdto">
			<tr onclick="/qnaview&num=${ qdto.QNA_NUM }" class="qnaItems">
				<td>${qdto.QNA_NUM}</td>
				<td>${qdto.QNA_ID}</td>
				<td><a href=" '/qna/detail + ${qdto.QNA_NUM}'">${qdto.QNA_SUBJECT}</a></td>
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
<%@ include file="../../admin/common/paging.jsp"%>
<%@ include file="../../admin/common/footer.jsp"%>