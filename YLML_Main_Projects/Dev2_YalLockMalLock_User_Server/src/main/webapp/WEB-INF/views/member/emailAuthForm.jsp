<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/signUp.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css">
</head>
<style type="text/css">
h1.info{
	font-weight: 600;
	text-align: center;
}

</style>
<script type="text/javascript">
function sendCodeToMail(){
	location.href='./sendCodeToMail.do'
}
</script>
</head>
<body>
	<div id="container">
	<h1 class="info">이메일 인증폼</h1><br><br>
	<div id="content"></div>
		<form action="./checkCode.do" method="post">
			<input type="text" name="code"><br>
			<input type="button" name="sendMailBtn" onclick='sendCodeToMail()' value="인증메일 보내기">
			<input type="submit" name="checkCodeBtn" value="인증하기">
		</form>
	</div>
</body>
</html>