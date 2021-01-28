<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div style="width: 360px; height: 600px; border: 1px solid black; margin:auto;">
	<table class="table table-hover">
		<tr>
			<th>연번</th>
			<th>소유자</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${lists}" var="vo" varStatus="vs">
			<tr onclick="location.href='./selectOneLostProperty.do?seq=${vo.seq}'">
				<td>${vo.seq}</td>
				<td>${vo.receiptUserId}</td>
				<td>${vo.lostRegdate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<button onclick="location.href='#'">메인으로</button>
		<form action="./searchIdLostProperty.do" method="post">
			<input type="text" value="USER02@NAVER.COM" name="receipt_user_id">
			<input type="submit" value="검색">
		</form>
	</div>
</div>
</body>
</html>