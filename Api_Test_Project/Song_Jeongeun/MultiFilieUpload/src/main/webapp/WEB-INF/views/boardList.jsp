<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="./boardWrite.do">글쓰기</a>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="list" items="${list}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td><a href="./selectView.do?seq=${vs.count}">${list.title}</a></td>
				<td>${list.id}</td>
				<td>${list.readcount}</td>
				<td>${list.regdate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>