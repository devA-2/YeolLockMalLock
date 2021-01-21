<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">
function sendCodeToMail(){
	location.href='./sendCodeToMail.do'
}

</script>
</head>
<body>
	<div id=container>
	<h1>이메일 인증폼</h1>
		<form action="member/checkCode.do" method="post">
			<input type="text" name="code"><br>
			<input type="button" name="sendMailBtn" onclick='sendCodeToMail()' value="인증메일 보내기">
			<input type="submit" name="checkCodeBtn" value="인증하기">
		</form>
	</div>
</body>
</html>