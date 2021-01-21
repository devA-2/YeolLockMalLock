<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>유실물 상세 조회</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div style="width: 360px; height: 600px; border: 1px solid black; margin: auto;">
	<table class="table table-hover">
		<tr>
			<th>소유자</th>
			<th>접수 일자</th>
			<th>처리 상태</th>
			<th>만기일</th>
			<th>보관 위치</th>
		</tr>
		<tr>
			<td>${dto.receipt_user_id}</td>
			<td>${dto.lost_regdate}</td>
			<td>${dto.lost_status }</td>
			<td>${dto.and_date}</td>
			<td>${dto.lost_keep_location}</td>
		</tr>
	</table>
	<div>
		<button onclick="location.href='./lostPropertyList.do'">뒤로가기</button>
	</div>
</div>
</body>
</html>