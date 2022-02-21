<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포스트 자세히 보기</title>
<link href="/css/spring.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="/js/follow.js"></script>
<script type="text/javascript" defer="defer">

function add_reply(post_num){
	if(document.frm.reply_content == "" ) {
		document.frm.reply_content.focus();
		alert("댓글 내용을 작성해 주세요");
		return;
	}
	var url = "spring.do?command=addReply&post_num="+post_num;
	document.frm.action=url;
	document.frm.submit();
}

function focus_reply(){
	document.frm.reply_content.focus();
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) 
	field.value = field.value.substring(0, maxlimit);
	else 
	countfield.value = maxlimit - field.value.length;
}

function post_like(post_num){
	var url = "spring.do?command=checkLike&post_num=" + post_num;
	document.frm.action=url;
	document.frm.submit();
}


function reply_like(reply_num, post_num){
	var url = "spring.do?command=checkReplyLike&reply_num="+reply_num+"&post_num="+post_num;
	document.frm.action=url;
	document.frm.submit();
}

function deleteCheck(post_num){
	var answer = confirm("포스트를 삭제할까요?")
	if(answer){
		location.href="spring.do?command=deletePost&post_num="+post_num;
	}
}

function setting(){
	document.getElementById("setting").style.display="flex";
}

function setting_close(){
	document.getElementById("setting").style.display="none";
}

function goReport(post_num){
	var url="spring.do?command=reportForm&post_num=" + post_num;
	var _width = '400';
	var _height = '500';
	var _left = Math.ceil((window.screen.width - _width)/2); 
	var _top = Math.ceil((window.screen.width - _height)/2); 
	var opt = "toolbar=no, menubar=no, resizable=no, fullscreen=yes, location=no, " +
		"width="+_width+", height="+_height+", left="+_left;
	window.open(url, "reportPost", opt);
}

function deleteReplyCheck(reply_num, post_num){
	var answer = confirm("덧글을 삭제할까요?")
	if(answer){
		location.href="spring.do?command=deleteReply&reply_num="+reply_num+"&post_num="+post_num;
	}
}

</script>
</head>
<body>

<form name="frm" method="post">
<%-- <%@ include file="/topnav/topnav.jsp" %> --%>
<!-- report modal -->
	<div id="setting">
	<c:choose>
		<c:when test="${PostDto.userid == loginUser.userid}">  <!-- ==으로 바꾸기 -->
				<div id="setting_menu">
					<div class="setting_btn"><a href='spring.do?command=editPostForm&post_num=${post_num}'> 수정 </a></div>
					<div class="setting_btn" onClick="deleteCheck(${post_num});"> <a href="#"> 삭제 </a></div>
					<div class="setting_btn" onClick="setting_close();">닫기</div>
					<div class="setting_layer"></div>
				</div>
		</c:when>
		<c:otherwise>
				<div id="setting_menu">
					<c:choose>
						<c:when test="${ isFollowing == 1 }">
							<div class="setting_btn" onclick="unfollow('${ PostDto.userid }')"> 언팔로우</div>
						</c:when>
						<c:otherwise>
							<div class="setting_btn" onclick="follow('${ PostDto.userid }')">팔로우</div>
						</c:otherwise>
					</c:choose>
					<div class="setting_btn"><a href='#' onClick="goReport(${post_num});">신고</a></div>
					<div class="setting_btn" onclick="setting_close();">닫기</div>
					<div class="setting_layer"></div>
				</div>
		</c:otherwise>
	</c:choose>
	</div>

