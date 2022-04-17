<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
		</div>