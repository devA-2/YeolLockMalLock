<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		보관함 리더기에<br> NFC를 태그해주세요
	</h1>
	<div>
		<form action="./beforePay.do" method="post">
			<!-- 	<input type="hidden" name="" value=""> -->
			<!-- 	<input type="hidden" name="" value=""> -->
			<input type="hidden" name="overCost" value="${overCost}"> 
			<input	type="text" name="key" value=""> 
			<input type="submit" value="가상 키대조 버튼">
		</form>
	</div>
</body>
</html>