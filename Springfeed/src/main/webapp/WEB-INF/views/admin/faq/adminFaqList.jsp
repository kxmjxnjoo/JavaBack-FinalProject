<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/admin_submenu.jsp" %>
<link rel="stylesheet" href="admin/css/admin.css">
<link rel="stylesheet" href="/admin/css/faq.css">

<h1>FAQ 리스트<!-- <img src="images/Report.png"> --></h1>
<span id="info">
<input type="button" id="button1" value="작성" onClick="/faq/add/form'">
${adminLogin.adminid}님 로그인
<input type="button" id="button2" value="로그아웃" onClick="/logout'"></span>
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
				<tr align="left" class="faqBox" onclick="/faqdetail&num=${ fdto.faq_num }">
					<td style="width: 10%;">${fdto.faq_num}</td>
					<td style="width: 30%;">${fdto.faq_subject}</td>
					<td style="width: 40%;">${fdto.faq_content}</td>
					<td style="width: 10%;"><button onclick="/faq/edit/form&num=${ fdto.faq_num }'" id="faqEditButton">수정</button></td>
					<td style="width: 10%;"><button onclick="/faq/delete(${ fdto.faq_num })" id="faqDeleteButton">삭제</button></td>
				</tr>
			</c:forEach>
			
	</table>
</form>
<!-- <input type="submit" id="ban" value="정지 처리" onClick="/adminMain'"> -->

	<!-- 페이지 수 -->
<%@ include file="/common/paging" %>

<script type="text/javascript">
	function deleteFaq(num) {
		if(confirm(num + "번 FAQ를 지울까요?")) {
			location.href = "spring.do?command=deletefaq&num=" + num
		}
	} 
</script>

<%@ include file="/admin/common/footer.jsp"%>
