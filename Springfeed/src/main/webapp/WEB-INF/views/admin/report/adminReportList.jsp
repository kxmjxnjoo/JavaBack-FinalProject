<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/common/admin_submenu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../../css/admin/admin.css">
	
<h1>신고 회원 리스트<!-- <img src="images/Report.png"> --></h1>
<span id="info">${loginAdmin.NAME}(${loginAdmin.ADMINID})님 로그인
	<button type="button" class="btn btn-outline-secondary" onclick="location.href/logoutAdmin'">로그아웃</button></span>
	<br><br><br>
	
<form name="frm" method="post">		
	
<!-- 정렬 선택 -->
		<div id="orderSelect">
<!-- 			<select name="reportOrder" id="reportOrder" class="select-css">
				<option value="0"> 정렬 </option>
				<option value="1"> 날짜 내림차순 </option>
				<option value="2"> 날짜 오름차순 </option>
				<option value="3"> 게시물 신고 </option>
				<option value="4"> 스토리 신고 </option>
				<option value="5"> 유저 신고 </option>
			</select>
			<input type="button" value="검색" onclick ="goOrder();"> -->
		</div>
			<table class="table table-hover">
			<tr>
				<th>번호</th><th>아이디</th><th>신고 유형</th><th>사유</th><th>신고자</th><th>신고날짜</th><th>처리</th>
			</tr>
			<c:forEach items="${rdto}" var="rdto">
				<tr align="left">
					<td  width="50">${rdto.REPORT_NUM}</td>
					<td  width="50">${rdto.REPORTED_ID}</td>
					<td  width="50">${rdto.REPORT_TYPE}</td>
					<td  width="200"><a href="">${rdto.REASON}</a></td>
					<td  width="50">${rdto.REPORTER_ID}</td>
					<td  width="70"><fmt:formatDate value="${rdto.INDATE}"/></td>
					<c:choose>
						<c:when test="${rdto.HANDLED.equals('y')}">
							<td  width="50"> <input type="button" disabled='disabled' name="banDone" value="처리 완료">					
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${rdto.REPORT_TYPE.equals('post')}">
									<td  width="50">
									<button type="button" class="btn btn-outline-danger" onclick="postReportCheck('${rdto.POST_NUM}', '${rdto.REPORT_NUM}');">처리</button> 
								</c:when>
								<c:when test="${rdto.REPORT_TYPE.equals('story')}">
									<td  width="50"> 
									<button type="button" class="btn btn-outline-danger" onclick="storyReportCheck('${rdto.STORY_NUM}', '${rdto.REPORT_NUM}');">처리</button> 
								</c:when>
								<c:otherwise>
									<td  width="50">
									<button type="button" class="btn btn-outline-danger" onclick="userReportCheck('${rdto.REPORTED_ID}' , '${rdto.REPORT_NUM}');">처리</button> 
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
	</table>
</form>
<%@ include file="../../admin/common/paging.jsp"%>
<%@ include file="../../admin/common/footer.jsp"%>

<script type="text/javascript" language="javascript" defer="defer"> 

function postReportCheck(post_num, report_num){
	document.frm.action = "/admin/report/post";
	
	// dom에 INPUT.. CREATE
	var input_post = document.createElement('input'); 
	input_post.setAttribute("type", "hidden");
	input_post.setAttribute("name", "post_num");
	input_post.setAttribute("value", post_num);
	
	var input_report = document.createElement('input'); 
	input_report.setAttribute("type", "hidden");
	input_report.setAttribute("name", "report_num");
	input_report.setAttribute("value", report_num);
	
	document.frm.appendChild(input_post);
	document.frm.appendChild(input_report);
	
	document.frm.submit();
}

function storyReportCheck(story_num, report_num){
	document.frm.action = "/admin/report/story";
	
	// dom에 INPUT.. CREATE
	var input_story = document.createElement('input'); 
	input_story.setAttribute("type", "hidden");
	input_story.setAttribute("name", "story_num");
	input_story.setAttribute("value", story_num);
	
	var input_report = document.createElement('input'); 
	input_report.setAttribute("type", "hidden");
	input_report.setAttribute("name", "report_num");
	input_report.setAttribute("value", report_num);
	
	document.frm.appendChild(input_story);
	document.frm.appendChild(input_report);
	
	document.frm.submit();
}

	function userReportCheck(reported_id, report_num){
	document.frm.action = "/admin/report/user	";
	
	// dom에 INPUT.. CREATE
 	var input_reported_id = document.createElement('input'); 
 	input_reported_id.setAttribute("type", "hidden");
 	input_reported_id.setAttribute("name", "reported_id");
 	input_reported_id.setAttribute("value", reported_id);

	var input_report = document.createElement('input'); 
	input_report.setAttribute("type", "hidden");
	input_report.setAttribute("name", "report_num");
	input_report.setAttribute("value", report_num);
	
	document.frm.appendChild(input_reported_id);
	document.frm.appendChild(input_report);
	
	document.frm.submit();
} 

</script>