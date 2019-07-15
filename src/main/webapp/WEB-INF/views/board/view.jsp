<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	pageContext.setAttribute("newline", "\n");
	
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
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${oneVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(oneVo.contents, newline, "<br>") }	
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/list?page=${param.page}&kwd=${param.kwd}">글목록</a>
				<c:set var='userNo' value='<%=userNo %>' />
				<c:if test="${oneVo.userNo == userNo }">
					<a href="${pageContext.servletContext.contextPath }/board/modify/${oneVo.no}?page=${param.page}&kwd=${param.kwd}">글수정</a>
				</c:if>
					<a href="${pageContext.servletContext.contextPath }/board/write?no=${oneVo.no}&page=${param.page}&kwd=${param.kwd}">덧글</a>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>