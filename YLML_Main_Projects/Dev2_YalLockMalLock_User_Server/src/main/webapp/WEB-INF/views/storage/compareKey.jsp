<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
	<h1>
		결제전<br> 
		보관함 리더기에<br>
		NFC를 태그해주세요
	</h1>
	<h5>태그 일치시 결제화면으로 넘어갑니다</h5>
	<form method="post" action="./beforePay.do">
		키<input type="text" name="key"><br>
		overCost<input type="text" name="overCost" value="${overCost }"><br>
		<input type="submit" value="태그">
	</form>
	</div>
</body>
</html>