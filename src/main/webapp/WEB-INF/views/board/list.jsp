<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
	Long userNo = 0L;
	if(authUser != null){
		userNo = authUser.getNo();
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/search" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${listCount-(currentPage-1)*10}' />
					<c:if test="${fn:length(list) > 0}">
						<c:forEach items='${list }' var='vo' varStatus='status'>
							<tr>
								<td>${count-status.index }</td>
								<!-- 원래는 0대신 vo.depth로 -->
								<td style="text-align:left; padding-left:${15*vo.depth}px">
								<c:choose>
									<c:when test="${vo.status == true }">
										<a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}?page=${currentPage}&kwd=${kwd}">
									 		<c:if test="${0 ne vo.depth }">
							                   <img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>
							                </c:if>
									 		${vo.title }
									    </a>
									</c:when>
									<c:when test="${vo.status == false }">
											<i>
											${vo.title }											
											</i>
									</c:when>
								</c:choose>
								</td>
								<td>${vo.name}</td>
								<td>${vo.hit}</td>
								<td>${vo.regDate }</td>

								<c:set var='userNo' value='<%=userNo %>' />
								<c:if test="${vo.userNo == userNo}">
								<c:if test="${vo.status == true }">
									<td><a href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}?page=${currentPage}&kwd=${kwd}" class="del">삭제</a></td>
								</c:if>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul id="pager">
					<!-- 
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
					 -->
					</ul>
				</div>
				<!-- pager 추가 -->
			<%
	          if(authUser != null){
	        %>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write?page=${currentPage}&kwd=${kwd}"
						id="new-book">글쓰기</a>
				</div>
			<%
	         }
            %>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
	
</body>
	<c:set var='listCount' value='${listCount}'/>
	<c:set var='currentPage' value='${currentPage}'/>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/paging.js"></script>
	<script type="text/javascript">
		var contextPath ="${pageContext.servletContext.contextPath }";
		setPage(${listCount}, ${currentPage}, contextPath);
	</script>
</html>