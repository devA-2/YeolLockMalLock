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
<script type="text/javascript" src="../js/userStorageList.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
	#content{
		position: absolute;
		left: 30%;
		top: 35%;
	}
	#btn{
		position: absolute;
		left: 10%;
		top: 250%;
	}
</style>
</head>
<body>
	<div id="container" class="container">
		<jsp:include page="../menu.jsp" />
		<div id="content">
			<p>배송이 완료되었습니다.</p>
			<button class="btn btn-info" id="btn" onclick="location.href='http://localhost:8095/Dev2_YalLockMalLock_User_Server/index.do'">홈으로</button>
		</div>
	</div>
</body>
</html>