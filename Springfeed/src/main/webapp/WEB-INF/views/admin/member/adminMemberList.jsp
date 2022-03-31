<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="../resources/static/css/admin/admin.css">
<script  type="text/javascript">
function go_search(){
	if( document.frm.key.value == "" ){
		alert("검색어를 입력하세요");
	 	return false;
	 }
	return true
}

</script>

<h1>회원리스트</h1>
<span id="info">${adminLogin.name}(${adminLogin.adminid})님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="/logout'"></span>
	<br><br><br>
		 
<form name="frm" value="/admin/memberList">	
	<input type="hidden" >
	
	<input type="text" id="member_search" name="key" value="${key}">
	<input id="search_btn" type="submit" name="search" value="회원 검색" onClick="return go_search();">

</form>


	<table>
		<tr><th>이름</th><th>아이디</th><th>이메일</th><th>핸드폰</th><th>사용계정</th>
				<th>가입일</th></tr>
		<c:forEach items="${memberList}" var="mdto">
			<tr><td width="50">${mdto.name}</td> <td width="50">${mdto.userid}</td> <td width="50">${mdto.email}</td>
					<td width="50">${mdto.phone}</td> <td width="50">${mdto.useyn}</td> 
					<td width="50"><fmt:formatDate value="${mdto.indate}"/></td>
		</c:forEach>
	</table>

	<!-- 페이지 수 -->
	<br /><br />
		<div id="paging" align="center" style="font-size:110%; font-weight:bold;">
			<c:url var="action" value="/admin/memberList" />
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
