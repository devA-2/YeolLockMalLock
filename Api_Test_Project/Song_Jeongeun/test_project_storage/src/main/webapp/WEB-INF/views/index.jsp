<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./userStorageList.do">
		<input type="hidden" name="email" value="user01@naver.com">
		<input type="submit" value="보관 내역 조회">
	</form>
	<a href="./deliveryListMain.do">배송(사용자)</a>
	<form action="./deliveryList.do">
		<input type="hidden" name="email" value="del01@naver.com">
		<input type="hidden" name="auth" value="80">
		<input type="submit" value="배송(배송원)">
	</form>
</body>
</html>