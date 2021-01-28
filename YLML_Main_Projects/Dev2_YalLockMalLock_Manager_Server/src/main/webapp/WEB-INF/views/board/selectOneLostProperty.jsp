<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>유실물 상세 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
</head>
<body>
<div id="container">
 <%@include file="../header.jsp" %>
	<div style="width:1000px;">
		<table class="table table-hover">
			<tr>
				<th>소유자</th>
				<th>접수 일자</th>
				<th>처리 상태</th>
				<th>만기일</th>
				<th>보관 위치</th>
			</tr>
			<tr>
				<td>${dto.receiptUserId}</td>
				<td>${dto.lostRegdate}</td>
				<td>${dto.lostStatus }</td>
				<td>${dto.andDate}</td>
				<td>${dto.lostKeepLocation}</td>
			</tr>
		</table>
	</div>
		<div>
			<button onclick="location.href='./lostPropertyList.do'" class="btn btn-primary">뒤로가기</button>
		</div>
<%@include file="../footer.jsp" %>
</div>
</body>
</html>