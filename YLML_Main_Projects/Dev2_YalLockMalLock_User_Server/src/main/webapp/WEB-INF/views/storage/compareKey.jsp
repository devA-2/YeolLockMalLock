<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	태그 절차 수정해야함  -->
	<div class="container">
	<h1>
		보관함 리더기에<br> NFC를 태그해주세요
	</h1>
	<form method="post" action="./insertGoods.do">
		<input type="text" name="NFC">
		<input type="submit" value="태그">
	</form>
	</div>
</body>
</html>