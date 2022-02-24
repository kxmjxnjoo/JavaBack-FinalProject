<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지</title>
<link rel="stylesheet" href="/css/message.css">
<link rel="stylesheet" href="/css/mainPopup.css">
</head>
<body>
	<%@ include file="/topnav/topnav.jsp" %>
	
	<div id="messageBox">
	
		<div id="userSelectBox">
		
			<div id="loginUserInfo">
				<h1>${ loginUser.userid }</h1>
				
				<i class="material-icons">add_box</i>
			</div>
			
			<div id="userBox">
				<c:forEach var="user" items="${ userList }">
					<div class="userSelect" onclick="location.href='spring.do?command=message&messagewith=${user.following}'">
						<img class="userImg" src="/images/${ user.followingImg }">
						
						<div class="userText">
							<h3>${ user.following }</h3>
							<h3>${ user.following }님과 대화해 보세요</h3>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
		<div id="userMessageBox">
		
			<c:choose>
				<c:when test="${ messageWith == null }">
					<div id="messageNoSelectionMessage">
						<i class="material-icons">sms</i>
						<h1>팔로우한 친구들에게 메세지를 보내 보세요!</h1>
						<button>메세지 보내기</button>
					</div>
				</c:when>
				
				<c:otherwise>
					<div id="messageHistoryBox">
						<div id="messageTitle">
							<div id="messageTitleProfile" onclick="location.href='spring.do?command=userpage&userid=${ messageWith.userid }'">
								<img src="/images/${ messageWith.img }">
								
								<h1>${ messageWith.userid }</h1>
							</div>
							
							<i class="material-icons" onclick="openPopup('${ messageWith.userid }')">more_horiz</i>
						</div>
					
						<div id="messageHistory">
							<c:forEach var="message" items="${ messageHistory }">
								<c:choose>
									<c:when test="${ message.messageFrom == loginUser.userid }">
										<div class="messageRight">
											<div class="messageRightBox">
												<h3>${ message.content }</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="messageLeft">
											<div class="messageLeftBox">
												<img src="/images/${ message.fromImg }">
												<h3>${ message.content }</h3>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						
						<div id="sendMessageBar">
							<form action="spring.do" method="post">
								<input type="hidden" name="command" value="sendmessage">
								<input type="hidden" name="messagewith" value="${ messageWith.userid }">
								
								<input type="text" name="content">
								
								<input type="submit" value="보내기">
							</form>
						</div>
						
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
	
	
	
	<div id="popupWindow" style="display: none;">
		<div id="popupBox">
			<button onclick="goReport();">신고</button>
			<hr>
			<button onclick="unfollowPopup()">팔로우 취소</button>
			<hr>
			<button onclick="openPopup()">취소</button>
		</div>
	</div>
	
	<script type="text/javascript">
		let tmpUserid = ""
		
		function openPopup(userid) {
			let popup = document.getElementById("popupWindow")
			if(popup.style.display == "none") {
				popup.style.display = ""
				tmpUserid = userid
			} else {
				popup.style.display = "none"
				tmpUserid = ""
			}
		}
		
		function unfollowPopup() {
			if(confirm(tmpUserid + "님을 언팔로우 할까요?")) {
				location.href = "spring.do?command=unfollow&userid=" + tmpUserid
			}
		}
		
		function goReport(){
			var url="spring.do?command=reportForm&post_num=" + tmpPostnum;
			var _width = '400';
			var _height = '500';
			var _left = Math.ceil((window.screen.width - _width)/2); 
			var _top = Math.ceil((window.screen.width - _height)/2); 
			var opt = "toolbar=no, menubar=no, resizable=no, fullscreen=yes, location=no, " +
				"width="+_width+", height="+_height+", left="+_left;
			window.open(url, "reportPost", opt);
		}
	</script>
	
	
	<%@ include file="/footer.jsp" %>
</body>
</html>