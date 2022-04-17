<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="/css/admin/admin.css">
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
<span id="info">${loginAdmin.NAME}(${loginAdmin.ADMINID})님 로그인
	<button type="button" class="btn btn-outline-secondary" onclick="location.href/logoutAdmin'">로그아웃</button></span>
	<br><br><br>
<form name="frm" action="/admin/memberList">
<input type="hidden" > 
<div class="row"> <div class="col-lg-6">	<input type="text" name="key" value="${key}" class="search">
	<input id="search_btn" class="search" type="submit" name="search" value="회원 검색" onClick="/admin/searchMember"></div></div>
	<table class="table table-hover" class="col-md-6" >
		<tr><th width="10%">이름</th>
				<th width="10%">아이디</th>
				<th width="10%">이메일</th>
				<th width="10%">핸드폰</th>
				<th width="10%">사용계정</th>
				<th width="10%">가입일</th></tr>
		<c:forEach items="${mdto}" var="mdto">
			<tr><td width="50">${mdto.NAME}</td> <td width="50">${mdto.USERID}</td> <td width="50">${mdto.EMAIL}</td>
					<td width="50">${mdto.PHONE}</td> <td width="50">${mdto.USEYN}</td> 
					<td width="50"><fmt:formatDate value="${mdto.INDATE}"/></td>
		</c:forEach>
	</table>
</form>
<%@ include file="../../admin/common/paging.jsp"%>
<%@ include file="../../admin/common/footer.jsp"%>