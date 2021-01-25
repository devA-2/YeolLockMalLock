<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/updateMypage.js"></script>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지 수정</title>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
</head>
<body>
	<div id="container">
	<h1>마이페이지 수정</h1>
		<form action="./updateInfo.do" method="post">
			<label>변경 할 핸드폰 번호 : </label>
				<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="11">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
			<div id="checkSMS">
				<input type="text" id="inputCertified_num" size="5">
				<input type="button" id="checkBtn" value="확인">
				<div class="time"></div>
			</div>
			<input type="submit" value="개인정보 수정" id="updateInfoBtn">	
		</form>
	</div>
</body>
</html>