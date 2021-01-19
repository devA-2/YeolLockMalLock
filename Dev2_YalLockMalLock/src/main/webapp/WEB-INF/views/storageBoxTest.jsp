<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관한 테스트</title>
</head>
<body>
	<form action="./userStorageList.do">
		<input type="hidden" name="email" value="user01@naver.com">
		<input type="submit" value="보관함">	
	</form>
	
	<form action="./checkDeliveryGoods.do">
		<input type="hidden" name="storageId" value="CITYHALL_A">
		<input type="submit" value="배송 물량 확인">	
	</form>
	
	<form action="./userDeliveryList.do">
		<input type="hidden" name="email" value="user01@naver.com">
		<input type="submit" value="배송">	
	</form>
</body>
</html>