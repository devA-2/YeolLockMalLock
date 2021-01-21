<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>
<!-- 아임포트 라이브러리 추가 : jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- 아임포트 라이브러리 추가 : iamport.payment.js -->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<div>
    	<input type="hidden" id="cost" value="${sessionScope.cost}">
    	<input type="hidden" id="phoneNum" value="${sessionScope.phoneNum}">
    	<input type="button" id="check_module" value="결제하기">
	</div>
</body>
<script type="text/javascript" src="../js/payment.js"></script>
</html>