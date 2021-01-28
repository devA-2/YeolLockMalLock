<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
#content{
	text-align: center;
	padding : 10%;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<h1>
				<br><br><br>문이 열렸습니다<br> 
			</h1>
			<hr>
			보관 후 문을 닫고 버튼을 클릭해주세요<br>
			클릭시 문이 잠깁니다 <br><br>
			<input type="button" value="보관 완료"  class="btn btn-info"  onclick="location.href='../index.do'">

		</div>

	</div>
</body>
</html>