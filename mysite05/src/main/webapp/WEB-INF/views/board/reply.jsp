<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board/write">
					<input type="hidden" name="gNo" value="${boardVo.gNo }">
					<input type="hidden" name="oNo" value="${boardVo.oNo }">
					<input type="hidden" name="depth" value="${boardVo.depth }">
					<input type="hidden" name="p" value="${param.p }" />
					<input type="hidden" name="kwd" value="${param.kwd }" />
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="contents"
									required="required"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/view/${vo.no}">취소</a>
						<input type="submit" value="답글">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>