<!-- setting modal -->
	<div id="setting">
	<c:choose>
		<c:when test="${PostDto.userid == loginUser.userid}">  <!-- ==으로 바꾸기 -->
			
				<div id="setting_menu">
					<div class="setting_btn"><a href='spring.do?command=editPostForm&post_num=${post_num}'> 수정 </a></div>
					<div class="setting_btn" onClick="deleteCheck(${post_num});"> <a href="#"> 삭제 </a></div>
					<div class="setting_btn" onClick="setting_close();">닫기</div>
					<div class="setting_layer"></div>
				</div>
		</c:when>
		<c:otherwise>
				<div id="setting_menu">
					<div class="setting_btn"><a href='#'>팔로우</a></div> <!-- 팔로우/언팔로우 c:choose 처리 -->
					<div class="setting_btn"><a href='#' onClick="goReport(${post_num});">신고</a></div>
					<div class="setting_btn" onclick="setting_close();">닫기</div>
					<div class="setting_layer"></div>
				</div>
		</c:otherwise>
	</c:choose>
	</div>
	
	<div class="wrap">
		<div class="detail_wrap">
			<!-- 포스팅한 이미지 표시 -->
			<div class="detail_img">
				<img src="../images/${PostDto.post_img}" width="860px"> 
			</div>
			<div class="contents">
				<!-- 글 작성자 프로필 -->
				<div id="user">
					<div id="userprofile" onclick="location.href='spring.do?command=userpage&userid=${PostDto.userid}'"> 
						<img class="userImg" src="../images/${ PostDto.user_img == null ? "tmpUserIcon.png" : PostDto.user_img }">
					</div>
					<b>${PostDto.userid}</b>  <!-- 클릭 시 유저 프로필로 이동하도록 function 추가 -->
					<div id="buttons" onClick="setting();">
								<span class="material-icons" > more_vert </span>
					</div>
				</div>
<!-- message test -->
				${message}
				
				<!-- 작성한 글 내용 -->
				<div id="content"> 
					<div id="posting_wrap">
					<div id="userprofile" onclick="location.href='spring.do?command=userpage&userid=${PostDto.userid}'">
						<img class="userImg" src="../images/${ PostDto.user_img == null ? "tmpUserIcon.png" : PostDto.user_img }">
						<%-- <c:choose>
							<c:when test="${empty PostDto.user_img}"> 
								<img src="../images/noProfile.png" width="50px" height="50px">
							</c:when>
							<c:otherwise>
								<img src="../images/${PostDto.user_img}" width="50px" height="50px">
							</c:otherwise>
						</c:choose> --%>
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
				
				<!-- 댓글 표시 구역 -->
				<div class="reply">
					<c:forEach items="${ReplyDto}" var="reply">
						<div id="reply_each">
							<img class="replyImg" src="../images/${ reply.img == null ? "tmpUserIcon.png" : reply.img }">
							<%-- <c:choose>
								<c:when test="${empty reply.img}">
									<img src="../images/2.jpg" width="50px" height="50px" onclick="location.href='spring.do?command=userpage&userid=${reply.userid}'">
								</c:when>
								<c:otherwise>
									<img src="../images/${reply.img}" width="50px" height="50px" onclick="location.href='spring.do?command=userpage&userid=${reply.userid}'">
								</c:otherwise>
							</c:choose> --%>
							<div id="text_content"><label>${reply.userid}</label> 
								${reply.content}
								<div id="date"><fmt:formatDate value="${reply.reply_date}"/></div>
							</div>
							
							<c:choose>	
								<c:when test="${PostDto.userid == loginUser.userid}">
									<span id="replyLike${reply.reply_num}" class="material-icons" 
									onclick="reply_like(${reply.reply_num}, ${post_num});">${reply.replyFileName}</span>
									<span id="deleteReply" class="material-icons" 
									onclick="deleteReplyCheck(${reply.reply_num}, ${post_num});">
										clear</span>
								</c:when>
								<c:when test="${reply.userid == loginUser.userid}">
									<span id="deleteReply" class="material-icons" style="margin-left:20px"
									onclick="deleteReplyCheck(${reply.reply_num}, ${post_num});">clear</span>	
								</c:when>
								<c:otherwise>
									<span id="replyLike${reply.reply_num}" class="material-icons"  style="margin-left:20px" 
									onclick="reply_like(${reply.reply_num}, ${post_num});">${reply.replyFileName}</span>
								</c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
					</div>
					<div id="reaction">
						<img src="${fileName}" id="postLike" width="30px" onClick="post_like(${post_num});">
						<img src="../images/comment.png" width="30px" onClick="focus_reply();">
					</div>
					<div id="write_reply_wrap">
						<div id="write_reply"> 
							<!-- <textarea name="reply_content" id="reply_content" cols=30 rows=1 
							onKeyDown="textCounter(this.form.reply_content,this.form.remLen,100);" 
							onKeyUp="textCounter(this.form.reply_content,this.form.remLen,100);"
							placeholder="댓글 달기..."></textarea> -->
							<input id="reply_content" name="reply_content" type="text" placeholder="댓글 달기..." maxlength='100'>
							<span><img id="replySending" src="../images/send.png" width="30px" onclick="add_reply(${post_num});"></span>
						</div>
					</div>
				</div><!-- contents end -->
			</div>
	</div>
</form>
</body>
</html>