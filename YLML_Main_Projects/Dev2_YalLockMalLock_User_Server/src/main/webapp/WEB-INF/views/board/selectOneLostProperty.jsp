<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>유실물 상세 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/boardCss.css">
</head>
<body>
	<div class="container">
		<div>
			<span>소유자 : ${dto.receiptUserId}</span><br>
			<span>접수 일자 : ${dto.lostRegdate}</span><br>
			<span>처리 상태 : ${dto.lostStatus }</span><br>
			<span>만기일 : ${dto.andDate}</span><br>
			<span>보관 위치 : ${dto.lostKeepLocation}</span>
		</div>
		
		
	<div class="reportBtn">
		<button class="btn btn-info" onclick="location.href='./lostPropertyList.do'">Home</button>
	</div>
</div>
</body>
</html>