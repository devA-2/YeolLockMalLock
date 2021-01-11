<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<title>신고글 게시판 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div>
<div></div>
<table class="table table-hover">
	<tr>
		<th>SEQ</th>
		<th>작성 일자</th>
		<th>제목</th>
		<th>작성자</th>
	</tr>
	<c:forEach items="${lists}" var="vo" varStatus="vs">
		<tr onclick="location.href='./selectOneReport.do?seq=${vo.seq}'">
			<td>${vo.seq}</td>
			<td>${vo.regdate}</td>
			<td>${vo.title}</td>
			<td>${vo.email}</td>
		</tr>
	</c:forEach>
</table>
</div>
<div>
	<div>
		<button onclick="location.href = './insertReport.do'">신고 글 작성</button><br>
	</div>
	<div>
		<form action="./searchId.do" method="post">
			<input type="submit" value="검색">
		</form>
	</div>
</div>
</body>
</html>