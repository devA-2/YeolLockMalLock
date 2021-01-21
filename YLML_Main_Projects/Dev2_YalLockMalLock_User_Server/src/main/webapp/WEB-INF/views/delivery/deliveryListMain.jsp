<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송</title>
</head>
<body>
	<a href="./deliveryInquiry.do">미리 조회</a>
	<form action="./deliveryList.do">
		<input type="hidden" name="email" value="user01@naver.com">
		<input type="hidden" name="auth" value="10">
		<input type="submit" value="배송 내역 조회">
	</form>
</body>
</html>