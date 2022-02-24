<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="admin_submenu.jsp" %>
<link rel="stylesheet" href="admin/css/admin.css">

<h1>신고 회원 리스트<!-- <img src="images/Report.png"> --></h1>
<span id="info">${adminLogin.adminid}님 로그인
	<input type="button" id="logout" value="로그아웃" onClick="location.href='spring.do?command=logout'"></span>
	<br><br><br>
	
<form name="frm" method="post">		
	<%--<%-- c:when test="${Report.useyn.size()==0}">
		<h4 style ="color: red; text-align:center;">처리할 사항이 없습니다</h4>
	</c:when> --%>
	
<!-- 정렬 선택 -->
		<select name="reportOrder" id="reportOrder" class="select-css">
			<option value = "" > 정렬 </option>
			<option value = "1" > 날짜 내림차순 </option>
			<option value = "2" > 날짜 오름차순 </option>
			<option value = "3" > 게시물 신고 </option>
			<option value = "4" > 스토리 신고 </option>
			<option value = "5" > 유저 신고 </option>
		</select>
		<input type="button" value="검색" onclick ="goOrder();">
		<table>
			<tr>
				<th>번호</th><th>아이디</th><th>신고 유형</th><th>사유</th><th>신고자</th><th>신고날짜</th><th>처리</th>
			</tr>
			<c:forEach items="${reportList}" var="rdto">
				<tr align="left">
					<%-- <td  align="left">&nbsp;&nbsp;
						<a href="spring.do?command=reportList=${reportList.num}">
						</a>
					</td> --%>
					<td  width="50">${rdto.report_num}</td>
					<td  width="50">${rdto.reported_id}</td>
					<td  width="50">${rdto.type}</td>
					<td  width="200">${rdto.reason}</td>
					<td  width="50">${rdto.reporter_id}</td>
					<td  width="70"><fmt:formatDate value="${rdto.indate}"/></td>
					<c:choose>
						<c:when test="${rdto.type.equals('post')}">
							<td  width="50"> <input type="button" name="banCheck" value="처리">
						</c:when>
						<c:when test="${rdto.type.equals('story')}">
							<td  width="50"> <input type="button" name="banCheck" value="처리">
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					

				</tr>
			</c:forEach>
	</table>
</form>
<form>
	<input type="submit" id="ban" value="정지 처리"  onClick="location.href='spring.do?command=reportUserCheck'">
</form>
<!-- <input type="submit" id="ban" value="정지 처리" onClick="location.href='spring.do?command=adminMain'"> -->
<br /><br />
	<div id="paging" align="center" style="font-size:110%; font-weight:bold;">
	<c:url var="action" value="spring.do?command=report" />
	
	<c:if test="${paging.prev}">
		<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;.
		
	</c:if>
	
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
	</c:if>
</div>

<script type="text/javascript" language="javascript" defer="defer"> 

function goOrder(){
	document.frm.action = "spring.do?command=reportOrder";
	document.frm.submit();
}

</script>
<%@ include file="/admin/footer.jsp"%>