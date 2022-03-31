<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="../resources/static/css/admin/admin.css">

<h1>Q&amp;A리스트</h1>
<span id="info">${adminLogin.name}(${adminLogin.adminid})님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="/logout'"></span>
	<br><br><br>
		 
	<table>
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>제목</th>
			<th>작성일</th>
			<th>답변여부</th>
		<tr>
		
		<c:forEach items="${qnaList}" var="qdto">
			<tr onclick="/qnaview&num=${ qdto.qna_num }'" class="qnaItems">
				<td width="50">${qdto.username}</td>
				<td width="50">${qdto.qna_id}</td>
				<td width="50">${qdto.qna_subject}</td>
				<td width="50"><fmt:formatDate value="${qdto.indate}"/></td>
				<td width="20">
					<c:choose>
						<c:when test='${ qdto.rep.equals("y") }'>
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
			<!-- 
				<c:forEach items="${memberList}" var="MemberDto">
					<tr><td height="23" align="center" >${memberList.adminid}</td>
						<td style="text-align:left; padding-left:50px;">
							<a href="#" onClick="go_detail('${memberList.adminid}')"></a></td>
						<td><fmt:formatDate value="${memberList.indate}"/></td>
						<td><c:choose>
			      				<c:when test='${memberList.useyn=="n"}'>미사용</c:when>
			   	 				<c:otherwise>사용</c:otherwise> 
							</c:choose></td></tr>
				</c:forEach>
			 -->	
	
	<!-- 페이지 수 -->
	<br /><br />
		<div id="paging" align="center" style="font-size:110%; font-weight:bold;">
			<c:url var="action" value="spring.do?command=adminMemberList" />
			<c:if test="${paging.prev}">
				<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
			</c:if>
			
			<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
				<c:choose>
					<c:when test="${paging.page==index}">
						<span style="color:red">${index}&nbsp;</span>
					</c:when>
					<c:otherwise>
						<a href="${action}&page=${index}&key=${key}">${index}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${paging.next}">
				<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
			</c:if>
		</div>

<%@ include file="../admin/common/footer.jsp"%>
