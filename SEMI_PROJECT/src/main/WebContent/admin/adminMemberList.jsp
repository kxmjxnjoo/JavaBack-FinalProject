<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="admin_submenu.jsp" %>
<link rel="stylesheet" href="css/admin.css">
<script  type="text/javascript">
function go_search( ){
	if( document.frm.key.value == "" ){
		alert("검색어를 입력하세요");
	 	return;
	 }
	var url = "spring.do?command=key&page=1";
	document.frm.action = url;
	document.frm.submit();
}

</script>

<h1>회원리스트</h1>
<span id="info">${adminLogin.name}(${adminLogin.adminid})님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="location.href='spring.do?command=logout'"></span>
	<br><br><br>
		 
<form name="frm" action="spring.do">		
<input type="text" id="member_search" name="key" value="${key}">
				<input id="search_btn" type="button" name="search" value="회원 검색" onClick="go_search();">
	<table>
		<tr><th>이름</th><td width="50">${memberdto.name}</td>
				<th>아이디</th><td width="50">${mdto.userid}</td>
				<th>이메일</th><td width="50">${mdto.email}</td>
				<th>핸드폰</th><td width="50">${mdto.phone}</td>
				<th>사용계정</th><td width="50">${mdto.useyn}</td>
				<th>가입일</th><td width="50"><fmt:formatDate value="${mdto.indate}"/></td>
		<c:forEach items="${memberList}" var="memberDto"></c:forEach>
	</table>
</form>
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

<%@ include file="/admin/footer.jsp"%>