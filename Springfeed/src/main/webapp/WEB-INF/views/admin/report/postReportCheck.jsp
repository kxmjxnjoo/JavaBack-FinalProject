<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/spring.css" rel="stylesheet"> 
<link href="../css/posting.css" rel="stylesheet"> 
<link rel="stylesheet" href="/css/admin/admin.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

		<div class="detail_wrap">
			<!-- 포스팅한 이미지 표시 -->
			<div class="detail_img">
				<img src="../images/${PostDto.post_img}" width="860px"> 
			</div>
			<div class="contents">
				<!-- 글 작성자 프로필 -->
				<div id="user">
					<div id="userprofile" onclick="/userpage&userid=${PostDto.userid}'"> 
						<img class="userImg" width=50px height=50px src="../images/${ PostDto.user_img == null ? "tmpUserIcon.png" : PostDto.user_img }">
					</div>
					<b>${PostDto.userid}</b>  
					<!-- <div id="buttons" onClick="setting();">
								<span class="material-icons" > more_vert </span>
					</div> -->
				</div>
<!-- message test -->
				
				<!-- 작성한 글 내용 -->
				<div id="content"> 
					<div id="posting_wrap">
					<div id="userprofile" onclick="/userpage&userid=${PostDto.userid}'">
						<img class="userImg" width=50px height=50px src="../images/${ PostDto.user_img == null ? "tmpUserIcon.png" : PostDto.user_img }">
						
						<label>${PostDto.userid}</label>
					</div>
					<div id="text_content"> <label>${PostDto.userid} </label> 
					${PostDto.content}
						<c:choose>
							<c:when test="${empty PostDto.address}"> </c:when>
							<c:otherwise>
							<div id="post_address">
								<span class="material-icons"> location_on </span>
								${PostDto.address}
							</div>
							</c:otherwise>
						</c:choose>
					<br>
					<div id="date"><fmt:formatDate value="${PostDto.create_date}"/></div>
					</div>
					</div>
				</div>
				
			<%-- 	<!-- 댓글 표시 구역 -->
				<div class="reply">
					<c:forEach items="${ReplyDto}" var="reply">
					<div id="reply_each">
						<img class="replyImg" src="../images/${ reply.img == null ? "tmpUserIcon.png" : reply.img }">
						<div id="text_content"><label>${reply.userid}</label> 
							${reply.content}
							<div id="date"><fmt:formatDate value="${reply.reply_date}"/></div>
						</div>
					</div>
					</c:forEach>
				</div> --%>
				</div><!-- contents end -->
			</div>

	
	<div id="handleReportCheck">
		<input type="button" value="신고 처리하기" onclick="handleReport(${post_num}, ${report_num});"> 
	</div>
	
<script type="text/javascript" language="javascript" defer="defer"> 
	
function handleReport(post_num, report_num){
	let result = confirm("신고 받은 게시물을 삭제하시겠습니까?");
	if(result) {
		location.href = "spring.do?command=handleReport&post_num="+post_num+"&report_num="+report_num;
	}
}

</script>
</body>
</html>