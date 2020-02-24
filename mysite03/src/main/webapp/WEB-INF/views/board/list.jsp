<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?a=find&title=${vo.title}" method="post">
					<input type="text" id="kwd" name="kwd" value="">
						
					 <input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
				<c:set var='listCount' value='${fn:length(list)}' />
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:forEach items="${list}" var="vo" varStatus='status'>
						<tr>
							<td>${listCount-status.index}</td>
							<td style="text-align:left; padding-left: ${20*vo.depth}px">
							<c:choose>
								<c:when test="${empty vo.title }">
									<c:if test="${vo.depth ne 0}">
										<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
									</c:if>
									<a>삭 제</a>
								</c:when>
								<c:otherwise>
									<c:if test="${vo.depth ne 0}">
										<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
									</c:if>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a>
								</c:otherwise>
							</c:choose>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate }</td>
							<c:if test="${authUser.no eq vo.userNo }">
								<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}&userNo=${vo.userNo}" class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>


				<!-- pager 추가 -->
				<div class="pager">
				    <ul>
				        <c:if test="${ vo.curPage > 5 && !empty kwd }">
				            <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ vo.blockStartNum - 1 }&kwd=${ kwd }
				           &curPage=${vo.curPage}">◀</a></li>
				        </c:if>
				        
				        <c:if test="${ curPageNum > 5 }">
				            <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ vo.blockStartNum - 1 }">◀</a></li>
				        </c:if>
				        
				        <c:forEach var="i" begin="${ vo.blockStartNum }" end="${ vo.blockLastNum }">
				            <c:choose>
				                <c:when test="${ i > vo.lastPageNum }">
				                    <li>${ i }</li>
				                </c:when>
				                <c:when test="${ i == vo.curPage }">
				                    <li class="selected">${ i }</li>
				                </c:when>
				                <c:when test="${ !empty kwd}">
				                    <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ i }&kwd=${ kwd }">${ i }</a></li>
				                </c:when>
				                <c:otherwise>
				                    <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ i }">${ i }</a></li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				        
				        <c:if test="${ vo.lastPageNum > vo.blockLastNum && !empty kwd }">
				            <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ vo.blockLastNum + 1 }&kwd=${ kwd }">▶</a></li>
				        </c:if>
				        
				        <c:if test="${ vo.lastPageNum > vo.blockLastNum }">
				            <li><a href="${pageContext.servletContext.contextPath}/board?a=list&page=${ vo.blockLastNum + 1 }">▶</a></li>
				        </c:if>
				    </ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${not empty authUser}">
								
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board?a=writeform&no=${authUser.no}"
							id="new-book">글쓰기</a>
					</div>
				</c:if>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>