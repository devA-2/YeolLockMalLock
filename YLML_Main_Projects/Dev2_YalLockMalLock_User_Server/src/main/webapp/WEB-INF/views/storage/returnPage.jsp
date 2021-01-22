<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
.container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}

</style>
</head>
<body>
<div class="container">
	<h3>보관한 상대방에게 반품 처리됩니다.<br> 반품 메세지를 입력해주세요</h3>
	<form action="./insertReturn.do" method="post">
		<textarea class="form-control" rows="5" name="message" placeholder="반품합니다"></textarea>
		<input type="submit" class="btn btn-success" value="반품완료"> 	
	</form>

</div>
</body>
</html>