<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>ID 검색 페이지</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div
		style="width: 360px; height: 600px; border: 1px solid black; margin: auto;">
		<table class="table table-hover">
			<tr>
				<th>번호</th>
				<th>소유자</th>
				<th>등록일</th>
			</tr>
			<c:forEach items="${list2}" var="vo" varStatus="vs">
				<tr onclick="location.href='./selectOneLostProperty.do?seq=${vo.seq}'">
					<td>${vo.seq}</td>
					<td>${vo.receipt_user_id}</td>
					<td>${vo.lost_regdate}</td>
				</tr>
			</c:forEach>
		</table>
		<button onclick="location.href='./lostPropertyList.do'">전체 목록으로</button>
	</div>
</body>
</html>