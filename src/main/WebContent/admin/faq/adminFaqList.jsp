<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="admin/css/admin.css">
<link rel="stylesheet" href="/admin/css/faq.css">

<h1>FAQ 리스트<!-- <img src="images/Report.png"> --></h1>
<span id="info">
<input type="button" id="button1" value="작성" onClick="location.href='spring.do?command=addfaqform'">
${adminLogin.adminid}님 로그인
<input type="button" id="button2" value="로그아웃" onClick="location.href='spring.do?command=logout'"></span>
	<br><br><br>
		
<form name="frm" method="post">		
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			
			
			<c:forEach items="${listFaq}" var="fdto">
				<tr align="left" class="faqBox" onclick="spring.do?command=faqdetail&num=${ fdto.faq_num }">
					<td style="width: 10%;">${fdto.faq_num}</td>
					<td style="width: 30%;">${fdto.faq_subject}</td>
					<td style="width: 40%;">${fdto.faq_content}</td>
					<td style="width: 10%;"><button onclick="location.href='spring.do?command=editfaqform&num=${ fdto.faq_num }'" id="faqEditButton">수정</button></td>
					<td style="width: 10%;"><button onclick="deleteFaq(${ fdto.faq_num })" id="faqDeleteButton">삭제</button></td>
				</tr>
			</c:forEach>
			
	</table>
</form>
<!-- <input type="submit" id="ban" value="정지 처리" onClick="location.href='spring.do?command=adminMain'"> -->

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

<script type="text/javascript">
	function deleteFaq(num) {
		if(confirm(num + "번 FAQ를 지울까요?")) {
			location.href = "spring.do?command=deletefaq&num=" + num
		}
	} 
</script>

<%@ include file="/admin/common/footer.jsp"%>
