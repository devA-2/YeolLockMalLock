<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>배송</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
	.content{
		position: relative;
	}
	#pageBtn{
		position: absolute;
		top: 45%;
		left: 25%;
	}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<div id="pageBtn">
				<button class="btn btn-info" onclick="location.href='./deliveryInquiry.do'">미리 조회</button>
				<button class="btn btn-info" onclick="location.href='./deliveryList.do'">배송 내역 조회</button>
			</div>
		</div>
	</div>
</body>
</html>