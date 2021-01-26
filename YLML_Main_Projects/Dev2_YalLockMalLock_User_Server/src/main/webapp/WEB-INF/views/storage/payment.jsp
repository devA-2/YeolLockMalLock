<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>결제</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="../js/userStorageList.js"></script>
<!-- 아임포트 라이브러리 추가 : jQuery -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<!-- 아임포트 라이브러리 추가 : iamport.payment.js -->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style type="text/css">
	.cost{
		position: absolute;
		left: 25%;
		top: 40%;
		font-size: 12px;
	}
	
	.btn{
		position: absolute;
		left: 35%;
		top: 45%;
	}	
</style>
</head>
<body>
	<div id="container" class="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<div class="cost">
				<span>총 보관 비용은 ${cost}원 입니다.</span>
		    	<input type="hidden" id="cost" value="${cost}">
		    	<input type="hidden" id="phoneNum" value="${mem.phoneNum}">
			</div>
			<div class="btn">
				<button class="btn btn-info" id="check_module">결제하기</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../js/payment.js"></script>
</html>